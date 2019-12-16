package com.example.clicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class leaderboardAdapter extends RecyclerView.Adapter<leaderboardAdapter.leaderboardViewHolder> {

    private ArrayList<userScore> mUserscoresList;
    Context context;


    public static class leaderboardViewHolder extends RecyclerView.ViewHolder{
        public TextView mPositionView;
        public ImageView mImageView;
        public TextView mScoreView;
        public TextView mUsernameView;

        public Button mRemoveButton;


        public leaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mPositionView = itemView.findViewById(R.id.positionView);
            mUsernameView = itemView.findViewById(R.id.usernameView);
            mScoreView = itemView.findViewById(R.id.scoreView);

            mRemoveButton = itemView.findViewById(R.id.removeBtn);
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
    public void onBindViewHolder(@NonNull leaderboardViewHolder holder, final int position) {
        userScore currentScore = mUserscoresList.get(position);
        holder.mPositionView.setText(String.valueOf(position + 1));
        holder.mUsernameView.setText((currentScore.getUsername()));
        holder.mScoreView.setText(String.valueOf(currentScore.getScore()));
        Picasso.with(context).load(currentScore.getImageResource()).into(holder.mImageView);


       /* URL url = null;
        try {
            url = new URL(currentScore.getImageResource());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.mImageView.setImageBitmap(bmp);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error = " + e);
        }*/

       holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // Get the clicked item label
               userScore itemLabel = mUserscoresList.get(position);

               // Remove the item on remove/button click
               mUserscoresList.remove(position);
               notifyItemRemoved(position);
               notifyItemRangeChanged(position, mUserscoresList.size());
           }
       });
    }



    @Override
    public int getItemCount() {
        return mUserscoresList.size();
    }
}
