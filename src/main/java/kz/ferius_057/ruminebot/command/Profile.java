package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Profile extends AbstractCommand {

    public Profile(Manager Manager) {
        super(Manager,"profile", "account","профиль","аккаунт","админы","стат");
    }

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

        StringBuilder text = new StringBuilder();
        text.append("\n\uD83D\uDC8E Информация о пользователе ").append(user.getFullName().get(0).getPush()).append(":");
        text.append("\n\uD83C\uDD94 ID Профиля VK: ").append(user.getUserId());
        text.append("\n\uD83D\uDDFD Ник в беседе: ").append(userChat.getNickname());
        if (userChat.getRole() == 0) text.append("\n\uD83D\uDC51 Роль: Участник");
        else text.append("\n\uD83D\uDC51 Роль: Админ");
        text.append("\n✳ Репутации: ").append(userChat.getReputation());
        if (userChat.isBanrep()) text.append("\n⭕ Бан Репутации: Есть");
        else text.append("\n⭕ Бан Репутации: Нету");

        if (user.getNicknameMinecraft().equals("false"))
            text.append("\n\n\uD83D\uDCA0 Ник в Minecraft: Не указан");
        else text.append("\n\n\uD83D\uDCA0 Ник в Minecraft: ").append(user.getNicknameMinecraft());
        if (user.getGithub().equals("false")) text.append("\n⚜ Github: Не указан");
        else text.append("\n⚜ Github: ").append(user.getGithub());

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(text.toString())
                .execute();
    }
}