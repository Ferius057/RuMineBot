package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.GetNameCase;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.tool.UserInPeerId;
import kz.ferius_057.ruminebot.database.ChatDao;

/**
 * @author Charles_Grozny
 */
public class UnBanRep extends AbstractCommand {

    public UnBanRep() {
        super("unbanrep", "repunban","разбанреп","репразбан");
    }

    @Override
    public void run(VkApi vkApi, Message message, String[] args) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();
        ChatDao chatDao = vkApi.getChatDao();

        int peerId = message.getPeerId();

        if (message.getFwdMessages().size() != 0 || message.getReplyMessage() != null) {
            ForeignMessage replyMessage = message.getReplyMessage();
            if (replyMessage == null) replyMessage = message.getFwdMessages().get(0);
            String user = peerId + "_" + replyMessage.getFromId();

            GetResponse getResponse = vk.users().get(actor).userIds(replyMessage.getFromId().toString()).nameCase(GetNameCase.GENITIVE).execute().get(0);
            String userName = getResponse.getFirstName() + " " + getResponse.getLastName();

            UserInPeerId userInPeerId = chatDao.getUserInPeerId(user);

            if (userInPeerId == null) {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ [id" + replyMessage.getFromId() + "|Пользователь] отсутствует в этой беседе.").execute();
                return;
            }


            if (userInPeerId.isBanrep()) {
                chatDao.updateBanReputation(user, false);
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("✅ Снял бан репутации у [id" + replyMessage.getFromId() + "|" + userName + "].").execute();
            } else {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("❗ У [id" + replyMessage.getFromId() + "|" + userName + "] нету бана репутации.").execute();
            }
        } else {
            vk.messages().send(actor).randomId(0).peerId(peerId)
                    .message("❗ Сообщение должно быть ответом на другое сообщение или пересланным сообщение.").execute();
        }
    }
}