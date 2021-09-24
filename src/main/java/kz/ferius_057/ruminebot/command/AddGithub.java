package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;

/**
 * @author Charles_Grozny
 */
public class AddGithub extends AbstractCommand {

    public AddGithub(VkApi vkApi) {
        super(vkApi, "github", "гитхаб");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                .message("❌ Данная команда находится в разработке.").execute();
    }
}