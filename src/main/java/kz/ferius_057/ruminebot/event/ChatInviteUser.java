package kz.ferius_057.ruminebot.event;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import com.vk.api.sdk.objects.messages.MessageActionStatus;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.tool.User;
import kz.ferius_057.ruminebot.command.api.tool.UserInPeerId;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;

/**
 * @author Charles_Grozny
 */
public class ChatInviteUser extends AbstractEvent {

    public ChatInviteUser(final VkApi vkApi) {
        super(vkApi, MessageActionStatus.CHAT_INVITE_USER);
    }

    @Override
    public void run(Message message, MessageAction action) throws ClientException, ApiException {
        if (action.getMemberId() >= 0) {
            UserInPeerId userInPeerId = vkApi.getChatDao().getUserInPeerId(message.getPeerId() + "_" + action.getMemberId());
            if (userInPeerId != null) {
                vk.messages().send(actor).randomId(0).peerId(message.getPeerId()).disableMentions(true)
                        .message("[id" + action.getMemberId() + "|" + userInPeerId.getNickname() + "], ты уже был в этой беседе.").execute();

                vkApi.getChatDao().updateExist(message.getPeerId() + "_" + message.getFromId(), true);
            } else {
                User user = User.user(vkApi, action.getMemberId().toString());

                vk.messages().send(actor).randomId(0).peerId(message.getPeerId()).disableMentions(true)
                        .message("[id" + action.getMemberId() + "|" + user.getFirstName()[0] + "], ты не был в этой беседе.").execute();
                vkApi.getChatDao().addUserInPeerId(message.getPeerId() + "_" + action.getMemberId(), user.getFirstName()[0].toString(), "0");
            }
        }
    }
}
