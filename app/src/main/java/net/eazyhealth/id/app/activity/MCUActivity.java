package net.eazyhealth.id.app.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.adapter.AdapterItem;
import net.eazyhealth.id.app.adapter.AdapterItemLoadMore;
import net.eazyhealth.id.app.adapter.patients.AdapterSchedule;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomSwipeRefreshLayout;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
import net.eazyhealth.id.app.model.Patients;
import net.eazyhealth.id.app.rest.EndPoints;
import net.eazyhealth.id.app.rest.RestClient;
import net.eazyhealth.id.app.rest.ServiceAddress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MCUActivity extends CustomAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String BUNDLE_TITLE = MCUActivity.class.getSimpleName() + "title";
    private static final int DELAY = 2;
    private String title = "";
    private String nextPage = null;
    private Toolbar toolbar;
    private CustomSwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AdapterItemLoadMore mAdapter;
    private CustomTextView tvTitle;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcu);
        bundle();
        initView();
        initVariable();
    }

    private void bundle() {
        if (getIntent().getExtras() != null) {
            title = getIntent().getStringExtra(BUNDLE_TITLE);
        }
    }

    private void initVariable() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                request();
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout = (CustomSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        tvTitle = (CustomTextView) findViewById(R.id.title_tv);
        tvTitle.setText(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        // recycleview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterItemLoadMore(getApplicationContext(), null, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new AdapterItemLoadMore.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (nextPage == null) {
                    return;
                }

                if (handler == null) {
                    handler = new Handler();
                }
                mAdapter.addProgress();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestNextPage();
                    }
                }, DELAY);

            }
        });
    }

    @Override
    public void onRefresh() {
        request();
    }

    private void request() {
        EndPoints service = RestClient.getInstance().getRetrofit().create(EndPoints.class);
        Call<Patients> repos = service.getPatients();
        repos.enqueue(new Callback<Patients>() {
            @Override
            public void onResponse(Call<Patients> call, Response<Patients> response) {
                try {
                    mAdapter.clear();
                    mAdapter.addItem(response.body().getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (response.body().getNextPage() != null) {
                    nextPage = response.body().getNextPage().replace(ServiceAddress.BASE_URL, "");
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Patients> call, Throwable t) {
                try {
                    CustomToast.setMessage(getApplicationContext(), t.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void requestNextPage() {
        EndPoints service = RestClient.getInstance().getRetrofit().create(EndPoints.class);
        Call<Patients> repos = service.getPatients(nextPage);
        repos.enqueue(new Callback<Patients>() {
            @Override
            public void onResponse(Call<Patients> call, Response<Patients> response) {
                mAdapter.removeProgress();
                try {
                    mAdapter.addItem(response.body().getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (response.body().getNextPage() == null) {
                    nextPage = null;
                } else {
                    nextPage = response.body().getNextPage().replace(ServiceAddress.BASE_URL, "");
                }

                mAdapter.setLoaded();
            }

            @Override
            public void onFailure(Call<Patients> call, Throwable t) {
                CustomToast.setMessage(getApplicationContext(), t.getMessage());
                mAdapter.removeProgress();
                mAdapter.setLoaded();
            }
        });
    }
}
