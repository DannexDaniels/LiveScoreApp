package com.dannextech.apps.livescoreapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameDetails extends AppCompatActivity {
    TextView hScore, aScore, ground, date;
    Button home, away;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        hScore = (TextView) findViewById(R.id.tvHomeScore);
        aScore = (TextView) findViewById(R.id.tvAwayScore);
        ground = (TextView) findViewById(R.id.tvGround);
        date = (TextView) findViewById(R.id.tvDate);
        home = (Button) findViewById(R.id.btHomeTeam);
        away = (Button) findViewById(R.id.btAwayTeam);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        hScore.setText(preferences.getString("hscore",null));
        aScore.setText(preferences.getString("ascore",null));
        ground.setText(preferences.getString("ground",null));
        date.setText(preferences.getString("date",null));
        home.setText(preferences.getString("home",null));
        away.setText(preferences.getString("away",null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mEdit :
                editScores();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void editScores() {
        Toast.makeText(getApplicationContext(),"Edit the fucking game",Toast.LENGTH_LONG).show();

        final LiveScoresQueries queries = new LiveScoresQueries(getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(GameDetails.this);
        builder.setTitle("Which team has scored?");
        final ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(GameDetails.this,android.R.layout.select_dialog_item);
        teamAdapter.add("Home");
        teamAdapter.add("Away");

        builder.setAdapter(teamAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int score = 0;
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor edit = preferences.edit();
                if (which == 0){
                    score++;
                    queries.updateScores(home.getText().toString(),"home",String.valueOf(score));

                    edit.putString("hscore",String.valueOf(score));

                    startActivity(new Intent(getApplicationContext(),GameDetails.class));
                }else {
                    score++;
                    queries.updateScores(home.getText().toString(),"away",String.valueOf(score));

                    edit.putString("ascore",String.valueOf(score));

                    startActivity(new Intent(getApplicationContext(),GameDetails.class));
                }

            }
        });
         builder.show();
    }
}
