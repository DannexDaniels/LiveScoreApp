package com.dannextech.apps.livescoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Matches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Matches.this,AddMatches.class));
            }
        });
        LiveScoresQueries queries = new LiveScoresQueries(this);

        RecyclerView rvMatches = (RecyclerView) findViewById(R.id.rvMatches);
        rvMatches.hasFixedSize();
        rvMatches.setLayoutManager(new LinearLayoutManager(this));
        rvMatches.setAdapter(new MatchAdapter(queries.retrieveMatch(),this));
        rvMatches.setItemAnimator(new DefaultItemAnimator());



    }

}
