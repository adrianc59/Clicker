package com.example.clicker;

public class userScore {
    private String mPosition;
    private int mImageResource;
    private String mText1;
    private String mText2;

    public userScore(String position, int imageResource, String text1, String text2){

        mPosition = position;
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public String getPosition(){

        return mPosition;
    }

    public int getImageResource(){

        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }
}
