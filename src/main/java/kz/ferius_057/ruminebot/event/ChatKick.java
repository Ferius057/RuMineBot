package kz.ferius_057.ruminebot.event;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.object.UserChat;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.MessageActionStatus;

/**
 * @author Charles_Grozny
 */
public class ChatKick extends AbstractEvent {

    public ChatKick(final Manager manager) {
        super(manager, MessageActionStatus.CHAT_KICK_USER);
    }

    @Override
    public void run(Message message, Message.Action action) throws VkApiException {
        UserChat userInPeerId = chatRepository.getUserFromChat(action.getMemberId(), message.getPeerId());
        if (userInPeerId == null) {
            chatRepository.addUserInPeerId(action.getMemberId(), message.getPeerId(), User.get(manager, action.getMemberId()).getFirstName()[0].toString(),0);
        }
        chatRepository.updateExist(message.getFromId(), message.getPeerId(),false);
    }
}