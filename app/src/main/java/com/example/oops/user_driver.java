package com.example.oops;

public class user_driver {
    private String Name;
    private String Password;
    private String Email;
    private String Community;
    private String Radius;

    public user_driver() {
    }

    public user_driver( String radius , String email,  String community, String name , String password) {
        Name = name;
        Password = password;
        Email = email;
        Community = community;
        Radius = radius;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCommunity() {
        return Community;
    }

    public void setCommunity(String community) {
        Community = community;
    }

    public String getRadius() {
        return Radius;
    }

    public void setRadius(String radius) {
        Radius = radius;
    }
}

