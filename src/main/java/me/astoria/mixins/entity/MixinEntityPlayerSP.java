package me.astoria.mixins.entity;

import me.astoria.Astoria;
import me.astoria.event.impl.entity.PlayerChatEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Shadow
    @Final
    public NetHandlerPlayClient sendQueue;

    @Overwrite
    public void sendChatMessage(String message) {
        if(message != null) {
            PlayerChatEvent event = new PlayerChatEvent(message);
            Astoria.EVENT_BUS.call(event);
            if(!event.isCanceled()) {
                this.sendQueue.addToSendQueue(new C01PacketChatMessage(event.getMessage()));
            }
        }
    }
}
