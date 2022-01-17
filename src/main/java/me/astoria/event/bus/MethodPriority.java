package me.astoria.event.bus;

import java.lang.reflect.Method;

public class MethodPriority {
    public Method method;
    public Integer priority;
    public Class in;

    public MethodPriority(Class in, Method method, Integer priority) {
        this.method = method;
        this.priority = priority;
        this.in = in;
    }
}
