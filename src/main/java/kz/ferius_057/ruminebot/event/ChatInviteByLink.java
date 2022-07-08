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
@EventAnnotation(statuses = MessageActionStatus.CHAT_INVITE_USER_BY_LINK)
public class ChatInviteByLink extends AbstractEvent {

    @Override
    public void run(Message message, Message.Action action) throws VkApiException {
        if (action.getMemberId() < 0) return;

        val memberId = action.getMemberId();
        chatRepository.addUserInPeerId(memberId, message.getPeerId(), User.get(manager, memberId).getFirstName()[0].toString(), 0);
    }
}
