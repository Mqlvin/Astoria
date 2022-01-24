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
Description: Holds injects for the <net.minecraft.client.Minecraft> class.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.3
--------------------------------------------------------------------
*/

package me.astoria.mixins.client;

import me.astoria.Astoria;
import me.astoria.event.impl.client.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
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
