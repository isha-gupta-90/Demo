package test.learn.com.demo.ui;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.learn.com.demo.data.model.ApiResponse;
import test.learn.com.demo.data.network.ApiClient;

/**
 * This class is the decision-making counterpart of the MainActivity.
 * This is is a pure java class, with no access to Android APIs.
 * <p>
 * This Presenter receives the user interactions passed on from MainActivity (or child views) and then takes the decision
 * based on the business logic, and then instructs the View to perform specific actions.
 * <p>
 * It also communicates with other helper classes for any data it needs to perform business logic.
 * <p>
 * This presenter - MainPresenter is responsible to fetch the data from server for MainActivity and then sets the data
 * or performs additional operations and also intimates the UI about the handling errors.
 *
 * @param <V> any view which extends the MainView.
 */
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
                if (response.isSuccessful()) {
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
    public void onAttach(MainView mainView) {
        mView = (V) mainView;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    private V getView() {
        return mView;
    }

}
