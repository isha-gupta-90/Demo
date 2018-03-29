package test.learn.com.demo.ui;

import test.learn.com.demo.data.model.ApiResponse;

/**
 * The view for the MainPresenter.This view should be implemented by the MainActivity in order to be attached the MainPresenter.
 */

public interface MainView {

    /**
     * This method is responsible for updating the views with ApiResponse
     *
     * @param apiResponse ApiResponse object
     */
    void updateViewWithData(ApiResponse apiResponse);
}