package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.impl.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.MinimalArgs;
import lombok.val;

import java.util.regex.Pattern;

/**
 * @author Charles_Grozny
 */
@MinimalArgs(value = 1, message = "!nick {твой ник}")
@CommandAnnotation(aliases = { "minenick", "minenickname", "minecraftnickname", "майнник", "ник", "никнейм", "nick", "nickname" })
public class NickNameMinecraft extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val nickName = args[0];
        val user = cache.getSender();
        val name = "[id" + message.getFromId() + "|" + cache.getSender().getFirstName()[0] + "],";

        String msg;
        if (Pattern.compile("^\\w{0,100}$").matcher(nickName).find()) { // \w - [A-Za-z0-9_]
            if (nickName.length() >= 2 && nickName.length() <= 32) {

                chatRepository.updateUser(user.getUserId(),
                        user.getFirstName(), user.getLastName(),
                        user.getGithub(), nickName);
                localData.users.remove(user.getUserId());

                msg = "✅ " + name + " Вы установили себе ник в Minecraft: " + nickName;

            } else msg = "❗ Длина ника должна быть не менее 2 и не более 32 символов.";
        } else msg = "❗ В вашем нике запрещенные символы.";

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}