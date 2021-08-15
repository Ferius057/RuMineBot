package com.ferius_057.ruminebot.longpoll;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;

public class CallbackApiLongPollHandler extends CallbackApiLongPoll {

    public CallbackApiLongPollHandler(VkApiClient client, GroupActor actor) {
        super(client, actor);
    }

    @Override
    public void messageNew(Integer groupId, final Message message) {
        System.out.println("\nnew message\ntext:" + message.getText());
    }
}
