package fr.example.mvvmretrofit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

import fr.example.mvvmretrofit.R;
import fr.example.mvvmretrofit.ui.listrestaurantfragment.ListRestaurantFragment;
import fr.example.mvvmretrofit.ui.mapfragment.MapFragment;
import fr.example.mvvmretrofit.viewmodels.RestaurantViewModel;
import fr.example.mvvmretrofit.viewmodels.ViewModelFactory;

public class RestaurantsActivity extends BaseActivity {

    private static String TAG = "TAG_RestaurantListActivity";
//    private RecyclerView mRecyclerView;
//    private RecyclerViewAdapter mAdapter;
//    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_nav_view);

        mBottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.bottom_list:{
                    Log.d(TAG, "onClick: bottomlist");
                    replaceFragment(new ListRestaurantFragment());
                    break;
                }
                case R.id.bottom_map:{
                    Log.d(TAG, "onClick: map");
                    replaceFragment(new MapFragment());
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + R.id.bottom_nav_view);
            }
            return true;
        });

        if (!Places.isInitialized()){
            Places.initialize(getApplicationContext(), "AIzaSyDh9-vXD67X64ASMqxSS-JQUy06g2mF2OE");
        }


        replaceFragment(new ListRestaurantFragment());

    }





}