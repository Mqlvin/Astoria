package me.astoria.mixins.menu;

import me.astoria.Astoria;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class MixinMainMenu {
    @Shadow private String splashText;

    @Inject(method = "initGui", at = @At("RETURN"))
    public void onInitGui(CallbackInfo ci) {
        this.splashText = "HELLOBABYYYYYYY";
    }

    @ModifyArg(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V", ordinal = 0), index = 1)
    private String onDrawScreen(String x) {
        return "Minecraft " + Astoria.NAME + " 1.8.9";
    }
}
