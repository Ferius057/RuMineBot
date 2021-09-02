package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

public interface Command {
    String getName();
    String[] getAliases();

    void run(Message message, String[] args) throws ClientException, ApiException;
}