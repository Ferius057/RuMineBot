package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiMessagesChatUserNoAccessException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ConversationMember;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetConversationMembersResponse;
import com.vk.api.sdk.objects.users.Fields;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.database.ChatDao;

import java.util.List;

public final class CommandRegister extends AbstractCommand {
    public CommandRegister() {
        super("reg", "register","test");
    }

    @Override
    public void run(VkApi vkApi, Message message, String[] args) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();

        GetConversationMembersResponse membersResponse;

        try {
            membersResponse = vk.messages().getConversationMembers(actor, message.getPeerId()).execute();
        } catch (ApiMessagesChatUserNoAccessException e) {
            if (e.getMessage().contains("You don't have access to this chat (917):")) {
                vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                        .message("Не удалось зарегистрировать беседу: Возможно вы мне дали \"Доступ ко всем сообщениям\", для полной работы мне нужно выдать Администратора.").execute();
            }
            return;
        }

        if (vkApi.getPeerIds().add(message.getPeerId())) {
            int countAdmins = 0;
            System.out.println(membersResponse.getItems());
            for (int i = 0; i < membersResponse.getItems().size(); i++) {
                System.out.println(membersResponse.getItems().get(i));
                if (membersResponse.getItems().get(i).getIsAdmin() != null) countAdmins++;
            }
            System.out.println(countAdmins);
            System.out.println(vkApi.getPeerIds());
            vkApi.getChatDao().addPeerId(message.getPeerId(), countAdmins, membersResponse.getCount());
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("Ваша беседа успешно зарегистрирована.").execute();

        } else {
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("Ваша беседа уже зарегистрирована!").execute();
        }
    }
}