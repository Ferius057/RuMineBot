package kz.ferius_057.ruminebot.command;

import kz.ferius_057.ruminebot.Manager;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.exceptions.VkApiException;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Charles_Grozny
 */
public class ReputationTop extends AbstractCommand {

    public ReputationTop(Manager Manager) {
        super(Manager, "reptop", "рептоп");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        List<UserChat> top = chatRepository.getUsersFromChat(message.getPeerId())
                .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        top.removeIf(s -> (s.getReputation() < 1));

        if (top.size() == 0) {
            vk.messages.send().setPeerId(message.getPeerId())
                    .setMessage("⭐ В беседе у всех 0 репутации.").execute();
        } else {
            StringBuilder text = new StringBuilder("⭐ Топ репутации пользоватей в беседе:\n");

            for (int i = 0; i < top.size(); i++) {
                User user = User.get(manager, top.get(i).getUserId());
                text.append(i + 1).append(". [id").append(user.getUserId()).append("|").append(user.getFirstName()[0]).append(" ").append(user.getLastName()[0]).append("] - ").append(top.get(i).getReputation()).append(" реп.\n");
            }

            vk.messages.send().setPeerId(message.getPeerId()).setDisableMentions(true)
                    .setMessage(text.toString()).execute();
        }
    }
}