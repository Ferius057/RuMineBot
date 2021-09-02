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
import kz.ferius_057.ruminebot.command.tool.User;
import kz.ferius_057.ruminebot.command.tool.UserInPeerId;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Charles_Grozny
 */
public class Resync extends AbstractCommand {

    public Resync(VkApi vkApi) {
        super(vkApi,"resync", "обновить", "update");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        GroupActor actor = vkApi.getActor();
        VkApiClient vk = vkApi.getClient();

        GetConversationMembersResponse membersResponse;

        try {
            membersResponse = vk.messages().getConversationMembers(actor, message.getPeerId()).fields(Fields.FIRST_NAME_NOM).execute();
        } catch (ApiMessagesChatUserNoAccessException e) {
            if (e.getMessage().contains("You don't have access to this chat (917):")) {
                vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                        .message("Не удалось обновить беседу: Возможно вы мне дали \"Доступ ко всем сообщениям\", для полной работы мне нужно выдать Администратора.").execute();
            }
            return;
        }

        ArrayList<String> users = new ArrayList<>();
        List<Integer> admins = new ArrayList<>();

        membersResponse.getItems().forEach(s -> {
            if (s.getIsAdmin() != null) admins.add(s.getMemberId());
        });

        membersResponse.getProfiles().forEach(s -> {
            users.add(s.getId().toString());
            UserInPeerId userInPeerId = vkApi.getChatDao().getUserInPeerId(message.getPeerId() + "_" + s.getId());
            if (admins.contains(s.getId())) {
                if (userInPeerId == null) vkApi.getChatDao().addUserInPeerId(message.getPeerId() + "_" + s.getId(), s.getFirstName(), "1");
                else vkApi.getChatDao().updatePeerId(message.getPeerId() + "_" + s.getId(), s.getFirstName(), "1", 1);
            } else {
                if (userInPeerId == null) vkApi.getChatDao().addUserInPeerId(message.getPeerId() + "_" + s.getId(), s.getFirstName(), "0");
                else vkApi.getChatDao().updatePeerId(message.getPeerId() + "_" + s.getId(), s.getFirstName(), "0", 1);
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                User.registrationUserInTheBot(vkApi, users.toArray(new String[0]));
            } catch (ClientException | ApiException e) {
                e.printStackTrace();
            }
        });

        vk.messages().send(actor).randomId(0).peerId(message.getPeerId())
                .message("Данные беседы были обновлены.").execute();
    }
}