package me.astoria.event.impl.entity;

import me.astoria.event.EventCancelable;

public class PlayerChatEvent extends EventCancelable {
    private String message;

    public PlayerChatEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
