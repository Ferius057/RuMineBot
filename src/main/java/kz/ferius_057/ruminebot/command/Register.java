package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiMessagesChatUserNoAccessException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetConversationMembersResponse;
import com.vk.api.sdk.objects.users.Fields;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.ArrayList;

public final class Register extends AbstractCommand {
    public Register(VkApi vkApi) {
        super(vkApi,"reg", "register");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        int peerId = message.getPeerId();

        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);
        if (sender.getRole() < 1) {
            vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                    .message("❗ [id" + peerId + "|" + sender.getNickname() + "], у вас недостаточно прав для данной команды.").execute();
        } else {
            GetConversationMembersResponse membersResponse;

            try {
                membersResponse = vk.messages().getConversationMembers(actor, peerId).fields(Fields.FIRST_NAME_NOM).execute();
            } catch (ApiMessagesChatUserNoAccessException e) {
                if (e.getMessage().contains("You don't have access to this chat (917):")) {
                    vk.messages().send(actor).randomId(0).peerId(peerId)
                            .message("Не удалось зарегистрировать беседу: Возможно вы мне дали \"Доступ ко всем сообщениям\", для полной работы мне нужно выдать Администратора.").execute();
                }
                return;
            }

            if (vkApi.getPeerIds().add(peerId)) {
                long start = System.currentTimeMillis();

                ArrayList<String> users = new ArrayList<>();
                ArrayList<String> admins = new ArrayList<>();

                membersResponse.getItems().forEach(s -> {
                    if (s.getIsAdmin() != null) admins.add(s.getMemberId().toString());
                });

                membersResponse.getProfiles().forEach(s -> {
                    users.add(s.getId().toString());
                    if (admins.contains(s.getId().toString())) {
                        chatRepository.addUserInPeerId(s.getId(), peerId, s.getFirstName(), 1);
                    } else {
                        chatRepository.addUserInPeerId(s.getId(), peerId, s.getFirstName(), 0);
                    }
                });

                for (int i = 0; i < users.size(); i++) {
                    if (!vkApi.getUsers().add(Integer.valueOf(users.get(i)))) {
                        users.remove(users.get(i));
                        i--;
                    }
                }

                //CompletableFuture.runAsync(() -> {
                try {
                    if (users.size() > 0) User.registrationUserInTheBot(vkApi, users.toArray(new String[0]));
                } catch (ClientException | ApiException e) {
                    e.printStackTrace();
                }
                //});

                chatRepository.createChat(peerId);

                vk.messages().send(actor).randomId(0).peerId(peerId)
                        .message("Ваша беседа успешно зарегистрирована.\nОбработал команду за " + (System.currentTimeMillis() - start) + "ms.").execute();
            } else {
                vk.messages().send(actor).randomId(0).peerId(peerId)
                        .message("Ваша беседа уже зарегистрирована!").execute();
            }
        }
    }
}