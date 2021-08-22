package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Manager {
    Map<String, ICommand> commandMap = new HashMap<>();

    public void register(ICommand command) {
        commandMap.put(command.getName(), command);
        for (String alias : command.getAliases()) commandMap.put(alias, command);
    }

    public void execute(VkApiClient vkApiClient, GroupActor groupActor, Message message) {
        String text = message.getText();
        if (text.length() <= 1 || text.charAt(0) != '!') return;
        String[] params = text.substring(1).split(" ");
        ICommand command = commandMap.get(params[0]);
        if (command == null) return;
        String[] args = Arrays.copyOfRange(params, 1, params.length);
        try {
            command.run(vkApiClient, groupActor, message, args);
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }
}