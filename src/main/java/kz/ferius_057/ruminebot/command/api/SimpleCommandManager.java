package kz.ferius_057.ruminebot.command.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.*;
import kz.ferius_057.ruminebot.command.role.Admin;
import kz.ferius_057.ruminebot.command.role.Default;
import kz.ferius_057.ruminebot.database.tool.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.*;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SimpleCommandManager implements CommandManager {
    Manager manager;
    Map<String, Command> commandMap;

    public static CommandManager create(final Manager manager) {
        CommandManager commandManager = new SimpleCommandManager(manager, new HashMap<>());
        commandManager.register(new Register(manager));
        commandManager.register(new Resync(manager));
        commandManager.register(new AddReputation(manager));
        commandManager.register(new Profile(manager));
        commandManager.register(new BanRep(manager));
        commandManager.register(new UnBanRep(manager));
        commandManager.register(new Uptime(manager));
        commandManager.register(new Admin(manager));
        commandManager.register(new Default(manager));
        commandManager.register(new Admins(manager));
        commandManager.register(new ReputationTop(manager));
        commandManager.register(new AddGithub(manager));
        commandManager.register(new AddNickNameMinecraft(manager));
        commandManager.register(new Help(manager));

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
            List<Message> messages = replyMessage(message);

            if (messages.get(0) == null)
                command.run(User.get(manager, message.getFromId()), message, args);
            else
                command.run(User.get(manager, message.getFromId()), message, messages, args);
        } catch (VkApiException e) {
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



    private List<Message> replyMessage(Message message) {
        List<Message> messages = message.getFwdMessages();
        messages.add(0, message.getReplyMessage());

        return messages;
    }



  /*
   TODO: 27.06.2022 | переписать это говно

   private boolean isRegisterPeerId(int peerId, Command command, LocalData localData) throws VkApiException {
        if (!Manager.getPeerIds().contains(peerId)) {
            if (!(command instanceof Register)) {
                if (localData.getRegisterPeerIdCooldown().containsKey(peerId)) {
                    if (localData.getRegisterPeerIdCooldown().get(peerId) < System.currentTimeMillis()) {
                        Manager.getClient().messages.send(Manager.get()).peerId(peerId).setDisableMentions(true)
                                .setMessage("❗ Для использования бота необходимо зарегестирировать беседу.").execute();
                        localData.setRegisterPeerIdCooldown(peerId, System.currentTimeMillis() + 5000);
                    }
                } else {
                    Manager.getClient().messages.send(Manager.get()).peerId(peerId).setDisableMentions(true)
                            .setMessage("❗ Для использования бота необходимо зарегестирировать беседу.").execute();
                    localData.setRegisterPeerIdCooldown(peerId, System.currentTimeMillis() + 5000);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }*/
}