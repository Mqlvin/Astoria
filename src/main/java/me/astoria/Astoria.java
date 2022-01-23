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
import me.astoria.event.SubscribeEvent;
import me.astoria.event.bus.EventBus;
import me.astoria.event.impl.client.ClientInitialisedEvent;
import me.astoria.event.impl.client.KeyPressEvent;
import me.astoria.event.impl.client.KeyReleaseEvent;
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
            Astoria.MODULE_MANAGER.modules.get(0).toggle();
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(Astoria.MODULE_MANAGER.modules.get(0).getEnabled().toString() + " new state."));
        }
    }

    @SubscribeEvent
    public void onKeyDownJ(KeyPressEvent event) {
        if(event.getKeyCode() == Keyboard.KEY_J) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§a§lDumping client info: "));
            EVENT_BUS.getAllListeners().forEach(listener -> Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(listener.getListener().getClass().getName())));
        }
    }
}
