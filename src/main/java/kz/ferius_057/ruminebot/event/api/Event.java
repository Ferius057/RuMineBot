package kz.ferius_057.ruminebot.event.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import com.vk.api.sdk.objects.messages.MessageActionStatus;
import kz.ferius_057.ruminebot.VkApi;

/**
 * @author Charles_Grozny
 */
public interface Event {
    MessageActionStatus getEventName();

    void run(VkApi vkApi, Message message, MessageAction action) throws ClientException, ApiException;
}