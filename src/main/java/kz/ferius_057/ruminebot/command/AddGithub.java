package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.JsonObject;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.MinimalArgs;
import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author Charles_Grozny
 */
@MinimalArgs(value = 1, message = "!github {ссылка на github аккаунт}")
@CommandAnnotation(aliases = { "github", "гитхаб" })
public class AddGithub extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val github = args[0].length() < 12 ? Math.random() : getFrom(args[0], "github.com/");
        val user = cache.getSender();
        val name = "[id" + message.getFromId() + "|" + cache.getSender().getFirstName()[0] + "],";

        String request = getRequest("https://api.github.com/users/" + github);

        JsonObject jsonObject = localData.gson.fromJson(request, JsonObject.class);
        String type = jsonObject.get("type").getAsString();

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

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }

    private String getFrom(final String text, final String from) {
        return text.substring(text.indexOf(from) + 1 + from.length() - 1);
    }


    public String getRequest(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
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