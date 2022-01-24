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
Description: FullbrightModule for the client. A further description of the module can be found in the constructor.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.4
--------------------------------------------------------------------
*/

package me.astoria.client.modules.render;

import me.astoria.client.modules.Category;
import me.astoria.client.modules.Module;
import net.minecraft.client.Minecraft;

public class FullbrightModule extends Module {
    public FullbrightModule() {
        super("Fullbright", "Used to see in very dark places.", "1.0.0", Category.RENDER, "Unknown", "", "Mqlvin", "", "fullbright.", true);
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 50.0f;
    }

    @Override
    public void disable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.5f;
    }

    @Override
    public void shutdown() {

    }
}
