package kz.ferius_057.ruminebot.event;

import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.event.api.impl.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.model.media.MessageActionStatus;
import kz.ferius_057.ruminebot.event.api.annotation.EventAnnotation;
import lombok.val;

/**
 * @author Charles_Grozny
 */
@EventAnnotation(statuses = MessageActionStatus.CHAT_INVITE_USER)
public class ChatInvite extends AbstractEvent {

    @Override
    public void run(Message message, Message.Action action) {
        if (action.getMemberId() < 0) return;

        val memberId = action.getMemberId();
        val peerId = message.getPeerId();

        if (chatRepository.getUserFromChat(memberId, peerId) != null)
            chatRepository.updateExist(message.getFromId(), peerId, true);
        else
            chatRepository.addUserInPeerId(memberId, peerId, 0);
    }

}
