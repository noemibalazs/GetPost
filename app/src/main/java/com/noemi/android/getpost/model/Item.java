package com.noemi.android.getpost.model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String url;

    public Item(int id, String firstName, String lastName, String url) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUrl() {
        return url;
    }
}
