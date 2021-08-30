package kz.ferius_057.ruminebot.event.api;

import com.vk.api.sdk.objects.messages.MessageActionStatus;

public abstract class AbstractEvent implements Event {

    protected final MessageActionStatus eventName;

    protected AbstractEvent(final MessageActionStatus eventName) {
        this.eventName = eventName;
    }

    @Override
    public MessageActionStatus getEventName() {
        return eventName;
    }
}