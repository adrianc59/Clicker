package com.example.clicker;

public class userScore {
    private String mUsername;
    private String mImageResource;
    private int mScore;


    public userScore(String username, int score, String imageResource){
        mImageResource = imageResource;
        mScore = score;
        mUsername = username;
    }

    public String getImageResource(){ return this.mImageResource; }
    public String getUsername(){
        return this.mUsername;
    }
    public int getScore(){
        return this.mScore;
    }
}
