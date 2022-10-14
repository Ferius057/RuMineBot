package kz.ferius_057.ruminebot.event;

import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.event.api.impl.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.model.media.MessageActionStatus;
import kz.ferius_057.ruminebot.event.api.annotation.EventAnnotation;
import lombok.val;

/**
 * @author Charles_Grozny
 */
@EventAnnotation(statuses = MessageActionStatus.CHAT_INVITE_USER_BY_LINK)
public class ChatInviteByLink extends AbstractEvent {

    @Override
    public void run(Message message, Message.Action action) {
        if (action.getMemberId() < 0) return;

        val memberId = action.getMemberId();
        chatRepository.addUserInPeerId(memberId, message.getPeerId(), 0);
    }
}
