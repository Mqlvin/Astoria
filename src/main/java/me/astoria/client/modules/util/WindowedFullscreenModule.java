package me.astoria.client.modules.util;

import me.astoria.Astoria;
import me.astoria.client.modules.Category;
import me.astoria.client.modules.Module;
import me.astoria.client.modules.ModuleManager;
import me.astoria.event.SubscribeEvent;
import me.astoria.event.impl.client.ToggleFullscreenEvent;
import me.astoria.log.Severity;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.awt.*;

public class WindowedFullscreenModule extends Module {
    public WindowedFullscreenModule() {
        super("WindowedFullscreen", "Allows support for multiple monitors with Minecraft.", "1.0.0", Category.UTIL, "hancin", "https://www.curseforge.com/members/hancin", "Mqlvin", "", "windowedfullscreen.", false);
    }

    @SubscribeEvent
    public void toggleFullscreen(ToggleFullscreenEvent event) { // todo: make this event only run if enabled
        try {
            if(event.isNowFullscreen()) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
                Display.setDisplayMode(Display.getDesktopDisplayMode());
                Display.setLocation(0, 0);
                Display.setFullscreen(false);
                Display.setResizable(false);
            } else {
                Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
                Display.setLocation(screenDimensions.width / 2 - (Minecraft.getMinecraft().displayWidth / 2), screenDimensions.height / 2 - (Minecraft.getMinecraft().displayHeight / 2));
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
                Display.setDisplayMode(new DisplayMode(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight));
                Display.setResizable(true);
            }
        } catch(Exception e) {
            ModuleManager.report(this, e.toString(), Severity.WARN);
        }
    }

    @Override
    public void enable() {
        Astoria.EVENT_BUS.register(this);
    }

    @Override
    public void disable() {
        Astoria.EVENT_BUS.unregister(this);
    }
}
