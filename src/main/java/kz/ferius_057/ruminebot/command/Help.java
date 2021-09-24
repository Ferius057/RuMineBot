package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;

/**
 * @author Charles_Grozny
 */
public class Help extends AbstractCommand {

    public Help(VkApi vkApi) {
        super(vkApi, "help", "помощь", "commands", "команды");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                .message("\uD83D\uDCDD Список команд: vk.com/@rumine_coders-help")
                .attachment("article-204964592_46752_e23fdbd46491368d8a").execute();
    }
}