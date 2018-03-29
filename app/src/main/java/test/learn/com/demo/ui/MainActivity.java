package test.learn.com.demo.ui;

import android.demo.com.demo.R;

import test.learn.com.demo.data.model.ApiResponse;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private Toolbar mTopToolbar;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;
    MainPresenter<MainView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        presenter = new MainPresenter<MainView>(MainActivity.this);
        presenter.onAttach(this);
        presenter.requestData();
    }

    private void initUI() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTopToolbar = findViewById(R.id.toolbar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        pullToRefresh.setRefreshing(false);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                presenter.requestData();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void updateViewWithData(ApiResponse apiResponse) {
        mTopToolbar.setTitle(apiResponse.getTitle());
        pullToRefresh.setRefreshing(false);

        recyclerAdapter = new RecyclerAdapter(apiResponse.getRows(), MainActivity.this);
        recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}