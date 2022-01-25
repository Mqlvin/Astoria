package me.astoria.api.hypixel.player;

public enum PlayerRank {
    DEFAULT("DEFAULT", "DEFAULT"),
    VIP("VIP", "VIP"),
    VIP_PLUS("VIP_PLUS", "VIP+"),
    MVP("MVP", "MVP"),
    MVP_PLUS("MVP_PLUS", "MVP+"),
    MVP_PLUS_PLUS("MVP_PLUS_PLUS", "MVP++");
    // YOUTUBE,
    // PIG,
    // MOJANG,
    // HELPER,
    // GAME_MASTER,
    // ADMINISTRATOR,
    // OWNER

    private final String rank;
    private final String format;
    PlayerRank(String rank, String format) {
        this.rank = rank;
        this.format = format;
    }

    public String getFormat(PlayerRank rank) {
        return format;
    }

    public static PlayerRank getRank(String name) {
        for(PlayerRank rank : PlayerRank.values()) {
            if(rank.rank.equalsIgnoreCase(name)) {
                return rank;
            }
        }
        return null;
    }
}
