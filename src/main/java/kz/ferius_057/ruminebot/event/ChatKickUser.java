package kz.ferius_057.ruminebot.event;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import com.vk.api.sdk.objects.messages.MessageActionStatus;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class ChatKickUser extends AbstractEvent {

    public ChatKickUser() {
        super(MessageActionStatus.CHAT_KICK_USER);
    }

    @Override
    public void run(VkApi vkApi, Message message, MessageAction action) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();


    }
}