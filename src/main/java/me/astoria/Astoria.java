/*
---------------------------------------
* Created: b0.1 - 14/01/2022
* Author(s): [Henry]
* License:
---------------------------------------
*/

package me.astoria;

import me.astoria.event.bus.EventBus;
import me.astoria.event.impl.client.OnLoadEvent;
import me.astoria.io.DirectoryUtil;
import me.astoria.log.Logger;
import me.astoria.util.wrapper.HypixelAPI;
import net.minecraft.client.Minecraft;

public class Astoria {
    public static final String NAME = "Astoria";
    public static final String VERSION = "b0.1";
    public static final String MINECRAFT_VERSION = "1.8.9";

    private static final Astoria INSTANCE = new Astoria();

    private static EventBus EVENT_BUS = new EventBus();

    public Astoria() {
        Logger.log("Initialising...");
        DirectoryUtil.generateStructure();
    }

    public static void initialise() {
        Logger.log("Client successfully initialised. " + NAME + " (" + MINECRAFT_VERSION + "/" + VERSION + ")");
        HypixelAPI api = new HypixelAPI("d8e7f0b1-6dcf-4a96-993c-8f11cf2fe15b");
    }


    public Astoria INSTANCE() {
        return INSTANCE;
    }
}
