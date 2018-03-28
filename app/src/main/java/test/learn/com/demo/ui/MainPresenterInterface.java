package test.learn.com.demo.ui;


public interface MainPresenterInterface<V extends MainView> {

    void requestData();

    void onAttach(V View);

    void onDetach();
}