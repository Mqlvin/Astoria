package me.astoria.client.modules;

import me.astoria.client.modules.render.FullbrightModule;
import me.astoria.client.modules.util.WindowedFullscreenModule;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {
    public CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();

    public static void report(Module module, String error, Severity severity) {
        Logger.report("[" + module.getName() + "-" + module.getVersion() + "] " + error, severity);
    }

    public ModuleManager() {
        modules.add(new WindowedFullscreenModule());
        modules.add(new FullbrightModule());
        modules.get(0).enable();
        modules.get(1).enable();
        System.out.println("Module manager loaded with " + modules.size() + " module.");
    }
}
