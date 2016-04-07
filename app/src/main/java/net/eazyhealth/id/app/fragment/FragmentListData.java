package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.adapter.AdapterItemLoadMore;
import net.eazyhealth.id.app.application.MyApplication;
import net.eazyhealth.id.app.custom.CustomSwipeRefreshLayout;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.model.response.mcu.McuModel;
import net.eazyhealth.id.app.rest.EndPoints;
import net.eazyhealth.id.app.rest.RestHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GALIH ADITYO on 4/7/2016.
 */
public class FragmentListData extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private CustomSwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AdapterItemLoadMore mAdapter;
    private String nextPage = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_data, container, false);
        initView(view);
        initVariable();
        return view;
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

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        swipeRefreshLayout = (CustomSwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        // recycleview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onRefresh() {
        request();
    }

    private void request() {
        EndPoints service = MyApplication.getInstance().getRetrofit().create(EndPoints.class);
        Call<McuModel> repos = service.getMcuList();
        repos.enqueue(new Callback<McuModel>() {
            @Override
            public void onResponse(Call<McuModel> call, Response<McuModel> response) {
                if (response.isSuccessful()) {
                    try {
                        mAdapter = new AdapterItemLoadMore(getActivity(), null, mRecyclerView);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnLoadMoreListener(new AdapterItemLoadMore.OnLoadMoreListener() {
                            @Override
                            public void onLoadMore() {
                                requestNextPage();
                            }
                        });
                        mAdapter.addItem(response.body().getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (response.body().getNextPage() == null) {
                        nextPage = null;
                    } else {
                        nextPage = RestHelper.removeBaseUrl(response.body().getNextPage());
                    }
                } else {
                    try {
                        CustomToast.setMessage(getActivity(), response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<McuModel> call, Throwable t) {
                try {
                    CustomToast.setMessage(getActivity(), t.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void requestNextPage() {
        if (nextPage == null) {
            return;
        }
        mAdapter.addProgress();

        EndPoints service = MyApplication.getInstance().getRetrofit().create(EndPoints.class);
        Call<McuModel> repos = service.getMcuList(nextPage);
        repos.enqueue(new Callback<McuModel>() {
            @Override
            public void onResponse(Call<McuModel> call, Response<McuModel> response) {
                mAdapter.removeProgress();

                if (response.isSuccessful()) {
                    try {
                        mAdapter.addItem(response.body().getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (response.body().getNextPage() == null) {
                        nextPage = null;
                    } else {
                        nextPage = RestHelper.removeBaseUrl(response.body().getNextPage());
                    }
                }

                mAdapter.setLoaded();
            }

            @Override
            public void onFailure(Call<McuModel> call, Throwable t) {
                try {
                    CustomToast.setMessage(getActivity(), t.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mAdapter.removeProgress();
                mAdapter.setLoaded();
            }
        });
    }
}
