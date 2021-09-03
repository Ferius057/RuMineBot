package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.tool.User;
import kz.ferius_057.ruminebot.command.tool.UserInPeerId;
import kz.ferius_057.ruminebot.database.ChatDao;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Charles_Grozny
 */
public class Profile extends AbstractCommand {

    public Profile(VkApi vkApi) {
        super(vkApi,"profile", "account","профиль","аккаунт","админы","стат");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        if (message.getFwdMessages().size() != 0 || message.getReplyMessage() != null) {
            ForeignMessage replyMessage = message.getReplyMessage();
            if (replyMessage == null) replyMessage = message.getFwdMessages().get(0);
            profile(vkApi.getUsers(), vkApi.getActor(), vkApi.getChatDao(), replyMessage.getFromId(), message.getPeerId());
        } else {
            profile(vkApi.getUsers(), vkApi.getActor(), vkApi.getChatDao(), message.getFromId(), message.getPeerId());
        }
    }

    private void profile(Set<Integer> users, GroupActor actor, ChatDao chatDao, int id, int peerId) throws ClientException, ApiException {
        if (users.contains(id)) {
            User userData = chatDao.getUser(id);

            String user = "[id" + id + "|" + userData.getFirstName()[0] + " " + userData.getLastName()[0] + "]";

            UserInPeerId userInPeerId = chatDao.getUserInPeerId(peerId + "_" + id);

            if (userInPeerId == null) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ [id" + id + "|" + userData.getFirstName()[0] + " " + userData.getLastName()[0] + "] отсутствует в этой беседе.").execute();
                return;
            }

            StringBuilder text = new StringBuilder();
            text.append("\n\uD83D\uDC8E Информация о пользователе ").append(user).append(":");
            text.append("\n\uD83C\uDD94 ID Профиля VK: ").append(id);
            text.append("\n\uD83D\uDDFD Ник в беседе: ").append(userInPeerId.getNickname());
            if (userInPeerId.getRole().equals("0")) text.append("\n\uD83D\uDC51 Роль: Участник");
            else if (userInPeerId.getRole().equals("1")) text.append("\n\uD83D\uDC51 Роль: Админ");
            text.append("\n✳ Репутации: ").append(userInPeerId.getReputation());
            if (userInPeerId.isBanrep()) text.append("\n⭕ Бан Репутации: Есть");
            else text.append("\n⭕ Бан Репутации: Нету");

            if (userData.getNicknameMinecraft().equals("false"))
                text.append("\n\n\uD83D\uDCA0 Ник в Minecraft: Не указан");
            else text.append("\n\n\uD83D\uDCA0 Ник в Minecraft: ").append(userData.getNicknameMinecraft());
            if (userData.getGithub().equals("false")) text.append("\n⚜ Github: Не указан");
            else text.append("\n⚜ Github: ").append(userData.getGithub());

            vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                    .message(text.toString()).execute();
        } else {
            vk.messages().send(actor).randomId(0).peerId(peerId)
                    .message("❌ Не удалось получить информацию.").execute();
        }
    }
}