package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.ExceptRegistered;

import java.time.Duration;
import java.util.List;

/**
 * @author Charles_Grozny
 */
@ExceptRegistered
@CommandAnnotation(aliases = { "uptime", "аптайм", "бот" })
public class Uptime extends AbstractCommand {

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
}