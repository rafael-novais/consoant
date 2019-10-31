package com.rafael.tinder.service;

import com.rafael.tinder.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemService {

    @GET("properties.json")
    Call<List<Item>> getProperties();


}
