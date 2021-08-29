package kz.ferius_057.ruminebot.command.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.Info;
import kz.ferius_057.ruminebot.command.Kick;
import kz.ferius_057.ruminebot.command.Register;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
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
        commandManager.register(new Register());
        commandManager.register(new Kick());
        commandManager.register(new Info());

        return commandManager;
    }

    @Override
    public void run(final Message message) {
        String text = message.getText();

        if (text.length() <= 1 || text.charAt(0) != '!') return;

        String[] params = text.substring(1).split(" ");

        Command command = commandMap.get(params[0].toLowerCase());
        if (command == null) return;

        String[] args = Arrays.copyOfRange(params, 1, params.length);

        try {
            command.run(vkApi, message, args);
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(final Command command) {
        commandMap.put(command.getName(), command);

        for (String alias : command.getAliases())
            commandMap.put(alias, command);
    }

}