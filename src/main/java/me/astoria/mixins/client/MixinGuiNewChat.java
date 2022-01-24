package me.astoria.mixins.client;

import me.astoria.Astoria;
import me.astoria.event.impl.entity.ChatReceivedEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat {
    @Shadow protected abstract void setChatLine(IChatComponent chatComponent, int chatLineId, int p_146237_3_, boolean p_146237_4_);
    @Shadow @Final private static Logger logger;

    @Overwrite
    public void printChatMessageWithOptionalDeletion(IChatComponent chatComponent, int chatLineId) {
        if(chatComponent != null) {
            ChatReceivedEvent event = new ChatReceivedEvent(chatComponent);
            Astoria.EVENT_BUS.call(event);
            if(!event.isCanceled()) {
                this.setChatLine(event.getMessage(), chatLineId, Minecraft.getMinecraft().ingameGUI.getUpdateCounter(), false);
                logger.info("[CHAT] " + event.getMessage().getUnformattedText());
            }
        }
    }
}
