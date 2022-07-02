package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Profile extends AbstractCommand {

    public Profile(Manager Manager) {
        super(Manager,"profile", "account","профиль","аккаунт","админы","стат");
    }

    @Override
    public void run(User sender, Message message, String[] args) throws VkApiException {
        profile(message.getFromId(), message.getPeerId());
    }

    @Override
    public void run(User sender, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        profile(replyMessages.get(0).getFromId(), message.getPeerId());
    }

    private void profile(int id, int peerId) throws VkApiException {
        User userData = User.get(manager, id);

        String user = "[id" + id + "|" + userData.getFirstName()[0] + " " + userData.getLastName()[0] + "]";

        UserChat userInPeerId = chatRepository.getUserFromChat(id, peerId);

        if (userInPeerId == null) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ [id" + id + "|" + userData.getFirstName()[0] + " " + userData.getLastName()[0] + "] отсутствует в этой беседе.")
                    .execute();
            return;
        }

        StringBuilder text = new StringBuilder();
        text.append("\n\uD83D\uDC8E Информация о пользователе ").append(user).append(":");
        text.append("\n\uD83C\uDD94 ID Профиля VK: ").append(id);
        text.append("\n\uD83D\uDDFD Ник в беседе: ").append(userInPeerId.getNickname());
        if (userInPeerId.getRole() == 0) text.append("\n\uD83D\uDC51 Роль: Участник");
        else if (userInPeerId.getRole() == 1) text.append("\n\uD83D\uDC51 Роль: Админ");
        text.append("\n✳ Репутации: ").append(userInPeerId.getReputation());
        if (userInPeerId.isBanrep()) text.append("\n⭕ Бан Репутации: Есть");
        else text.append("\n⭕ Бан Репутации: Нету");

        if (userData.getNicknameMinecraft().equals("false"))
            text.append("\n\n\uD83D\uDCA0 Ник в Minecraft: Не указан");
        else text.append("\n\n\uD83D\uDCA0 Ник в Minecraft: ").append(userData.getNicknameMinecraft());
        if (userData.getGithub().equals("false")) text.append("\n⚜ Github: Не указан");
        else text.append("\n⚜ Github: ").append(userData.getGithub());

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(text.toString())
                .execute();
    }
}