package test.learn.com.demo.ui;


public interface MainPresenterInterface<V extends MainView> {

    /**
     * Request data from the API
     */
    void requestData();

    /**
     * To be called when the view is attached to a presenter
     *
     * @param view the view attached
     */

    void onAttach(V view);

    /**
     * To be called when the view is detached from the presenter.
     */
    void onDetach();
}

