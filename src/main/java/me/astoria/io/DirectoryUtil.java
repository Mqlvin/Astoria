package me.astoria.io;

import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryUtil {
    public static final ArrayList<String> rootDirectories = new ArrayList<>(Arrays.asList("config", "modules", "private"));
    public static final File root = new File(System.getProperty("user.dir") + "/orb");
    public static Boolean safeGenerate(File directory) {
        if(directory.exists()) {
            return true;
        }
        return directory.mkdir();
    }

    public static void generateStructure() {
        Boolean failed = false;
        failed = safeGenerate(root);
        for(String dir : rootDirectories) {
            failed = DirectoryUtil.safeGenerate(new File(root + "/" + dir));
        }
        if(!failed) {
            Logger.report("One or more directories were unable to generate.", Severity.FATAL);
        }


    }
}
