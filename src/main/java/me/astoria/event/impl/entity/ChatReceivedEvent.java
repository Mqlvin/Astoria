package me.astoria.event.impl.entity;

import me.astoria.event.EventCancelable;
import net.minecraft.util.IChatComponent;

public class ChatReceivedEvent extends EventCancelable {
    private IChatComponent message;

    public ChatReceivedEvent(IChatComponent message) {
        this.message = message;
    }

    public IChatComponent getMessage() {
        return message;
    }

    public void setMessage(IChatComponent message) {
        this.message = message;
    }
}
