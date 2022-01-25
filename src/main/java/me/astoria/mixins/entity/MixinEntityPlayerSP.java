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
Description: Holds injects for the <net.minecraft.client.entity.EntityPlayerSP> class.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.4
--------------------------------------------------------------------
*/

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
