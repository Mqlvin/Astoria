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
Description: The class which holds all modules at runtime.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.4
--------------------------------------------------------------------
*/

package me.astoria.client.modules;

import me.astoria.client.modules.movement.ToggleSprintModule;
import me.astoria.client.modules.render.FullbrightModule;
import me.astoria.client.modules.util.WindowedFullscreenModule;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {
    public CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();

    public void report(Module module, String error, Severity severity) {
        Logger.report("[" + module.getName() + "-" + module.getVersion() + "] " + error, severity);
    }

    public ModuleManager() {
        modules.add(new WindowedFullscreenModule());
        modules.add(new FullbrightModule());
        modules.add(new ToggleSprintModule());
        modules.get(0).enable();
        modules.get(1).enable();
        modules.get(2).enable();
        System.out.println("Module manager loaded with " + modules.size() + " module.");
    }

    public void saveAll() {
        this.modules.forEach(Module::shutdown);
    }
}
