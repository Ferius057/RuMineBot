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
public class ChatKickUser extends AbstractEvent {

    public ChatKickUser(final VkApi vkApi) {
        super(vkApi, MessageActionStatus.CHAT_KICK_USER);
    }

    @Override
    public void run(Message message, MessageAction action) throws ClientException, ApiException {
        vkApi.getChatDao().updateExist(message.getPeerId() + "_" + message.getFromId(),false);
    }
}