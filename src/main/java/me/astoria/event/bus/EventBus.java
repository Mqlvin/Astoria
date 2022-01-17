package me.astoria.event.bus;

import me.astoria.Astoria;
import me.astoria.event.SubscribeEvent;
import me.astoria.event.impl.Event;
import me.astoria.event.impl.client.OnLoadEvent;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class EventBus {
    private HashMap<Class<? extends Event>, ArrayList<MethodPriority>> listeners = new HashMap<>();

    public void register(Event event, Class listening) {
        ArrayList<MethodPriority> currentMethods;
        if(listeners.containsKey(event.getClass())) {
            currentMethods = listeners.get(event.getClass());
            for(Method method : listening.getMethods()) {
                if(method.isAnnotationPresent(SubscribeEvent.class)) {
                    currentMethods.add(new MethodPriority(listening, method, method.getAnnotation(SubscribeEvent.class).priority().getLevel()));
                }
            }
        } else {
            currentMethods = new ArrayList<>();
            for(Method method : listening.getMethods()) {
                if(method.isAnnotationPresent(SubscribeEvent.class)) {
                    currentMethods.add(new MethodPriority(listening, method, method.getAnnotation(SubscribeEvent.class).priority().getLevel()));
                }
            }
        }
        // sort current mehtods
        listeners.put(event.getClass(), currentMethods);
    }

    public void call(Event event) {
        if(listeners.containsKey(event.getClass())) {
            ArrayList<MethodPriority> methods = listeners.get(event.getClass());
            for(MethodPriority method : methods) {
                try {
                    method.method.invoke(method.in);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Logger.report("Error while invoking method in event bus: " + e.toString(), Severity.FATAL);
                }
            }
        }
    }
}
