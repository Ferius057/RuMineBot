package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.object.UserChat;
import lombok.val;

import java.util.List;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "profile", "account", "профиль", "аккаунт", "стат" })
public class Profile extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        profile(cache.getSender(), cache.getSenderUserChat(), message.getPeerId());
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        profile(cache.getReplySenders().get(0), cache.getReplySendersUserChat().get(0), message.getPeerId());
    }

    private void profile(User user, UserChat userChat, int peerId) throws VkApiException {
        if (userChat == null) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ " + user.getFullName().get(0).getPush() + " отсутствует в этой беседе.")
                    .execute();
            return;
        }

        val text = "\n\uD83D\uDC8E Информация о пользователе " + user.getFullName().get(0).getPush() + ":" +
                "\n\uD83C\uDD94 ID Профиля VK: " + user.getUserId() +

                "\n\n\uD83D\uDC51 Роль: " + (userChat.getRole() == 0 ? "Участник" : "Админ") +
                "\n✳ Репутации: " + userChat.getReputation() +
                "\n⭕ Бан Репутации: " + (userChat.isBanrep() ? "Есть" : "Нету") +

                "\n\n\uD83D\uDCA0 Ник в Minecraft: " + (user.getNicknameMinecraft().equals("false") ? "Не указан" : user.getNicknameMinecraft()) +
                "\n⚜ Github: " + (user.getGithub().equals("false") ? "Не указан" : user.getGithub());

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(text)
                .execute();
    }
}