package me.astoria.io;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.astoria.Astoria;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModuleConfig {
    private JsonObject save;
    private String moduleName;
    private File location;

    public ModuleConfig(String moduleName) {
        this.moduleName = moduleName;
        File moduleDir = DirectoryUtil.getDir("modules");
        if(moduleDir == null) {
            moduleDir = new File(System.getProperty("user.dir") + "/" + Astoria.NAME.toLowerCase());
            Logger.report("Error loading config file for module " + moduleName + ".  Module folder did not exist.", Severity.WARN);
        }
        location = new File(moduleDir + "/" + moduleName);

        if(!location.exists()) {
            save = new JsonParser().parse("{}").getAsJsonObject();
            location.mkdirs();
        } else if(location.exists() && !new File(location.getPath() + "/module-config.json").exists()) {
            save = new JsonParser().parse("{}").getAsJsonObject();
        } else {
            try {
                Logger.log("Successfully loaded config from module " + moduleName + ".");
                save = new JsonParser().parse(String.join(", ", Files.readAllLines(Paths.get(location.getPath() + "/module-config.json")))).getAsJsonObject();
            } catch(Exception e) {
                save = new JsonParser().parse("{}").getAsJsonObject();
                Logger.report("Error reading config file for module " + moduleName + ". Module config was reset to the default values.", Severity.WARN);
            } // todo: you need to set default values here
        }
        save();
    }

    public JsonObject get() {
        return save;
    }

    public void set(JsonObject object) {
        save = object;
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(location + "/module-config.json");
            writer.write(save.toString());
            writer.close();
        } catch(Exception e) {
            Logger.report("Error while trying to save config file for module " + moduleName + ". " + e, Severity.FATAL);
        }
    }
}
