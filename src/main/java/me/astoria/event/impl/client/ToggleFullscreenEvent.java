package me.astoria.event.impl.client;

import me.astoria.event.Event;

public class ToggleFullscreenEvent extends Event {
    private final Boolean isFullscreen;

    public ToggleFullscreenEvent(Boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
    }

    public boolean isNowFullscreen() {
        return isFullscreen;
    }
}
