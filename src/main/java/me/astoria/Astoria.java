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
Description: Main class called at startup, holds instances for the client, handles initialisation and shutdown of client.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.3
--------------------------------------------------------------------
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
import me.astoria.event.impl.entity.ChatReceivedEvent;
import me.astoria.event.impl.entity.PlayerChatEvent;
import me.astoria.io.DirectoryUtil;
import me.astoria.io.ModuleConfig;
import me.astoria.log.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;

import java.security.Key;

public class Astoria {
    public static final String NAME = "Astoria";
    public static final String VERSION = "b0.4";
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

        ModuleConfig moduleConfig = new ModuleConfig("test");

        Astoria.EVENT_BUS.call(new ClientInitialisedEvent());
    }

    public void shutdown() {
        Logger.log("Client beginning shutdown.");
        MODULE_MANAGER.saveAll();
        EVENT_BUS.unregister(this);
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

    @SubscribeEvent
    public void onKeyDownZoom(KeyPressEvent event) {
        if(event.getKeyCode() == Keyboard.KEY_R) {
            Minecraft.getMinecraft().gameSettings.fovSetting = 20.0f;
            Minecraft.getMinecraft().gameSettings.smoothCamera = true;
        }
    }

    @SubscribeEvent
    public void onKeyUpZoom(KeyReleaseEvent event) {
        if(event.getKeyCode() == Keyboard.KEY_R) {
            Minecraft.getMinecraft().gameSettings.fovSetting = 85.0f;
            Minecraft.getMinecraft().gameSettings.smoothCamera = false;
        }
    }

    @SubscribeEvent
    public void onChatMessageSent(PlayerChatEvent event) {
        if(event.getMessage().startsWith("--")) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§a§lDumping client info: "));
        } else if(event.getMessage().startsWith("abc")) {
            event.setMessage("123");
        } else {
            event.setMessage(event.getMessage().replace(":)", "☻"));
        }
    }

    @SubscribeEvent
    public void onChatMessageReceived(ChatReceivedEvent event) {
        if(event.getMessage().getUnformattedText().startsWith("§a§lDumping client")) {
            event.setMessage(new ChatComponentText("WORKING BY THAE WAY WOW"));
        } else if(event.getMessage().getUnformattedText().contains("123")) {
            event.cancel();
        }
    }
}
