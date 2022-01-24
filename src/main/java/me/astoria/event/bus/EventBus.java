/*
--------------------------------------------------------------------
Copyright (C) 2021-2022 by Mqlvin | Contact: %%license_contact%%
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
--------------------------------------------------------------------
Description: The class containing methods to register and unregister listeners, and holding a hashmap of all listeners.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.3
--------------------------------------------------------------------
*/

package me.astoria.event.bus;

import me.astoria.event.Event;
import me.astoria.event.SubscribeEvent;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.lang.reflect.Method;
import java.util.ArrayList;
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

    public ArrayList<EventListener> getAllListeners() {
        ArrayList<EventListener> eventListenerReturn = new ArrayList<>();
        if(listeners == null || listeners.values().size() == 0) {
            return eventListenerReturn;
        }
        listeners.values().forEach(eventListenerReturn::addAll);
        return eventListenerReturn;
    }
}
