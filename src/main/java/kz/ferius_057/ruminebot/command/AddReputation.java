package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiParamUserIdException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

/**
 * @author Charles_Grozny
 */
public class AddReputation extends AbstractCommand {

    public AddReputation(VkApi vkApi) {
        super(vkApi,"+reputation", "+rep", "+репутация", "+реп");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        ForeignMessage replyMessage = getForeignMessage(message);

        int peerId = message.getPeerId();

        if (replyMessage != null) {
            UserChat user = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);
            UserChat userSender = chatRepository.getUserFromChat(message.getFromId(), peerId);

            User userData;
            try {
                userData = User.user(vkApi, String.valueOf(replyMessage.getFromId()));
            } catch (ApiParamUserIdException e) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❌ Не удалось получить пользователя | " + e.getMessage()).execute();
                return;
            }

            if (user == null || !user.isExist()) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❌ [id" + replyMessage.getFromId() + "|" + userData.getFirstName()[0] + " " + userData.getLastName()[0] + "] отсутствует в этой беседе.").execute();
                return;
            }

            if (user.getUserId() == userSender.getUserId()) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❌ Выдавать репутацию самому себе запрещено.").execute();
                return;
            }
            // Проверка есть ли бан репутации у того кто даёт репутацию
            if (!userSender.isBanrep()) {
                chatRepository.giveReputation(replyMessage.getFromId(), peerId, user.getReputation() + 1);
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("⚡ [id" + replyMessage.getFromId() + "|" +
                                userData.getFirstName()[0] + " " + userData.getLastName()[0] +
                                "] получил +1 к репутации.").execute();
            } else {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ [id" + message.getFromId() + "|" +
                                userSender.getNickname() + "], вы не можете выдавать репутацию, так как у вас бан репутации.").execute();
            }
        }
    }
}