package com.example.clicker;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private int currCount;
    private int totalCount;
    private int multiplier;

    //Constructors
    public User(){
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.currCount = 0;
        this.totalCount = 0;
        this.multiplier = 1;
    }

    //Getters
    public int getId()
    {
        return this.id;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public int getCurrCount()
    {
        return this.currCount;
    }
    public int getTotalCount()
    {
        return this.totalCount;
    }
    public int getMultiplier()
    {
        return this.multiplier;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setUsername(String username){ this.username = username; }
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
    public void setCurrCount(int count){ this.currCount = count; }
    public void setTotalCount(int count){ this.totalCount = count; }
    public void setMultiplier(int multiplier){ this.multiplier = multiplier; }
}
