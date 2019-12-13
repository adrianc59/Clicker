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
        public TextView mTextView3;
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;


        public leaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.textViewPos);
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

        holder.mImageView.setImageResource(currentScore.getImageResource());
        holder.mTextView1.setText(currentScore.getText1());
        holder.mTextView2.setText((currentScore.getText2()));
        holder.mTextView3.setText((currentScore.getPosition()));
    }

    @Override
    public int getItemCount() {
        return mUserscoresList.size();
    }
}
