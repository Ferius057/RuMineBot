package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.UptimeTool;

import java.time.Duration;

/**
 * @author Charles_Grozny
 */
public class Uptime extends AbstractCommand {

    public Uptime(VkApi vkApi) {
        super(vkApi, "uptime", "аптайм","бот");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        Duration duration = Duration.ofMillis(System.currentTimeMillis() - UptimeTool.getTimeStartMs());

        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours() % 24;
        long minutes = duration.minusHours(hours).toMinutes() % 60;
        long seconds = duration.minusMinutes(minutes).getSeconds() % 60;

        StringBuilder time = new StringBuilder("Время работы: ");
        if (days != 0) time.append(days).append(" дн. ");
        if (hours != 0) time.append(hours).append(" ч. ");
        if (minutes != 0) time.append(minutes).append(" мин. ");
        if (seconds != 0) time.append(seconds).append(" сек. ");

        vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                .message("✅Бот работает\nВремя запуска: " + UptimeTool.getTimeStart() + " GMT+3\n" + time).execute();
    }
}