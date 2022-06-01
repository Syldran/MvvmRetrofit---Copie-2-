package fr.example.mvvmretrofit.ui.listrestaurantfragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.example.mvvmretrofit.R;
import fr.example.mvvmretrofit.databinding.ListItemBinding;
import fr.example.mvvmretrofit.models.Restaurant;
import fr.example.mvvmretrofit.ui.DetailsRestaurantActivity;
import fr.example.mvvmretrofit.ui.RestaurantsActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Restaurant> mRestaurants = new ArrayList<>();
    private final OnRestaurantListener mOnRestaurantListener;
    private static final String TAG = "TAG_RecyclerViewAdapter";



    public RecyclerViewAdapter(List<Restaurant> restaurants, OnRestaurantListener onRestaurantListener) {
        mRestaurants = restaurants;
        mOnRestaurantListener = onRestaurantListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext())));
//        return new ViewHolder(view, mOnRestaurantListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference="+mRestaurants.get(position).getPhotos().get(0).getPhotoReference()+"&key=AIzaSyDCXBbnL9Tw5L_0G6MMtr-F7ibrX-oAx40";
        final Restaurant restaurant = mRestaurants.get(position);
        holder.mBinding.textRecyclerViewTitle.setText(restaurant.getName());
        holder.mBinding.textRecyclerViewAddress.setText(restaurant.getVicinity());
        if (restaurant.getOpeningHours() == null) {
            holder.mBinding.textRecyclerViewOpening.setText("Undefined");
        } else {
            holder.mBinding.textRecyclerViewOpening.setText(mRestaurants.get(position).getOpeningHours().getOpenNow() ? "Open" : "Closed");
        }

        if (mRestaurants.get(position).getRating() != null) {
            float rating = mRestaurants.get(position).getRating().floatValue() / 5f * 3f;
            holder.mBinding.ratingBarRecyclerView.setRating(rating);
        }
        if (mRestaurants.get(position).getPhotos() != null){
            DetailsRestaurantActivity.loadImage(holder.itemView.getContext(), url, holder.mBinding.imgRecyclerViewRestaurant);
        }
        holder.itemView.setOnClickListener(view -> {
            mOnRestaurantListener.onRestaurantClick(restaurant);
        });
//        holder.getTextTitle().setText(restaurant.getName());
//        holder.getTextVicinity().setText(restaurant.getVicinity());
//        if (restaurant.getOpeningHours() == null) {
//            holder.getTextOpen().setText("Undefined");
//        } else {
//            holder.getTextOpen().setText(mRestaurants.get(position).getOpeningHours().getOpenNow() ? "Open" : "Closed");
//        }
//
//        if (mRestaurants.get(position).getRating() != null) {
//            float rating = mRestaurants.get(position).getRating().floatValue() / 5f * 3f;
//            holder.getRatingBar().setRating(rating);
//        }
//        if (mRestaurants.get(position).getPhotos() != null){
//            DetailsRestaurantActivity.loadImage(holder.itemView.getContext(), url, holder.getImgRestaurant());
//        }
//        holder.itemView.setOnClickListener(view -> {
//            mOnRestaurantListener.onRestaurantClick(restaurant);
//        });
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public void updateResults(@NonNull final List<Restaurant> restaurants) {
        mRestaurants = restaurants;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textTitle;
//        private final TextView textVicinity;
//        private final TextView textOpen;
//        private final RatingBar ratingBar;
//        private final ImageView imgRestaurant;
        ListItemBinding mBinding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
//            textTitle = (TextView) view.findViewById(R.id.text_recycler_view_title);
//            textVicinity = (TextView) view.findViewById(R.id.text_recycler_view_address);
//            textOpen = (TextView) view.findViewById(R.id.text_recycler_view_opening);
//            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar_recycler_view);
//            imgRestaurant = (ImageView) view.findViewById(R.id.img_recycler_view_restaurant);


            // Define click listener for the ViewHolder's View
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (onRestaurantListener != null){
//                        int position = getAbsoluteAdapterPosition();
//
//                        if (position != RecyclerView.NO_POSITION) {
//                            onRestaurantListener.onRestaurantClick(position);
//                        }
//                    }
//                }
//            }

        }

//        public TextView getTextVicinity() {
//            return textVicinity;
//        }
//
//        public TextView getTextTitle() {
//            return textTitle;
//        }
//
//        public TextView getTextOpen(){return textOpen;}
//
//        public ImageView getImgRestaurant() {
//            return imgRestaurant;
//        }
//
//        public RatingBar getRatingBar() {
//            return ratingBar;
//        }
    }
//
//    public interface OnRestaurantListener{
//        void onRestaurantClick(int position);
//    }

    public interface OnRestaurantListener{
        void onRestaurantClick(Restaurant restaurant);
    }
}
