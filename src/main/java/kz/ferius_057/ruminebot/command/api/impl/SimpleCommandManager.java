package kz.ferius_057.ruminebot.command.api.impl;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.manager.Manager;
import kz.ferius_057.ruminebot.command.api.Command;
import kz.ferius_057.ruminebot.command.api.CommandManager;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.MinimalArgs;
import kz.ferius_057.ruminebot.command.api.annotation.Permission;
import kz.ferius_057.ruminebot.command.api.annotation.ExceptRegistered;
import kz.ferius_057.ruminebot.command.api.model.CacheDataMessage;
import kz.ferius_057.ruminebot.object.basic.User;
import kz.ferius_057.ruminebot.object.basic.UserChat;
import kz.ferius_057.ruminebot.util.AccessingAllClassesInPackage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SimpleCommandManager implements CommandManager {

    Manager manager;
    Map<String, Command> commandMap;

    CacheDataMessage cacheDataMessage = new CacheDataMessage();

    @SneakyThrows
    public static CommandManager create(final Manager manager) {
        val commandManager = new SimpleCommandManager(manager, new HashMap<>());
        val classesInPackage = AccessingAllClassesInPackage.getClassesCommand("kz.ferius_057.ruminebot.command");
        for (val clazz : classesInPackage)
            commandManager.register((Command) clazz.getConstructor().newInstance());

        System.out.printf("Зарегистрировано %d команд.\n", classesInPackage.size());

        return commandManager;
    }

    @Override
    public void run(final Message message) {
        val text = message.getText();

        Command command;
        String[] split, params, args;

        // TODO: 29.07.2022 | переписать и сделать что бы через пробел команда тоже работала

        split = text.split(" ");
        if (split.length == 0) return;

        if (text.length() <= 1 || text.charAt(0) != '!') {
            if (commandMap.get(split[0].toLowerCase()) == null || text.charAt(0) != '+') return;

            params = text.substring(1).split(" ");
            command = commandMap.get(split[0].toLowerCase());
        } else {
            params = text.substring(1).split(" ");
            command = commandMap.get(params[0].toLowerCase());
        }
        args = Arrays.copyOfRange(params, 1, params.length);

        if (command != null) {
            try {
                val messages = replyMessage(message);

                cacheDataMessage.getReplySenders().clear();
                cacheDataMessage.getReplySendersUserChat().clear();

                cacheDataMessage.setSender(User.get(manager, message.getFromId()));
                cacheDataMessage.setSenderUserChat(manager.chatRepository().getUserFromChat(message.getFromId(), message.getPeerId()));

                if (!checkRegisterChatForCommand(command, message.getPeerId())) return;
                if (!checkSyntax(command, message.getPeerId(), args)) return;
                if (!checkPermission(command, cacheDataMessage.getSender(), cacheDataMessage.getSenderUserChat(), message.getPeerId())) return;

                if (messages.size() == 0)
                    command.run(cacheDataMessage, message, args);
                else {
                    val users = cacheDataMessage.getReplySenders();
                    val userChats = cacheDataMessage.getReplySendersUserChat();

                    for (val reply : messages) {
                        if (reply.getFromId() > 0) {
                            users.add(User.get(manager, reply.getFromId()));
                            userChats.add(manager.chatRepository().getUserFromChat(reply.getFromId(), message.getPeerId()));
                        }
                    }

                    if (users.size() == 0) {
                        manager.vk().messages.send()
                                .setPeerId(message.getPeerId())
                                .setDisableMentions(true)
                                .setMessage("❌ Данная команда работает только на пользователей.")
                                .execute();
                        return;
                    }

                    cacheDataMessage.setReplySenders(users);
                    cacheDataMessage.setReplySendersUserChat(userChats);

                    command.run(cacheDataMessage, message, messages, args);
                }
            } catch (VkApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void register(final Command command) {
        for (val alias : command.getClass().getAnnotation(CommandAnnotation.class).aliases())
            commandMap.put(alias, command);
    }

    private List<Message> replyMessage(Message message) {
        val messages = message.getFwdMessages();
        if (message.getReplyMessage() != null) messages.add(0, message.getReplyMessage());

        return messages;
    }


    private boolean checkPermission(Command command, User user, UserChat userChat, int peerId) throws VkApiException {
        val declaredAnnotation = command.getClass().getDeclaredAnnotation(Permission.class);
        if (declaredAnnotation == null || userChat.getRole() >= declaredAnnotation.value()) return true;

        manager.vk().messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage("❗ [id" + user.getUserId() + "|" + cacheDataMessage.getSender().getFirstName()[0] + "], у вас недостаточно прав для данной команды.")
                .execute();
        return false;
    }

    private boolean checkSyntax(Command command, int peerId, String... args) throws VkApiException {
        val declaredAnnotation = command.getClass().getDeclaredAnnotation(MinimalArgs.class);
        if (declaredAnnotation == null || declaredAnnotation.value() <= args.length) return true;

        manager.vk().messages.send()
                .setPeerId(peerId)
                .setMessage(declaredAnnotation.message())
                .execute();
        return false;
    }

    private boolean checkRegisterChatForCommand(Command command, int peerId) throws VkApiException {
        val declaredAnnotation = command.getClass().getDeclaredAnnotation(ExceptRegistered.class);
        if (declaredAnnotation != null || manager.getPeerIds().contains(peerId)) return true;

        manager.vk().messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage("❌ Для использования этой команды нужно зарегистрировать чат командой <<!reg>>.")
                .execute();
        return false;
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