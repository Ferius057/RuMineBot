package kz.ferius_057.ruminebot.command;

import kz.ferius_057.ruminebot.Manager;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.exceptions.VkApiException;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;

import java.time.Duration;
import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Uptime extends AbstractCommand {

    public Uptime(Manager Manager) {
        super(Manager, "uptime", "аптайм","бот");
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        Duration duration = Duration.ofMillis(System.currentTimeMillis() - localData.timeStartMs);

        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours() % 24;
        long minutes = duration.minusHours(hours).toMinutes() % 60;
        long seconds = duration.minusMinutes(minutes).getSeconds() % 60;

        StringBuilder time = new StringBuilder("Время работы: ");
        if (days != 0) time.append(days).append(" дн. ");
        if (hours != 0) time.append(hours).append(" ч. ");
        if (minutes != 0) time.append(minutes).append(" мин. ");
        if (seconds != 0) time.append(seconds).append(" сек. ");

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage("✅Бот работает\nВремя запуска: " + localData.getTimeStart() + " GMT+3\n" + time)
                .execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}