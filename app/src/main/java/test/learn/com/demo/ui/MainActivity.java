package test.learn.com.demo.ui;

import android.demo.com.demo.R;

import test.learn.com.demo.data.model.ApiResponse;
import test.learn.com.demo.data.model.Row;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private List<Row> responses;
    private android.support.v7.widget.Toolbar mTopToolbar;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;
    MainPresenter<MainView> presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTopToolbar = findViewById(R.id.toolbar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        pullToRefresh.setRefreshing(false);
        presenter=new MainPresenter<MainView>(MainActivity.this);
        presenter.onAttach(this);

        presenter.requestData();

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

        pullToRefresh.setRefreshing(false);
        mTopToolbar.setTitle(apiResponse.getTitle());

        responses = apiResponse.getRows();
        adapter = new RecyclerAdapter(responses, MainActivity.this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}