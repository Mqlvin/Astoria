package me.astoria.api.hypixel.player.games;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.astoria.log.Logger;
import me.astoria.log.Severity;

public class BuildBattleStatistics {
    private Integer gamesPlayed;
    private Integer score;
    private Integer coins;
    private Integer totalVotes;
    private Integer wins;
    private Integer guessTheBuildWins;
    private Integer superVotes;
    private Integer correctGuesses;
    private Boolean hasMusic;

    public BuildBattleStatistics(JsonObject response) {
        try {
            setDefaults();
            JsonObject data = response.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("BuildBattle");
            gamesPlayed = Integer.parseInt(data.get("games_played").toString().replace("\"", ""));
            score = Integer.parseInt(data.get("score").toString().replace("\"", ""));
            coins = Integer.parseInt(data.get("coins").toString().replace("\"", ""));
            totalVotes = Integer.parseInt(data.get("total_votes").toString().replace("\"", ""));
            wins = Integer.parseInt(data.get("wins").toString().replace("\"", ""));
            guessTheBuildWins = Integer.parseInt(data.get("wins_guess_the_build").toString().replace("\"", ""));
            superVotes = Integer.parseInt(data.get("super_votes").toString().replace("\"", ""));
            correctGuesses = Integer.parseInt(data.get("correct_guesses").toString().replace("\"", ""));
            hasMusic = Boolean.parseBoolean(data.get("music").toString().replace("\"", ""));
        } catch(Exception e) {
            Logger.report("Error while loading BuildBattle data into wrapper. " + e, Severity.WARN);
            setDefaults();
        }
    }

    public void setDefaults() {
        gamesPlayed = 0;
        score = 0;
        coins = 0;
        totalVotes = 0;
        wins = 0;
        guessTheBuildWins = 0;
        superVotes = 0;
        correctGuesses = 0;
        hasMusic = false;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getCoins() {
        return coins;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getGuessTheBuildWins() {
        return guessTheBuildWins;
    }

    public Integer getSuperVotes() {
        return superVotes;
    }

    public Integer getCorrectGuesses() {
        return correctGuesses;
    }

    public Boolean getHasMusic() {
        return hasMusic;
    }
}
