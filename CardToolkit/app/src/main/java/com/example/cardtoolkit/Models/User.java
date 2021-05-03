package com.example.cardtoolkit.Models;

public class User {

    private String email;

    // Empty object constructor
    public User() {}

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
