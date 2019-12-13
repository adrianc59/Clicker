package com.example.clicker;

public class Ranking {
    private String userName;
    private long score;

    public Ranking() {}

    public Ranking(String userName, long score)
    {
        this.userName = userName;
        this.score = score;

    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName){

        this.userName = userName;
    }

}

