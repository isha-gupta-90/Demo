package android.learn.com.demo.data.network;

import android.learn.com.demo.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

//https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<ApiResponse> getResponseFromApi();
}
