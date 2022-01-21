/*
---------------------------------------
* Created: b0.1 - 14/01/2022
* Author(s): [Henry]
* License:
---------------------------------------
*/

package me.astoria;

import me.astoria.api.hypixel.HypixelAPI;
import me.astoria.api.hypixel.punishment.PunishmentInfo;
import me.astoria.client.modules.ModuleManager;
import me.astoria.event.Priority;
import me.astoria.event.SubscribeEvent;
import me.astoria.event.bus.EventBus;
import me.astoria.event.impl.client.*;
import me.astoria.io.DirectoryUtil;
import me.astoria.log.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;

public class Astoria {
    public static final String NAME = "Astoria";
    public static final String VERSION = "b0.3";
    public static final String MINECRAFT_VERSION = "1.8.9";

    public static final Astoria INSTANCE = new Astoria();

    public static EventBus EVENT_BUS = new EventBus();
    public static ModuleManager MODULE_MANAGER = new ModuleManager();
    private static HypixelAPI HYPIXEL_API;

    public Astoria() {
        Logger.log("Initialising...");
        DirectoryUtil.generateStructure();
    }

    public void initialise() {
        EVENT_BUS.register(this);
        Logger.log("Event bus has been initialised.");
        HypixelAPI api = new HypixelAPI("", false);
        System.out.println(api.getKey() + " " + api.getKeyStatus());

        PunishmentInfo punishmentInfo = new PunishmentInfo();
        System.out.println(punishmentInfo.getTotalBans());


        Astoria.EVENT_BUS.call(new ClientInitialisedEvent());
    }

    @SubscribeEvent
    public void onLoad(ClientInitialisedEvent event) {
        Logger.log("Client successfully initialised. " + NAME + " (" + MINECRAFT_VERSION + "/" + VERSION + ")");
    }

    @SubscribeEvent
    public void onKeyDown(KeyPressEvent event) {
        if(event.getKeyCode() == Keyboard.KEY_RSHIFT) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Open GUI."));
        }
    }

    @SubscribeEvent
    public void onKeyUp(KeyReleaseEvent event) {
        if(event.getKeyCode() == Keyboard.KEY_RSHIFT) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("YOU WANT TO CLOSE SO MUCH L OL LMASOFMOFOA"));
        }
    }

    @SubscribeEvent
    public void onKeyDownD(KeyPressEvent event) {
        if(event.getKeyCode() == Keyboard.KEY_RSHIFT && event.isRepeatEvent()) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("YOU STOP SPAMMING NOW!!!!!!!!"));
        }
    }

    @SubscribeEvent
    public void noParamEventLOL() {
        System.out.println("do this");
    }
}
