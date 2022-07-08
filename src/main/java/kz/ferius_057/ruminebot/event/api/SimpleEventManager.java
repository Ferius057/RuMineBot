package kz.ferius_057.ruminebot.event.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.event.api.annotation.EventAnnotation;
import kz.ferius_057.ruminebot.util.AccessingAllClassesInPackage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SimpleEventManager implements EventManager {
    Map<String, Event> eventMap;

    @SneakyThrows
    public static EventManager create() {
        val commandManager = new SimpleEventManager(new HashMap<>());
        List<Class<?>> classesInPackage = AccessingAllClassesInPackage.getClassesEvent("kz.ferius_057.ruminebot.event");
        for (val clazz : classesInPackage)
            commandManager.register((Event) clazz.getConstructor().newInstance());

        System.out.printf("Зарегистрировано %d событий.\n", classesInPackage.size());

        return commandManager;
    }

    @Override
    public boolean run(final Message message, final Message.Action action) {
        Event event = eventMap.get(action.getType());
        if (event == null) return false;

        try {
            event.run(message, action);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void register(final Event event) {
        for (val status : event.getClass().getAnnotation(EventAnnotation.class).statuses())
            eventMap.put(status.getValue(), event);
    }
}