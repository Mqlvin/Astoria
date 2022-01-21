package me.astoria.event.impl.client;

import me.astoria.event.Event;

public class KeyPressEvent extends Event {
    private final Boolean isRepeatEvent;
    private final Integer keyCode;

    public KeyPressEvent(Integer keyCode, Boolean isRepeatEvent) {
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
