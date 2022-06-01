package fr.example.mvvmretrofit.ui.listrestaurantfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.example.mvvmretrofit.R;
import fr.example.mvvmretrofit.models.Restaurant;
import fr.example.mvvmretrofit.ui.DetailsRestaurantActivity;
import fr.example.mvvmretrofit.viewmodels.RestaurantViewModel;
import fr.example.mvvmretrofit.viewmodels.ViewModelFactory;

public class ListRestaurantFragment extends Fragment implements RecyclerViewAdapter.OnRestaurantListener {

    RestaurantViewModel mViewModel;
    private final ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    private static String TAG = "TAG_ListRestaurantFragment";

    private RecyclerView mRecyclerView;

    private void initData() {
        mViewModel = new ViewModelProvider(requireActivity(), ViewModelFactory.getInstance()).get(RestaurantViewModel.class);
        mViewModel.mRestaurants.observe(requireActivity(), restaurants -> {
            if (restaurants != null){
//                for (int i = 0; i < restaurants.size(); i++) {
//                    Log.d(TAG, "onChanged: "+restaurants.get(i).getName());
//                    if (restaurants.get(i).getOpeningHours() != null) {
//                        Log.d(TAG, "initData: pas null");
//                    } else Log.d(TAG, "initData: null");
//                }
                mRestaurants.clear();
                mRestaurants.addAll(restaurants);
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
            else{
                Log.d(TAG, "onChanged: is null");
            }
        });
    }

    public void configRecyclerView(View view){
        Log.d(TAG, "configRecyclerView: ");
        // Set the adapter
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mRestaurants, this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        Log.d(TAG, "onCreateView: ");

        configRecyclerView(view);
        initData();
        return view;
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        Intent intent = new Intent(getActivity(), DetailsRestaurantActivity.class);
//        intent.putExtra("POSITION", position);
        intent.putExtra("ID", restaurant.getPlaceId());
        intent.putExtra("RESTAURANT", restaurant);
        intent.putExtra("PHOTO", restaurant.getPhotos().get(0).getPhotoReference());


        startActivity(intent);
    }
}