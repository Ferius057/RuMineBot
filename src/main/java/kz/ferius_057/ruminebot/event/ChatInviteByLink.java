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
public class ChatInviteByLink extends AbstractEvent {

    public ChatInviteByLink(final VkApi vkApi) {
        super(vkApi, MessageActionStatus.CHAT_INVITE_USER_BY_LINK);
    }

    @Override
    public void run(Message message, MessageAction action) throws ClientException, ApiException {
        System.out.println(action);
        if (action.getMemberId() >= 0) {
            User user = User.user(vkApi, action.getMemberId().toString());
            System.out.println(user.getUserId());
            System.out.println(user.getFirstName()[0]);
            chatRepository.addUserInPeerId(action.getMemberId(), message.getPeerId(), user.getFirstName()[0].toString(), 0);
        }
    }
}
