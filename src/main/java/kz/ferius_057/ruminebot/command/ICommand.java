package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

public interface ICommand {
    String getName();

    String[] getAliases();

    void run(VkApiClient vkApiClient, GroupActor groupActor, Message message, String[] args) throws ClientException, ApiException;
}