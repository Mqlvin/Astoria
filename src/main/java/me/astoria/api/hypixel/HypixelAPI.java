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
Description: Used as an API key across the whole client. Makes sure API key is valid and correctly owned before use.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.2
--------------------------------------------------------------------
*/

package me.astoria.api.hypixel;

import me.astoria.util.http.HttpClient;
import me.astoria.util.http.HttpResponse;

public class HypixelAPI {
    private String key;
    private Integer keyStatus = 0;
    /*
    keyStatus codes:
    0: Undetermined
    1: Valid
    2: Invalid
    3: Valid but not owned by UUID TODO
    4: Other
     */

    public HypixelAPI(String key, Boolean overrideAuth) {
        this.key = key;
        if(overrideAuth) {
            keyStatus = 1;
        } else {
            HttpResponse response = HttpClient.request("https://api.hypixel.net/key?key=" + key);
            if(response.response.startsWith("java.io.IOException: ")) {
                keyStatus = 2;
            } else if(response.response.startsWith("{") && response.response.contains("record")) { // todo: check key owner vs uuid. parse json and check this using gson
                keyStatus = 1;
            } else {
                keyStatus = 4;
            }
        }
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public String getKey() {
        return key;
    }
}
