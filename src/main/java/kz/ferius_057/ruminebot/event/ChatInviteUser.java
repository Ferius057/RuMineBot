package kz.ferius_057.ruminebot.event;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiParamUserIdException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import com.vk.api.sdk.objects.messages.MessageActionStatus;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;
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
            UserChat userInPeerId = chatRepository.getUserFromChat(action.getMemberId(), message.getPeerId());
            if (userInPeerId != null) {

                chatRepository.updateExist(message.getFromId(), message.getPeerId(), true);
            } else {
                User user;
                try {
                    user = User.user(vkApi, action.getMemberId().toString());
                } catch (ApiParamUserIdException e) {
                   return;
                }

                chatRepository.addUserInPeerId(action.getMemberId(), message.getPeerId(), user.getFirstName()[0].toString(),0);
            }
        }
    }
}
