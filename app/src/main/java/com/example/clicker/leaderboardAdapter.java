package com.example.clicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class leaderboardAdapter extends RecyclerView.Adapter<leaderboardAdapter.leaderboardViewHolder> {

    private ArrayList<userScore> mUserscoresList;

    public static class leaderboardViewHolder extends RecyclerView.ViewHolder{
        public TextView mPositionView;
        public ImageView mImageView;
        public TextView mScoreView;
        public TextView mUsernameView;


        public leaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mPositionView = itemView.findViewById(R.id.positionView);
            mUsernameView = itemView.findViewById(R.id.usernameView);
            mScoreView = itemView.findViewById(R.id.scoreView);
        }
    }

    public leaderboardAdapter(ArrayList<userScore> userScoresList) {

        mUserscoresList = userScoresList;
    }


    @NonNull
    @Override
    public leaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_leaderboard, parent, false);
        leaderboardViewHolder lvh = new leaderboardViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull leaderboardViewHolder holder, int position) {
        userScore currentScore = mUserscoresList.get(position);

        holder.mPositionView.setText(String.valueOf(currentScore.getPosition()));
        holder.mUsernameView.setText((currentScore.getUsername()));
        holder.mScoreView.setText(String.valueOf(currentScore.getScore()));
        holder.mImageView.setImageResource(currentScore.getImageResource());
    }

    @Override
    public int getItemCount() {
        return mUserscoresList.size();
    }
}
