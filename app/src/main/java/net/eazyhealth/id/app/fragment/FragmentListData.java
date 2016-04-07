package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.adapter.AdapterItemLoadMore;
import net.eazyhealth.id.app.application.MyApplication;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomSwipeRefreshLayout;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
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
public class FragmentListData extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RippleViewAndroidM.OnRippleCompleteListener {

    private CustomSwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AdapterItemLoadMore mAdapter;
    private String nextPage = null;
    private CustomRippleView rvSort;
    private CustomRippleView rvFilter;
    private DialogPlus dialogFilter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_data, container, false);
        initView(view, savedInstanceState);
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

    private void initView(View view, Bundle savedInstanceState) {
        initDialogFilter(savedInstanceState);

        rvFilter = (CustomRippleView) view.findViewById(R.id.rv_filter);
        rvFilter.setOnRippleCompleteListener(this);

        rvSort = (CustomRippleView) view.findViewById(R.id.rv_sort);
        rvSort.setOnRippleCompleteListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        swipeRefreshLayout = (CustomSwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        // recycleview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initDialogFilter(Bundle savedInstanceState) {
        dialogFilter = DialogPlus.newDialog(getActivity())
                .setAdapter(new ArrayAdapter<>(getActivity(), R.layout.content_dialog, new String[]{"Klinik Jantung", "Klinik Mata", "Klinik Hati"}))
                .setCancelable(true)
                .setGravity(Gravity.TOP)
                .setContentBackgroundResource(R.color.colorPrimaryDark)
                .setInAnimation(R.anim.slide_in_top)
                .setOutAnimation(R.anim.slide_out_top)
                .setHeader(R.layout.header_dialog)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        CustomToast.setMessage(getActivity(), position+"");
                    }
                })
                .create();
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

    @Override
    public void onComplete(RippleViewAndroidM rippleView) {
        if (rippleView == rvFilter) {
            dialogFilter.show();
        } else if (rippleView == rvSort) {

        }
    }
}
