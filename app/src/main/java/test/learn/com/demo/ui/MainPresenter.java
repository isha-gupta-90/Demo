package test.learn.com.demo.ui;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.learn.com.demo.data.model.ApiResponse;
import test.learn.com.demo.data.network.ApiClient;

public class MainPresenter<V extends MainView> implements MainPresenterInterface {
    private V mView;

    public MainPresenter(V mView) {
        this.mView = mView;
    }

    @Override
    public void requestData() {
        Call<ApiResponse> call = ApiClient.getApiService().getResponseFromApi();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                // Log.d("Success", response.body().toString());
                if (response.isSuccessful())
                {
                    getView().updateViewWithData(response.body());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(MainView View) {
        mView = (V) View;
    }

    @Override
    public void onDetach() {
        mView=null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    private V getView() {
        return mView;
    }

}
