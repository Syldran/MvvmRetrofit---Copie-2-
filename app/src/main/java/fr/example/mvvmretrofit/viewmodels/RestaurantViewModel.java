package fr.example.mvvmretrofit.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import fr.example.mvvmretrofit.models.Restaurant;
import fr.example.mvvmretrofit.data.repositories.RestaurantRepository;

public class RestaurantViewModel extends ViewModel {

    private static String TAG = "TAG_RestaurantListViewModel";

    public MutableLiveData<List<Restaurant>> mRestaurants;
    public MutableLiveData<Restaurant> mRestaurant;
    RestaurantRepository mRestaurantRepository;
    public LatLng mLocation;
    public String mName;


    public void fetchDetailsRestaurant(String id){
        mRestaurantRepository.getDetailsRestaurant(id, restaurant -> {
            mRestaurant.setValue(restaurant);
        });
    }

    public void fetchRestaurants(String name,LatLng location, int radius){
        Log.d(TAG, "fetchRestaurants: ");
        String sLocation= location.latitude + "," + location.longitude;
        mRestaurantRepository.getRestaurants(sLocation, radius, restaurants -> {
            mRestaurants.setValue(restaurants);
        });
        mLocation=location;
        mName=name;
        Log.d(TAG, "fetchRestaurants: mlocation : "+mLocation);
    }

    public RestaurantViewModel(RestaurantRepository restaurantRepository){
        mRestaurantRepository = restaurantRepository;
        mRestaurants = new MutableLiveData<>();
        mRestaurant = new MutableLiveData<>();
    }
}
