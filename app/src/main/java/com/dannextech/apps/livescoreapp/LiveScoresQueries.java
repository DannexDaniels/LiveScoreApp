package com.dannextech.apps.livescoreapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

/**
 * Created by root on 2/19/18.
 */

public class LiveScoresQueries {
    Context context;
    LiveScoresHelper helper;
    SQLiteDatabase database;

    public LiveScoresQueries(Context context) {
        this.context = context;
        helper = new LiveScoresHelper(context);
    }

    public Boolean saveMatch(String home, String away, String date,String time, String ground){
        database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LiveScoreContract.MatchesDB.COL_AWAY_SCORES, "0");
        values.put(LiveScoreContract.MatchesDB.COL_AWAY_TEAM, away);
        values.put(LiveScoreContract.MatchesDB.COL_GAME_DATE,date);
        values.put(LiveScoreContract.MatchesDB.COL_GAME_TIME,time);
        values.put(LiveScoreContract.MatchesDB.COL_GAME_GROUND,ground);
        values.put(LiveScoreContract.MatchesDB.COL_HOME_SCORES,"0");
        values.put(LiveScoreContract.MatchesDB.COL_HOME_TEAM,home);

        Long result = database.insert(LiveScoreContract.MatchesDB.TABLE_NAME,null,values);
        database.close();

        if (result==-1)
            return false;
        else
            return true;
    }

    public LiveScoreModel[] retrieveMatch(){
        database = helper.getReadableDatabase();

        String columns [] = {LiveScoreContract.MatchesDB.COL_HOME_TEAM, LiveScoreContract.MatchesDB.COL_AWAY_TEAM, LiveScoreContract.MatchesDB.COL_GAME_DATE, LiveScoreContract.MatchesDB.COL_GAME_TIME, LiveScoreContract.MatchesDB.COL_GAME_GROUND, LiveScoreContract.MatchesDB.COL_HOME_SCORES, LiveScoreContract.MatchesDB.COL_AWAY_SCORES};
        String sortOrder = LiveScoreContract.MatchesDB.COL_GAME_DATE + " DESC";

        Cursor cursor = database.query(LiveScoreContract.MatchesDB.TABLE_NAME,columns,null,null,null,null,sortOrder);

        int numrows = (int) DatabaseUtils.queryNumEntries(database, LiveScoreContract.MatchesDB.TABLE_NAME);
        LiveScoreModel [] model = new LiveScoreModel[numrows];

        int position = 0;
        while (cursor.moveToNext()){
            model[position] = new LiveScoreModel();

            model[position].setaScore(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_AWAY_SCORES))));
            model[position].setAway(cursor.getString(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_AWAY_TEAM)));
            model[position].setGameDate(cursor.getString(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_GAME_DATE)));
            model[position].setGameTime(cursor.getString(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_GAME_TIME)));
            model[position].setGround(cursor.getString(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_GAME_GROUND)));
            model[position].setHome(cursor.getString(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_HOME_TEAM)));
            model[position].sethScore(cursor.getString(cursor.getColumnIndexOrThrow(LiveScoreContract.MatchesDB.COL_HOME_SCORES)));

            position++;
        }

        cursor.close();
        database.close();

        return model;

    }

    public void updateScores(String home, String whichScored, String score){
        database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (whichScored.equals("away")){
            values.put(LiveScoreContract.MatchesDB.COL_AWAY_SCORES,score);
        }else if (whichScored.equals("home")){
            values.put(LiveScoreContract.MatchesDB.COL_HOME_SCORES,score);
        }

        String selection = LiveScoreContract.MatchesDB.COL_HOME_TEAM + " =? ";
        String [] selectionArgs = {home};

        database.update(LiveScoreContract.MatchesDB.TABLE_NAME,values,selection,selectionArgs);



    }
}
