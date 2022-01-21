package me.astoria.event.bus;

import me.astoria.event.Event;
import me.astoria.event.SubscribeEvent;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private HashMap<Class<? extends Event>, CopyOnWriteArrayList<EventListener>> listeners = new HashMap<>();

    public void register(Object listener) {
        for(Method method : listener.getClass().getDeclaredMethods()) {
            if(!method.isAnnotationPresent(SubscribeEvent.class)) continue;
            if(method.getParameterCount() == 0) continue;
            SubscribeEvent subscribeEvent = method.getAnnotation(SubscribeEvent.class);

            Class<? extends Event> event;
            try {
                event = (Class<? extends Event>) method.getParameters()[0].getType();
            } catch(Exception e) {
                Logger.report("Method " + method.getName() + " was annotated as an event, however it had no or an invalid event parameter.", Severity.WARN);
                continue;
            }
            if(!listeners.containsKey(event)) {
                listeners.put(event, new CopyOnWriteArrayList<>());
            }
            listeners.get(event).add(new EventListener(listener, method, subscribeEvent.priority()));
            listeners.get(event).sort(Comparator.comparingInt(listen -> listen.getPriority().getLevel()));
        }
    }

    public void unregister(Object listener_) {
        listeners.values().forEach(arr -> arr.removeIf(listener -> listener.getListener() == listener_));
    }

    public void call(Event event) {
        if(listeners.containsKey(event.getClass())) {
            listeners.getOrDefault(event.getClass(), new CopyOnWriteArrayList<>()).forEach(method -> {
                try {
                    method.getMethod().invoke(method.getListener(), event);
                } catch(Exception e) {
                    Logger.report("Unable to execute method from class " + method.getListener().getClass().getName() + " during event system invocation. " + e, Severity.FATAL);
                }
            });
        }
    }
}
