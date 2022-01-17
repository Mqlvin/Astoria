/*
---------------------------------------
* Created: b0.1 - 14/01/2022
* Author(s): [Henry]
* License:
---------------------------------------
*/

package me.astoria.mixins.client;

import me.astoria.Astoria;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"), index = 0)
    private String onDisplayCreate(String x) {
        return Astoria.NAME + " Client (" + Astoria.MINECRAFT_VERSION + "/" + Astoria.VERSION + ")";
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStart(CallbackInfo ci) {
        Astoria.initialise();
    }
}
