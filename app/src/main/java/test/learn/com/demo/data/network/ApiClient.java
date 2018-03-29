package test.learn.com.demo.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides a retrofit instance for making the network calls.
 */
public class ApiClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    private ApiClient() {
        //can't be instantiated
    }

    /**
     * Creates and returns instance of ApiService interface using the base url.
     *
     * @return instance of ApiService interface to be used elsewhere
     */

    public static ApiService getApiService() {
        return getClient(BASE_URL).create(ApiService.class);
    }


    /**
     * Builds a new {@link Retrofit} object
     * <p>
     * Calling baseUrl  is required before calling build(). All other methods are optional.
     * Create an implementation of the API endpoints defined by the service interface.
     *
     * @param baseUrl - base url
     * @return Retrofit object.
     */
    private static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}