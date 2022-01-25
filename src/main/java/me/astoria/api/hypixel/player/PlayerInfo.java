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
Description: Holds and sorts objects with data from the player endpoint from the Hypixel API.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.4
--------------------------------------------------------------------
*/


package me.astoria.api.hypixel.player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.astoria.api.hypixel.HypixelAPI;
import me.astoria.api.hypixel.player.games.BuildBattleStatistics;
import me.astoria.log.Logger;
import me.astoria.log.Severity;
import me.astoria.util.http.HttpClient;
import me.astoria.util.http.HttpResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerInfo {
    private HypixelAPI instance;
    private String uuid;

    private long firstLogin;
    private long lastLogin;
    private String displayName;
    private PlayerRank rank;
    private ArrayList<String> oldUsernames;
    private BigInteger networkExp;
    private double networkExpParsed;
    private long karma;
    private Integer totalDailyRewards;
    private Integer dailyRewardStreak;
    private Integer dailyRewardStreakHighscore;
    private Integer ranksGifted;


    private BuildBattleStatistics buildBattle;

    public PlayerInfo(HypixelAPI api, String uuid) {
        this.instance = api;
        this.uuid = uuid.replace("-", "");
    }

    public void refresh() {
        JsonObject response;
        try {
            response = new JsonParser().parse(HttpClient.request("https://api.hypixel.net/player?key=" + instance.getKey() + "&uuid=" + uuid).response).getAsJsonObject();
        } catch(Exception e) {
            throwError();
            return;
        }
        if(response == null) {
            throwError();
            return;
        }
        buildBattle = new BuildBattleStatistics(response);
        try {
            System.out.println("here");
            if(response.getAsJsonObject("player").has("firstLogin")) {
                firstLogin = Long.parseLong(response.getAsJsonObject("player").get("firstLogin").toString().replace("\"", ""));
            }
            if(response.getAsJsonObject("player").has("lastLogin")) {
                lastLogin = Long.parseLong(response.getAsJsonObject("player").get("lastLogin").toString().replace("\"", ""));
            }
            if(response.getAsJsonObject("player").has("displayname")) {
                displayName = response.getAsJsonObject("player").get("displayname").toString().replace("\"", "");
            }
            System.out.println("there");
            if(response.getAsJsonObject("player").has("newPackageRank")) {
                rank = PlayerRank.getRank(response.getAsJsonObject("player").get("newPackageRank").toString().replace("\"", ""));
                if(response.getAsJsonObject("player").has("monthlyPackageRank")) {
                    String monthlyPackageRank = response.getAsJsonObject("player").get("monthlyPackageRank").toString().replace("\"", "");
                    if(!monthlyPackageRank.equalsIgnoreCase("none")) {
                        rank = PlayerRank.MVP_PLUS_PLUS;
                    }
                }
            } else {
                rank = PlayerRank.DEFAULT;
            }
            System.out.println("bing bong");
            if(response.getAsJsonObject("player").has("networkExp")) {
                networkExp = BigInteger.valueOf(Long.parseLong(response.getAsJsonObject("player").get("networkExp").toString().replace("\"", "")));
                System.out.println("WOW OK ITS THE PARSE"); // TODO: THIS IS THROWING THE ERROR
                networkExpParsed = Math.sqrt((networkExp.longValue() * 2) + 30625) - 2.5;
            }
            System.out.println("there x2");
            if(response.getAsJsonObject("player").has("karma")) {
                karma = Long.parseLong(response.getAsJsonObject("player").get("karma").toString().replace("\"", ""));
            }
            System.out.println("sad ");
        } catch(Exception e) {
            Logger.report("Error while parsing player-data into wrapper, wrapper values have been defaulted. " + e, Severity.WARN);
        }
    }

    public long getFirstLogin() {
        return firstLogin;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public PlayerRank getRank() {
        return rank;
    }

    public ArrayList<String> getOldUsernames() {
        return oldUsernames;
    }

    public BigInteger getNetworkExp() {
        return networkExp;
    }

    public double getNetworkLevel() {
        return networkExpParsed;
    }

    public long getKarma() {
        return karma;
    }

    public Integer getTotalDailyRewards() {
        return totalDailyRewards;
    }

    public Integer getDailyRewardStreak() {
        return dailyRewardStreak;
    }

    public Integer getDailyRewardStreakHighscore() {
        return dailyRewardStreakHighscore;
    }

    public Integer getRanksGifted() {
        return ranksGifted;
    }

    public BuildBattleStatistics getBuildBattleStats() {
        return buildBattle;
    }

    private void throwError() {
        Logger.report("Error while grabbing data from \"player\" Hypixel API endpoint. All wrapper values have been set to an appropriate value.", Severity.WARN);
        setDefaults();
    }

    private void setDefaults() {
        buildBattle.setDefaults();

        firstLogin = 0;
        lastLogin = 0;
        displayName = "Unknown";
        rank = PlayerRank.DEFAULT;
        oldUsernames = new ArrayList<>(Arrays.asList("Unknown", "Unknown"));
        networkExp = BigInteger.valueOf(0);
        networkExpParsed = 1;
        karma = 0;
        totalDailyRewards = 0;
        dailyRewardStreak = 0;
        dailyRewardStreakHighscore = 0;
        ranksGifted = 0;
    }
}
