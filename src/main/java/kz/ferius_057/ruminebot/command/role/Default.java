package kz.ferius_057.ruminebot.command.role;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiParamUserIdException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ForeignMessage;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

/**
 * @author Charles_Grozny
 */
public class Default extends AbstractCommand {

    public Default(VkApi vkApi) {
        super(vkApi, "default", "участник");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        int peerId = message.getPeerId();

        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);

        if (sender.getRole() < 1) {
            vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                    .message("❗ [id" + message.getFromId() + "|" + sender.getNickname() + "], у вас недостаточно прав для данной команды.").execute();
        } else {
            ForeignMessage replyMessage = getForeignMessage(message);
            if (replyMessage != null) {
                User user;
                try {
                    user = User.user(vkApi, replyMessage.getFromId().toString());
                } catch (ApiParamUserIdException e) {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❌ Не удалось получить пользователя | " + e.getMessage()).execute();
                    return;
                }

                UserChat userInPeerId = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);

                if (userInPeerId == null) {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❌ [id" + user.getUserId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] отсутствует в этой беседе.").execute();
                    return;
                }

                if (userInPeerId.getRole() == 0) {
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("❗ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] уже имеет роль участника.").execute();
                } else {
                    chatRepository.updateRole(replyMessage.getFromId(), peerId, 0);
                    vk.messages().send(actor).randomId(0).peerId(peerId).disableMentions(true)
                            .message("✅ [id" + replyMessage.getFromId() + "|" + user.getFirstName()[0] + " " + user.getLastName()[0] + "] теперь участник.").execute();
                }
            }
        }
    }
}