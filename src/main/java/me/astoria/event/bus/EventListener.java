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
Description: The object used to store listening methods in the event bus.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.3
--------------------------------------------------------------------
*/

package me.astoria.event.bus;

import me.astoria.event.Priority;

import java.lang.reflect.Method;

public class EventListener {
    private Object listener;
    private Method method;
    private Priority priority;

    public EventListener(Object listener, Method method, Priority priority) {
        this.listener = listener;
        this.method = method;
        this.priority = priority;
    }

    public Object getListener() {
        return listener;
    }

    public Method getMethod() {
        return method;
    }

    public Priority getPriority() {
        return priority;
    }
}
