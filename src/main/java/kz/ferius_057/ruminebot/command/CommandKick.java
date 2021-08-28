package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;

public final class CommandKick extends AbstractCommand {
    /*
    Будет сделано позже...
    */

    public CommandKick() {
        super("kick", "кик","кикни");
    }

    @Override
    public void run(final VkApi vkApi, final Message message, final String[] args) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();

        vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                .message("Кикаю").execute();
    }
}