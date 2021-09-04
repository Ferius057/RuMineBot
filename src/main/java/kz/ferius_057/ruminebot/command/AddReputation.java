package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.tool.UserInPeerId;
import kz.ferius_057.ruminebot.database.ChatDao;

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
            String user = peerId + "_" + replyMessage.getFromId();

            boolean isUserInPeerId = chatDao.isUserInPeerId(user);

            UserInPeerId userSender = chatDao.getUserInPeerId(peerId + "_" + message.getFromId());

            GetResponse getResponse = vk.users().get(actor).userIds(replyMessage.getFromId().toString()).execute().get(0);
            if (isUserInPeerId) {
                // Проверка есть ли бан репутации у того кто даёт репутацию
                if (!userSender.isBanrep()) {
                    chatDao.giveReputation(user, chatDao.getUserInPeerId(user).getReputation() + 1);
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("[id" + replyMessage.getFromId() + "|" +
                                    getResponse.getFirstName() + " " + getResponse.getLastName() +
                                    "] получил +1 к репутации.").execute();
                } else {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("[id" + message.getFromId() + "|" +
                                    userSender.getNickname() + "], вы не можете выдавать репутацию, так как у вас бан репутации.").execute();
                }
            } else {
                vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                        .message("[id" + replyMessage.getFromId() + "|" +
                                getResponse.getFirstName() + " " + getResponse.getLastName() +
                                "] отсутствует в этой беседе.").execute();
            }
        }
    }
}