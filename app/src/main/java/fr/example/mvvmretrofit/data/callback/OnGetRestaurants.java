package fr.example.mvvmretrofit.data.callback;

import java.util.List;

import fr.example.mvvmretrofit.models.Restaurant;

public interface OnGetRestaurants {
    void onGetRestaurantData(List<Restaurant> restaurants);

}
