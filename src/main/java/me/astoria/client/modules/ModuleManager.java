package me.astoria.client.modules;

import me.astoria.client.modules.util.WindowedFullscreen;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {
    CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();

    public static void report(Module module, String error, Severity severity) {
        Logger.report("[" + module.getName() + "-" + module.getVersion() + "] " + error, severity);
    }

    public ModuleManager() {
        modules.add(new WindowedFullscreen());
        modules.get(0).enable();
        System.out.println("Module manager loaded with " + 1 + " module.");
    }
}
