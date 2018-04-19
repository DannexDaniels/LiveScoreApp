package com.dannextech.apps.livescoreapp;

import android.provider.BaseColumns;

/**
 * Created by root on 2/19/18.
 */

public class LiveScoreContract {
    public LiveScoreContract() {
    }

    public static class MatchesDB implements BaseColumns{
        public static final String TABLE_NAME = "matches";

        public static final String COL_HOME_TEAM = "home";
        public static final String COL_AWAY_TEAM = "away";
        public static final String COL_GAME_DATE = "date";
        public static final String COL_GAME_TIME = "time";
        public static final String COL_GAME_GROUND = "ground";
        public static final String COL_HOME_SCORES = "hscores";
        public static final String COL_AWAY_SCORES = "ascores";

    }
}
