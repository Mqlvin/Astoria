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
Description: Holds data from the punishment endpoint from the Hypixel API.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.2
--------------------------------------------------------------------
*/

package me.astoria.api.hypixel.punishment;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.astoria.api.hypixel.HypixelAPI;
import me.astoria.log.Logger;
import me.astoria.log.Severity;
import me.astoria.util.http.HttpClient;
import me.astoria.util.http.HttpResponse;

public class PunishmentInfo {
    private Integer watchdogLastMinute = 0;
    private Integer watchdogDaily = 0;
    private Integer watchdogTotal = 0;
    private Integer staffDaily = 0;
    private Integer staffTotal = 0;

    public void refresh(HypixelAPI api) {
        HttpResponse response = HttpClient.request("https://api.hypixel.net/punishmentstats?key=" + api.getKey());
        if(response.response.contains("success")) {
            JsonObject responseObject = new JsonParser().parse(response.response).getAsJsonObject();
            if(responseObject.has("watchdog_lastMinute") && responseObject.has("staff_rollingDaily") && responseObject.has("watchdog_total") && responseObject.has("watchdog_rollingDaily") && responseObject.has("staff_total")) {
                watchdogLastMinute = Integer.parseInt(responseObject.get("watchdog_lastMinute").toString());
                watchdogDaily = Integer.parseInt(responseObject.get("watchdog_rollingDaily").toString());
                watchdogTotal = Integer.parseInt(responseObject.get("watchdog_total").toString());
                staffDaily = Integer.parseInt(responseObject.get("staff_rollingDaily").toString());
                staffTotal = Integer.parseInt(responseObject.get("staff_total").toString());
            } else {
                watchdogLastMinute = 0;
                watchdogDaily = 0;
                watchdogTotal = 0;
                staffDaily = 0;
                staffTotal = 0;
                Logger.report("Error while grabbing data from \"punishmentstats\" Hypixel API endpoint. All wrapper values have been set to 0.", Severity.WARN);
            }
        }
    }

    public Integer getTotalBans() {
        return staffTotal + watchdogTotal;
    }

    public Integer getTotalStaffBans() {
        return staffTotal;
    }

    public Integer getTotalWatchdogBans() {
        return watchdogTotal;
    }

    public Integer getWatchdogLastMinuteBans() {
        return watchdogLastMinute;
    }

    public Integer getWatchdogDailyBans() {
        return watchdogDaily;
    }

    public Integer getStaffDailyBans() {
        return staffDaily;
    }

    public void logData() {
        Logger.log("Total: " + getTotalBans() + "\n" + "Total staff: " + getTotalStaffBans() + "\n" + "Total watchdog: " + getTotalWatchdogBans() + "\n" + "Daily staff: " + getStaffDailyBans() + "\n" + "Daily watchdog: " + getWatchdogDailyBans() + "\n" + "Minutely watchdog: " + getWatchdogLastMinuteBans());
    }
}
