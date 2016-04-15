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

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.adapter.AdapterItemLoadMore;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomSwipeRefreshLayout;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
import net.eazyhealth.id.app.model.response.backendless.Mcu;

/**
 * Created by GALIH ADITYO on 4/7/2016.
 */
public class FragmentListData extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RippleViewAndroidM.OnRippleCompleteListener {

    private int offset;

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
        offset = 0;

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated("detail");
        queryOptions.addRelated("clinic");
        queryOptions.setOffset(offset);

        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

        Backendless.Persistence.of(Mcu.class).find(query, new AsyncCallback<BackendlessCollection<Mcu>>() {
            @Override
            public void handleResponse(final BackendlessCollection<Mcu> response) {
                try {
                    mAdapter = new AdapterItemLoadMore(getActivity(), null, mRecyclerView);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnLoadMoreListener(new AdapterItemLoadMore.OnLoadMoreListener() {
                        @Override
                        public void onLoadMore() {
                            requestNextPage(response);
                        }
                    });
                    mAdapter.addItem(response.getData());
                } catch (Exception e) {
                    CustomToast.setMessage(getActivity(), e.getMessage());
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                try {
                    CustomToast.setMessage(getActivity(), fault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void requestNextPage(BackendlessCollection<Mcu> response) {
        if (offset > response.getData().size()) {
            return;
        }

        final int oldOffset = offset;
        offset = offset + response.getData().size();

        mAdapter.addProgress();

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated("detail");
        queryOptions.addRelated("clinic");
        queryOptions.setOffset(offset);

        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setQueryOptions(queryOptions);

        Backendless.Persistence.of(Mcu.class).find(query, new AsyncCallback<BackendlessCollection<Mcu>>() {
            @Override
            public void handleResponse(final BackendlessCollection<Mcu> response) {
                mAdapter.removeProgress();

                    try {
                        mAdapter.addItem(response.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                mAdapter.setLoaded();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                offset = oldOffset;
                try {
                    CustomToast.setMessage(getActivity(), fault.getMessage());
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
