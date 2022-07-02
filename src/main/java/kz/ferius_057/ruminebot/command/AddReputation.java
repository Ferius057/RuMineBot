package kz.ferius_057.ruminebot.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;

import java.util.List;

/**
 * @author Charles_Grozny
 */
public class AddReputation extends AbstractCommand {

    public AddReputation(Manager Manager) {
        super(Manager, "+reputation", "+rep", "+репутация", "+реп");
    }

    @Override
    public void run(User userSender, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        Message replyMessage = replyMessages.get(0);

        int peerId = message.getPeerId();

        UserChat user = chatRepository.getUserFromChat(replyMessage.getFromId(), peerId);
        UserChat sender = chatRepository.getUserFromChat(message.getFromId(), peerId);

        User userData = User.get(manager, replyMessage.getFromId());


        if (user == null || !user.isExist()) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ [id" + replyMessage.getFromId() + "|" + userData.getFirstName()[0] + " " + userData.getLastName()[0] + "] отсутствует в этой беседе.")
                    .execute();
            return;
        }

        if (user.getUserId() == sender.getUserId()) {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❌ Выдавать репутацию самому себе запрещено.").execute();
            return;
        }
        // Проверка есть ли бан репутации у того кто даёт репутацию
        if (!sender.isBanrep()) {
            chatRepository.giveReputation(replyMessage.getFromId(), peerId, user.getReputation() + 1);
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("⚡ [id" + replyMessage.getFromId() + "|" +
                            userData.getFirstName()[0] + " " + userData.getLastName()[0] +
                            "] получил +1 к репутации.")
                    .execute();
        } else {
            vk.messages.send()
                    .setPeerId(peerId)
                    .setDisableMentions(true)
                    .setMessage("❗ [id" + message.getFromId() + "|" +
                            sender.getNickname() + "], вы не можете выдавать репутацию, так как у вас бан репутации.")
                    .execute();
        }
    }
}