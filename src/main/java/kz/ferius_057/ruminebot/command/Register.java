package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiMessagesChatUserNoAccessException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetConversationMembersResponse;
import com.vk.api.sdk.objects.users.Fields;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;

import java.util.ArrayList;
import java.util.List;

public final class Register extends AbstractCommand {
    public Register() {
        super("reg", "register","test");
    }

    @Override
    public void run(VkApi vkApi, Message message, String[] args) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();

        GetConversationMembersResponse membersResponse;

        try {
            membersResponse = vk.messages().getConversationMembers(actor, message.getPeerId()).fields(Fields.FIRST_NAME_NOM).execute();
        } catch (ApiMessagesChatUserNoAccessException e) {
            if (e.getMessage().contains("You don't have access to this chat (917):")) {
                vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                        .message("Не удалось зарегистрировать беседу: Возможно вы мне дали \"Доступ ко всем сообщениям\", для полной работы мне нужно выдать Администратора.").execute();
            }
            return;
        }

        if (vkApi.getPeerIds().add(message.getPeerId())) {
            int countAdmins = (int) membersResponse.getItems().stream().filter(s -> s.getIsAdmin() != null).count();
            List<Integer> admins = new ArrayList<>();

            membersResponse.getItems().forEach(s -> {
                if (s.getIsAdmin() != null) admins.add(s.getMemberId());
            });

            membersResponse.getProfiles().forEach(s -> {
                vkApi.getChatDao().registrationUserInTheBot(
                        s.getId(),
                        s.getFirstName(),
                        s.getLastName());
                if (admins.contains(s.getId())) {
                    vkApi.getChatDao().addUserInPeerId(message.getPeerId() + "_" + s.getId(), s.getFirstName(), "1", 0);
                } else {
                    vkApi.getChatDao().addUserInPeerId(message.getPeerId() + "_" + s.getId(), s.getFirstName(), "0", 0);
                }
            });

            vkApi.getChatDao().addPeerId(message.getPeerId(), countAdmins, membersResponse.getCount());
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("Ваша беседа успешно зарегистрирована.").execute();
        } else {
            vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                    .message("Ваша беседа уже зарегистрирована!").execute();
        }
    }
}