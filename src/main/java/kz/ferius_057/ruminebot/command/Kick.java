package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

public class Kick extends Command {
    /*
    Будет сделано позже...
    */

    public Kick() {
        super("kick", "кик","кикни");
    }

    @Override
    public void run(VkApiClient vk, GroupActor actor, Message message, String[] args) throws ClientException, ApiException {
        vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                .message("Кикаю").execute();
    }
}