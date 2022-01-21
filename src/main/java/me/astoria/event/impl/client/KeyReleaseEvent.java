package me.astoria.event.impl.client;

import me.astoria.event.Event;

public class KeyReleaseEvent extends Event {
    private final Boolean isRepeatEvent;
    private final Integer keyCode;

    public KeyReleaseEvent(Integer keyCode, Boolean isRepeatEvent) {
        this.isRepeatEvent = isRepeatEvent;
        this.keyCode = keyCode;
    }

    public Boolean isRepeatEvent() {
        return this.isRepeatEvent;
    }

    public Integer getKeyCode() {
        return keyCode;
    }
}
