/*
---------------------------------------
* Created: b0.1 - 14/01/2022
* Author(s): [Henry]
* License:
---------------------------------------
*/

package me.astoria.mixins.client;

import me.astoria.Astoria;
import me.astoria.event.impl.client.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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
        Astoria.INSTANCE.initialise();
    }

    @Inject(method = "clickMouse", at = @At("RETURN"))
    public void onLeftClick(CallbackInfo ci) {
        Astoria.EVENT_BUS.call(new LeftClickEvent());
    }

    @Inject(method = "rightClickMouse", at = @At("RETURN"))
    public void onRightClick(CallbackInfo ci) {
        Astoria.EVENT_BUS.call(new RightClickEvent());
    }

    @Inject(method = "toggleFullscreen", at = @At("RETURN"))
    public void onToggleFullscreen(CallbackInfo ci) {
        Astoria.EVENT_BUS.call(new ToggleFullscreenEvent(Minecraft.getMinecraft().isFullScreen()));
    }

    @Inject(method = "dispatchKeypresses", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z"))
    public void onKeyboardTick(CallbackInfo ci) {
        Astoria.EVENT_BUS.call(Keyboard.getEventKeyState() ? new KeyPressEvent(Keyboard.getEventKey(), Keyboard.isRepeatEvent()) : new KeyReleaseEvent(Keyboard.getEventKey(), Keyboard.isRepeatEvent()));
    }
}
