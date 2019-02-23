package com.noemi.android.getpost.network;

import com.noemi.android.getpost.model.Item;
import com.noemi.android.getpost.model.ItemList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("users")
    Call<ItemList> getListItems();

    @POST("users")
    Call<Item> createPost(@Body Item item);

}
