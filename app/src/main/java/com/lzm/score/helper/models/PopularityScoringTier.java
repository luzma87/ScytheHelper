package com.lzm.score.helper.models;

public class PopularityScoringTier {
    private int min;
    private int max;

    private int starMultiplier;
    private int territoryMultiplier;
    private int resourceMultiplier;

    public PopularityScoringTier(int min, int max, int starMultiplier, int territoryMultiplier, int resourceMultiplier) {
        this.min = min;
        this.max = max;
        this.starMultiplier = starMultiplier;
        this.territoryMultiplier = territoryMultiplier;
        this.resourceMultiplier = resourceMultiplier;
    }

    public int getScore(PlayerScore playerScore) {
        int popularity = playerScore.getPopularity();
        if (isInTier(popularity)) {
            int playerStars = playerScore.getStars();
            int playerTerritories = playerScore.getTerritories();
            int playerResources = (int) Math.floor(playerScore.getResources() / 2);

            int starsScore = playerStars * starMultiplier;
            int territoriesScore = playerTerritories * territoryMultiplier;
            int resourcesScore = playerResources * resourceMultiplier;

            return starsScore + territoriesScore + resourcesScore;
        }
        return 0;
    }

    private boolean isInTier(int popularity) {
        return min <= popularity && popularity <= max;
    }
}
