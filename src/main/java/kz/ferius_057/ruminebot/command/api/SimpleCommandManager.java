package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.*;
import kz.ferius_057.ruminebot.command.Info;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class SimpleCommandManager implements CommandManager {
    private final VkApi vkApi;
    private final Map<String, Command> commandMap;

    private SimpleCommandManager(final VkApi vkApi, final Map<String, Command> commandMap) {
        this.vkApi = vkApi;
        this.commandMap = commandMap;
    }

    public static CommandManager create(final VkApi vkApi) {
        CommandManager commandManager = new SimpleCommandManager(vkApi, new HashMap<>());
        commandManager.register(new Register(vkApi));
        commandManager.register(new Info(vkApi));
        commandManager.register(new Resync(vkApi));
        commandManager.register(new AddReputation(vkApi));
        commandManager.register(new Profile(vkApi));
        commandManager.register(new BanRep(vkApi));
        commandManager.register(new UnBanRep(vkApi));

        return commandManager;
    }

    @Override
    public boolean run(final Message message) {
        String text = message.getText();

        Command command;

        String[] args;

        if (text.length() <= 1 || text.charAt(0) != '!') {
            if (commandMap.get(text.split(" ")[0].toLowerCase()) != null && text.charAt(0) == '+') {
                String[] params = text.substring(1).split(" ");

                command = commandMap.get(text.split(" ")[0].toLowerCase());

                args = Arrays.copyOfRange(params, 1, params.length);
            } else return false;
        } else {
            String[] params = text.substring(1).split(" ");

            command = commandMap.get(params[0].toLowerCase());

            args = Arrays.copyOfRange(params, 1, params.length);
        }


        if (command == null) return true;

        try {
            command.run(message, args);
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void register(final Command command) {
        commandMap.put(command.getName(), command);

        for (String alias : command.getAliases())
            commandMap.put(alias, command);
    }

}