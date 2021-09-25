package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Charles_Grozny
 */
public class ReputationTop extends AbstractCommand {

    public ReputationTop(VkApi vkApi) {
        super(vkApi, "reptop", "рептоп");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        List<UserChat> top = chatRepository.getUsersFromChat(message.getPeerId())
                .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        top.removeIf(s -> (s.getReputation() < 1));

        if (top.size() == 0) {
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("⭐ В беседе у всех 0 репутации.").execute();
        } else {
            StringBuilder text = new StringBuilder("⭐ Топ репутации пользоватей в беседе:\n");

            for (int i = 0; i < top.size(); i++) {
                User user = User.user(vkApi, String.valueOf(top.get(i).getUserId()));
                text.append(i+1).append(". [id").append(user.getUserId()).append("|").append(user.getFirstName()[0]).append(" ").append(user.getLastName()[0]).append("] - ").append(top.get(i).getReputation() + " реп.\n");
            }

            vk.messages().send(actor).randomId(0).peerId(message.getPeerId()).disableMentions(true)
                    .message(text.toString()).execute();
        }
    }
}