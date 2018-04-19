package com.dannextech.apps.livescoreapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 2/19/18.
 */

public class LiveScoresHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "livescore_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "CREATE TABLE " +
            LiveScoreContract.MatchesDB.TABLE_NAME + " ( " +
            LiveScoreContract.MatchesDB._ID + " INTEGER PRIMARY KEY, " +
            LiveScoreContract.MatchesDB.COL_HOME_TEAM + " TEXT, " +
            LiveScoreContract.MatchesDB.COL_AWAY_TEAM + " TEXT, " +
            LiveScoreContract.MatchesDB.COL_GAME_DATE + " TEXT, " +
            LiveScoreContract.MatchesDB.COL_GAME_TIME + " TEXT, " +
            LiveScoreContract.MatchesDB.COL_GAME_GROUND + " TEXT, " +
            LiveScoreContract.MatchesDB.COL_HOME_SCORES + " INTEGER, " +
            LiveScoreContract.MatchesDB.COL_AWAY_SCORES + " INTEGER)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + LiveScoreContract.MatchesDB.TABLE_NAME;

    public LiveScoresHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.e(TAG, "onCreate: Database has been called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
