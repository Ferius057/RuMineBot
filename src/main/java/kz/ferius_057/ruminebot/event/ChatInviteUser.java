package kz.ferius_057.ruminebot.event;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import com.vk.api.sdk.objects.messages.MessageActionStatus;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;

/**
 * @author Charles_Grozny
 */
public class ChatInviteUser extends AbstractEvent {

    public ChatInviteUser() {
        super(MessageActionStatus.CHAT_INVITE_USER);
    }

    @Override
    public void run(VkApi vkApi, Message message, MessageAction action) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();

        String exist = vkApi.getChatDao().wasUserInPeerId(message.getPeerId() + "_" + action.getMemberId());
        System.out.println(exist);

        if (exist.equals("false")) {
            String firstName = vk.users().get(actor).userIds(action.getMemberId().toString()).execute().get(0).getFirstName();

            vk.messages().send(actor).randomId(0).peerId(message.getPeerId()).disableMentions(true)
                    .message("[id" + action.getMemberId() + "|" + firstName +  "], ты не был в этой беседе.").execute();


            vkApi.getChatDao().addUserInPeerId(message.getPeerId() + "_" + action.getMemberId(), firstName,"0");
        } else {
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId()).disableMentions(true)
                    .message("[id" + action.getMemberId() + "|" + exist +  "], ты уже был в этой беседе.").execute();

            vkApi.getChatDao().updateExist(message.getPeerId() + "_" + message.getFromId(),1);
        }
    }
}
