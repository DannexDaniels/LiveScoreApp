package com.dannextech.apps.livescoreapp;

/**
 * Created by root on 2/19/18.
 */

public class LiveScoreModel {
    String home, away, hScore, aScore, ground, gameDate, gameTime;

    public LiveScoreModel() {
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String gethScore() {
        return hScore;
    }

    public void sethScore(String hScore) {
        this.hScore = hScore;
    }

    public String getaScore() {
        return aScore;
    }

    public void setaScore(String aScore) {
        this.aScore = aScore;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }
}
