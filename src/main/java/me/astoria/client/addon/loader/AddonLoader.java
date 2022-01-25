package me.astoria.client.addon.loader;

import me.astoria.io.DirectoryUtil;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

public class AddonLoader {
    public static void load(String name) {
        try {
            JarFile addonFile = new JarFile(new File(DirectoryUtil.getDir("modules") + "/" + name + ".jar"));
            
        } catch(IOException e) {
            Logger.report("Error while loading module " + name + ". " + e, Severity.FATAL);
        }
    }
}
