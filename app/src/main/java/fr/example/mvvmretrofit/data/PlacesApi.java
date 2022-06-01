package fr.example.mvvmretrofit.data;

import fr.example.mvvmretrofit.data.responses.DetailsResponse;
import fr.example.mvvmretrofit.data.responses.NearbyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesApi {
    @GET("nearbysearch/json")
    Call<NearbyResponse> getNearBy(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("key") String key
    );

    @GET("details/json")
    Call<DetailsResponse> getDetails(
            @Query("place_id") String id,
            @Query("key") String key
    );
}
