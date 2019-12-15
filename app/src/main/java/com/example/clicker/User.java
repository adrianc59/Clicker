package com.example.clicker;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private int currCount;
    private int totalCount;
    private int multiplier;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;

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

        this.item1 = 0;
        this.item2 = 0;
        this.item3 = 0;
        this.item4 = 0;
        this.item5 = 0;
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

    public int getItem1() { return this.item1; }
    public int getItem2() { return this.item2; }
    public int getItem3() { return this.item3; }
    public int getItem4() { return this.item4; }
    public int getItem5() { return this.item5; }

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

    public void setItem1() { this.item1 = 1; }
    public void setItem2() { this.item2 = 1; }
    public void setItem3() { this.item3 = 1; }
    public void setItem4() { this.item4 = 1; }
    public void setItem5() { this.item5 = 1; }
}
