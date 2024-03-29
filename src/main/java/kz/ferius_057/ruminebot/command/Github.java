package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.JsonObject;
import kz.ferius_057.ruminebot.command.api.impl.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.MinimalArgs;
import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Charles_Grozny
 */
@MinimalArgs(value = 1, message = "!github {ссылка на github аккаунт}")
@CommandAnnotation(aliases = { "github", "гитхаб" })
public class Github extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val github = args[0].length() < 12 ? Math.random() : nickNameParseFromGithubLink(args[0]);
        val user = cache.getSender();
        val name = "[id" + message.getFromId() + "|" + cache.getSender().getFirstName()[0] + "],";

        val request = getRequest("https://api.github.com/users/" + github);

        val jsonObject = localData.gson.fromJson(request, JsonObject.class);
        val type = jsonObject.get("type").getAsString();

        String msg;
        if (type.equalsIgnoreCase("user")) {

            chatRepository.updateUser(user.getUserId(),
                    user.getFirstName(), user.getLastName(),
                    "github.com/" + github, user.getNicknameMinecraft());
            localData.users.remove(user.getUserId());

            msg = "✅ " + name + " Вы установили себе Github: github.com/" + github;

        } else if (type.equalsIgnoreCase("organization")) msg = "❗ Github может быть только типа пользователь.";
        else msg = type;

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }

    private String nickNameParseFromGithubLink(final String text) {
        return text.substring(text.indexOf("github.com/") + 1 + "github.com/".length() - 1);
    }


    public String getRequest(String url) {
        try {
            val obj = new URL(url);
            val connection = (HttpURLConnection) obj.openConnection();
            val in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            val response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            return "{\"type\": \"❌ Такой аккаунт github не найден.\"}";
        }
    }
}