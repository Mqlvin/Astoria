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
Description: A utility class holding functions for chat components.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.4
--------------------------------------------------------------------
*/

package me.astoria.util.chat;

import joptsimple.internal.Strings;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChatUtils {
    public static String removeFormatting(String rawMessage) {
        System.out.println(Instant.now().getEpochSecond());
        ArrayList<String> parsedMessage = new ArrayList<>(Arrays.asList(rawMessage.split("")));
        for(int i = 0; i < parsedMessage.size(); i++) {
            if(parsedMessage.get(i).equals("§")) {
                parsedMessage.remove(i);
                parsedMessage.remove(i);
                i -= 1;
            }
        }
        return Strings.join(parsedMessage, "");
    }
}
