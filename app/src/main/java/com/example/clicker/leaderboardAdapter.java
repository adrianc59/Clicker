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
    private String sessionUsername;
    private DatabaseManager dbManager;
    private Context context;
    private Session session;


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

    public leaderboardAdapter(ArrayList<userScore> userScoresList, String username) {
        mUserscoresList = userScoresList;
        sessionUsername = username;
    }


    @NonNull
    @Override
    public leaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_leaderboard, parent, false);
        leaderboardViewHolder lvh = new leaderboardViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final leaderboardViewHolder holder, final int position) {
        userScore currentScore = mUserscoresList.get(position);
        holder.mPositionView.setText(String.valueOf(position + 1));
        holder.mUsernameView.setText((currentScore.getUsername()));
        holder.mScoreView.setText(String.valueOf(currentScore.getScore()));
        Picasso.with(context).load(currentScore.getImageResource()).into(holder.mImageView);

        if(holder.mUsernameView.getText().equals(sessionUsername)) {
            holder.mRemoveButton.setVisibility(View.VISIBLE);
        }

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
                String rowName = mUserscoresList.get(position).getUsername();

                context = holder.mRemoveButton.getContext();
                session = new Session(context);

                dbManager = new DatabaseManager(context);
                dbManager.open();

                dbManager.reset(session);

                // Remove the item on remove/button click
                mUserscoresList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mUserscoresList.size());
                System.out.println("Test1 ========================================");
            }
        });
    }



    @Override
    public int getItemCount() {
        return mUserscoresList.size();
    }
}
