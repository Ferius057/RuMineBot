package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;

/**
 * @author Charles_Grozny
 */
public class Info extends AbstractCommand {

    /*
    Будет сделано позже...
    */

    public Info() {
        super("инфо", "инфа");
    }

    @Override
    public void run(VkApi vkApi, Message message, String[] args) throws ClientException, ApiException {
        VkApiClient vk = vkApi.getClient();
        GroupActor actor = vkApi.getActor();


    }
}