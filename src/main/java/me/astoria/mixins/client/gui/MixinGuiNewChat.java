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
Description: Holds injects for the <net.minecraft.client.gui.GuiNewChat> class.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.4
--------------------------------------------------------------------
*/

package me.astoria.mixins.client.gui;

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
