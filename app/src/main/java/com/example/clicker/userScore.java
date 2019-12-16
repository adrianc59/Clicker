package com.example.clicker;

public class userScore {
    private int mPosition;
    private String mUsername;
    private int mImageResource;
    private int mScore;


    public userScore(int position, String username, int score, int imageResource){
        mPosition = position;
        mImageResource = imageResource;
        mScore = score;
        mUsername = username;
    }

    public int getPosition(){
        return this.mPosition;
    }

    public int getImageResource(){

        return this.mImageResource;
    }

    public String getUsername(){
        return this.mUsername;
    }

    public int getScore(){
        return this.mScore;
    }
}
