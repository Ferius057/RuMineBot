package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiMessagesChatUserNoAccessException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.responses.GetConversationMembersResponse;
import kz.ferius_057.ruminebot.database.Data;

public class RegisteringConversation extends Command {
    public void register() throws ClientException, ApiException {
        GetConversationMembersResponse membersResponse = null;
        try {
            membersResponse = vk.messages().getConversationMembers(actor, message.getPeerId()).execute();
        } catch (ApiMessagesChatUserNoAccessException e) {
            if (e.getMessage().contains("You don't have access to this chat (917):")) {
                vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                        .message("Не удалось зарегистрировать беседу: Возможно вы мне дали \"Доступ ко всем сообщениям\", для полной работы мне нужно выдать Администратора.").execute();
            return;
            }
        }

        if (Data.peerIds.contains(message.getPeerId())) {
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("Ваша беседа уже зарегестрирована!").execute();
        } else {

        }
    }
}