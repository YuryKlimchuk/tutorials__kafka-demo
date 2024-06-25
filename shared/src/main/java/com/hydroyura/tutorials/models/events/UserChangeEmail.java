package com.hydroyura.tutorials.models.events;

public class UserChangeEmail {

    private String id;
    private String newEmail;


    public UserChangeEmail() {}


    public String getId() {
        return id;
    }

    public UserChangeEmail setId(String id) {
        this.id = id;
        return this;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public UserChangeEmail setNewEmail(String newEmail) {
        this.newEmail = newEmail;
        return this;
    }
}
