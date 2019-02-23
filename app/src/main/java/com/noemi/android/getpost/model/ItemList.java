package com.noemi.android.getpost.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    @SerializedName("data")
    private List<Item> listItem = new ArrayList<>();

    public List<Item> getListItem() {
        return listItem;
    }
}
