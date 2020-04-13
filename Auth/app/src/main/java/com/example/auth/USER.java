package com.example.auth;

public class USER
{
    public String EMAIL,NAME,PHONE,PASSWORD,COMMUNITY;

    public USER() {
    }

    public USER( String COMMUNITY ,String EMAIL, String NAME, String PASSWORD, String PHONE) {
        this.EMAIL = EMAIL;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.PASSWORD = PASSWORD;
        this.COMMUNITY = COMMUNITY;
    }
}
