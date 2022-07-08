package kz.ferius_057.ruminebot.event;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.MessageActionStatus;
import kz.ferius_057.ruminebot.event.api.annotation.EventAnnotation;
import kz.ferius_057.ruminebot.object.User;
import lombok.val;

/**
 * @author Charles_Grozny
 */
@EventAnnotation(statuses = MessageActionStatus.CHAT_KICK_USER)
public class ChatKick extends AbstractEvent {

    @Override
    public void run(Message message, Message.Action action) throws VkApiException {
        System.out.println("123");
        val memberId = action.getMemberId();
        val peerId = message.getPeerId();

        if (chatRepository.getUserFromChat(memberId, peerId) == null)
            chatRepository.addUserInPeerId(memberId, peerId, User.get(manager, memberId).getFirstName()[0].toString(),0);

        chatRepository.updateExist(message.getFromId(), peerId,false);
    }
}