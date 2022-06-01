package fr.example.mvvmretrofit.data.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import fr.example.mvvmretrofit.data.RetrofitService;
import fr.example.mvvmretrofit.data.callback.OnGetRestaurants;
import fr.example.mvvmretrofit.data.callback.OnDetailsRestaurant;
import fr.example.mvvmretrofit.data.responses.DetailsResponse;
import fr.example.mvvmretrofit.data.responses.NearbyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    private static String TAG = "TAG_RestaurantRepository";

    public void getDetailsRestaurant(String id, OnDetailsRestaurant onDetailsRestaurantCallBack){
        RetrofitService.getPlacesApi().getDetails(id, "AIzaSyDh9-vXD67X64ASMqxSS-JQUy06g2mF2OE").enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d(TAG, "onResponse: ok");
                    onDetailsRestaurantCallBack.onGetDetailsRestaurantData(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {

            }
        });
    }

    public void getRestaurants(String location, int radius, OnGetRestaurants onGetRestaurantsCallBack){
        Log.d(TAG, "getRestaurants: location & radius = "+location+" & "+ radius);


        RetrofitService.getPlacesApi().getNearBy(location, radius, "restaurant", "AIzaSyDh9-vXD67X64ASMqxSS-JQUy06g2mF2OE").enqueue(new Callback<NearbyResponse>() {
            @Override
            public void onResponse(@NonNull Call<NearbyResponse> call, @NonNull Response<NearbyResponse> response) {
                if (response.code() == 200 && response.body() != null){
                    Log.d(TAG, "onResponse: ok");
                    onGetRestaurantsCallBack.onGetRestaurantData(response.body().getResults());
                }else {
                    try {
                        Log.d(TAG, "onResponse: ERROR : "+response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NearbyResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }

        });
    }
}
