package me.astoria.client.modules.render;

import me.astoria.client.modules.Category;
import me.astoria.client.modules.Module;
import net.minecraft.client.Minecraft;

public class FullbrightModule extends Module {
    public FullbrightModule() {
        super("Fullbright", "Removes shadows from the game.", "1.0.0", Category.RENDER, "Unknown", "", "Mqlvin", "", "fullbright.", true);
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 50.0f;
    }

    @Override
    public void disable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.5f;
    }
}
