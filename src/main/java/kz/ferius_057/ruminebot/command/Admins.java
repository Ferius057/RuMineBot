package kz.ferius_057.ruminebot.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import kz.ferius_057.ruminebot.VkApi;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.Collections;
import java.util.List;

/**
 * @author Charles_Grozny
 */
public class Admins extends AbstractCommand {

    public Admins(VkApi vkApi) {
        super(vkApi, "admins", "админы");
    }

    @Override
    public void run(Message message, String[] args) throws ClientException, ApiException {
        List<UserChat> admins = chatRepository.getAdminsFromChat(message.getPeerId());
        StringBuilder sb = new StringBuilder("✅ Список администраторов:\n");
        for (int i = 0; i < admins.size(); i++) {
            User user = User.user(vkApi, String.valueOf(admins.get(i).getUserId()));
            sb.append(i+1).append(". ").append(user.getFirstName()[0]).append(" ").append(user.getLastName()[0]).append(".\n");
        }
        vk.messages().send(actor).randomId(0).peerId(message.getPeerId()).disableMentions(true)
                .message(sb.toString()).execute();
    }
}