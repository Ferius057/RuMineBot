package kz.ferius_057.ruminebot.event;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.Manager;
import kz.ferius_057.ruminebot.database.tool.User;
import kz.ferius_057.ruminebot.event.api.AbstractEvent;
import kz.ferius_057.ruminebot.event.api.MessageActionStatus;

/**
 * @author Charles_Grozny
 */
public class ChatInviteByLink extends AbstractEvent {

    public ChatInviteByLink(final Manager manager) {
        super(manager, MessageActionStatus.CHAT_INVITE_USER_BY_LINK);
    }

    @Override
    public void run(Message message, Message.Action action) throws VkApiException {
        System.out.println(action);
        if (action.getMemberId() >= 0) {
            User user = User.get(manager, action.getMemberId());
            System.out.println(user.getUserId());
            System.out.println(user.getFirstName()[0]);
            chatRepository.addUserInPeerId(action.getMemberId(), message.getPeerId(), user.getFirstName()[0].toString(), 0);
        }
    }
}
