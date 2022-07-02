package kz.ferius_057.ruminebot.event;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.database.tool.UserChat;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.MessageActionStatus;

/**
 * @author Charles_Grozny
 */
public class ChatInvite extends AbstractEvent {

    public ChatInvite(final Manager manager) {
        super(manager, MessageActionStatus.CHAT_INVITE_USER);
    }

    @Override
    public void run(Message message, Message.Action action) throws VkApiException {
        if (action.getMemberId() >= 0) {
            UserChat userInPeerId = chatRepository.getUserFromChat(action.getMemberId(), message.getPeerId());
            if (userInPeerId != null) {

                chatRepository.updateExist(message.getFromId(), message.getPeerId(), true);
            } else {
                User user = User.get(manager, action.getMemberId());

                chatRepository.addUserInPeerId(action.getMemberId(), message.getPeerId(), user.getFirstName()[0].toString(),0);
            }
        }
    }
}
