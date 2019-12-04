package com.example.clicker;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private int RepCount;
    private int TapCount;

    //Constructors
    public User(){
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(int id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
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
    public int getRepCount()
    {
        return this.RepCount;
    }
    public int getTapCount()
    {
        return this.TapCount;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setUsername(String username){ this.username = username; }
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
    public void setRepCount(int count){ this.RepCount = count; }
    public void setTapCount(int count){ this.TapCount = count; }
}
