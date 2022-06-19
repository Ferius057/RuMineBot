package kz.ferius_057.ruminebot.event;

import com.vk.api.sdk.exceptions.ApiException;
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
public class ChatKick extends AbstractEvent {

    public ChatKick(final VkApi vkApi) {
        super(vkApi, MessageActionStatus.CHAT_KICK_USER);
    }

    @Override
    public void run(Message message, MessageAction action) throws ClientException, ApiException {
        UserChat userInPeerId = chatRepository.getUserFromChat(action.getMemberId(), message.getPeerId());
        if (userInPeerId == null) {
            chatRepository.addUserInPeerId(action.getMemberId(), message.getPeerId(), User.user(vkApi, action.getMemberId().toString()).getFirstName()[0].toString(),0);
        }
        chatRepository.updateExist(message.getFromId(), message.getPeerId(),false);
    }
}