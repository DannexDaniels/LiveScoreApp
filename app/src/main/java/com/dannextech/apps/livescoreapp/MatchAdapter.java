package com.dannextech.apps.livescoreapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 2/19/18.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    LiveScoreModel[] model;
    Context context;

    public MatchAdapter(LiveScoreModel[] liveScoreModels, Matches matches) {
        model = liveScoreModels;
        context = matches;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_details,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvDate.setText(model[position].getGameDate());
        holder.tvAway.setText(model[position].getAway());
        holder.tvHome.setText(model[position].getHome());
        holder.cvMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("home",model[position].getHome());
                edit.putString("away",model[position].getAway());
                edit.putString("hscore",model[position].gethScore());
                edit.putString("ascore",model[position].getaScore());
                edit.putString("ground",model[position].getGround());
                edit.putString("date",model[position].getGameDate());
                edit.apply();
                v.getContext().startActivity(new Intent(context,GameDetails.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.length == 0 ? 0 : model.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHome, tvAway, tvDate;
        CardView cvMatch;
        public ViewHolder(View itemView) {
            super(itemView);

            tvHome = (TextView) itemView.findViewById(R.id.tvGameHome);
            tvAway = (TextView) itemView.findViewById(R.id.tvGameAway);
            tvDate = (TextView) itemView.findViewById(R.id.tvGameDate);
            cvMatch = (CardView) itemView.findViewById(R.id.cvMatch);
        }
    }
}
