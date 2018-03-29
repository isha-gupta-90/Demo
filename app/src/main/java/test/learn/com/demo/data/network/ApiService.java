package test.learn.com.demo.data.network;

import test.learn.com.demo.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Interface for writing methods to be used for making network calls using retrofit.
 * It is an interface which can have the methods for making API calls.
 */
public interface ApiService {

    //https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<ApiResponse> getResponseFromApi();
}
