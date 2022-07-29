package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.object.User;
import lombok.val;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = {"reptop", "рептоп"})
public class ReputationTop extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val text = new StringBuilder("⭐ Топ репутации пользователей в беседе:\n");
        val count = new AtomicInteger(0);
        chatRepository.getUsersFromChat(message.getPeerId()).stream()
                .filter(s -> s.getReputation() > 0).sorted(Comparator.reverseOrder())
                .forEach(userChat -> text.append(count.addAndGet(1)).append(". ")
                        .append(User.get(manager, userChat.getUserId()).getFullName().get(0).getPush())
                        .append(" - ")
                        .append(userChat.getReputation())
                        .append(" реп.\n"));

        if (count.get() <= 0)
            text.delete(0, text.length()).append("⭐ В беседе у всех 0 репутации.");

        if (hasBadTop(args)) {
            text.append("\n\n\uD83D\uDD3B Пользователи имеющие репутацию меньше 0:\n");
            count.set(0);
            chatRepository.getUsersFromChat(message.getPeerId()).stream()
                    .filter(s -> s.getReputation() < 0).sorted(Comparator.reverseOrder())
                    .forEach(userChat -> text.append(count.addAndGet(1)).append(". ")
                            .append(User.get(manager, userChat.getUserId()).getFullName().get(0).getPush())
                            .append(" - <<")
                            .append(userChat.getReputation())
                            .append(">> реп.\n"));
        }

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(text.toString())
                .execute();
    }

    private boolean hasBadTop(String... args) {
        return args.length > 0 && args[0].equalsIgnoreCase("-");
    }
}