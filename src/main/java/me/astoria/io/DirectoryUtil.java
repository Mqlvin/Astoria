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
Description: Creates and handles directories used by the client.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.2
--------------------------------------------------------------------
*/

package me.astoria.io;

import me.astoria.Astoria;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryUtil {
    public static final ArrayList<String> rootDirectories = new ArrayList<>(Arrays.asList("config", "modules", "private"));
    public static final File root = new File(System.getProperty("user.dir") + "/" + Astoria.NAME.toLowerCase());
    public static Boolean safeGenerate(File directory) {
        if(directory.exists()) {
            return true;
        }
        return directory.mkdir();
    }

    public static void generateStructure() {
        Boolean failed;
        failed = safeGenerate(root);
        for(String dir : rootDirectories) {
            failed = DirectoryUtil.safeGenerate(new File(root + "/" + dir));
        }
        if(!failed) {
            Logger.report("One or more directories were unable to generate.", Severity.FATAL);
        }
    }

    public static File getDir(String name) {
        for(String dir : rootDirectories) {
            if(dir.equalsIgnoreCase(name)) {
                return new File(root + "/" + dir);
            }
        }
        return null;
    }
}
