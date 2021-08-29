package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;

public interface Command {
    String getName();
    String[] getAliases();

    void run(VkApi vkApi, Message message, String[] args) throws ClientException, ApiException;
}