package net.eazyhealth.id.app.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.DetailTemplateActivity;
import net.eazyhealth.id.app.activity.ListDataActivity;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.dialog.DialogFragmentScheduleDateTime;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;
import net.eazyhealth.id.app.model.response.backendless.Mcu;
import net.eazyhealth.id.app.preferences.DataPreferences;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GALIH ADITYO on 4/5/2016.
 */
public class AdapterItemLoadMore extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Mcu> mDataset;
    private Context context;

    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private DialogFragmentScheduleDateTime dialogAddSchedule;
    private RecyclerView recyclerView;

    public AdapterItemLoadMore(Context context, List<Mcu> myDataSet, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;

        dialogAddSchedule = new DialogFragmentScheduleDateTime();

        if (myDataSet == null) {
            mDataset = new LinkedList<>();
        } else {
            mDataset = myDataSet;
        }

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    public void clear() {
        if (mDataset.size() > 0) {
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    public void addItem(List<Mcu> list) {
        for (Mcu data:list) {
            mDataset.add(data);
            notifyItemInserted(mDataset.size());
        }
        notifyDataSetChanged();
    }

    public void removeProgress() {
        mDataset.remove(mDataset.size() - 1);
        notifyItemRemoved(mDataset.size());
        notifyDataSetChanged();
    }

    public void addProgress() {
        mDataset.add(null);
        notifyItemInserted(mDataset.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_adapter_patients_schedule, parent, false);

            vh = new TextViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading_loadmore, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            final Mcu data = mDataset.get(position);
            ((TextViewHolder) holder).tvClinic.setText(data.getProductName());
            ((TextViewHolder) holder).tvType.setText(data.getClinic().getName());
            ((TextViewHolder) holder).tvPackage.setText(data.getClinic().getAddress()+", "+data.getClinic().getCity());
            ((TextViewHolder) holder).tvDate.setText(data.getPrice()+"");
            ((TextViewHolder) holder).btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dialogAddSchedule.isVisible()) {
                        dialogAddSchedule = new DialogFragmentScheduleDateTime();
                        dialogAddSchedule.setMcu(data);
                        dialogAddSchedule.setListener(new OnDialogTemplateTwoButton() {
                            @Override
                            public void onTemplateDialogNo() {

                            }

                            @Override
                            public void onTemplateDialogYes() {
                                ((ListDataActivity) context).notificationShoppingCart();
                            }
                        });
                        dialogAddSchedule.show(((FragmentActivity) context).getSupportFragmentManager(), "");
                    }
                }
            });
            ((TextViewHolder) holder).btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataPreferences pref = new DataPreferences(context);
                    pref.setMcuChoosen(data);

                    Intent i = new Intent(context.getApplicationContext(), DetailTemplateActivity.class);
                    i.putExtra("title", context.getResources().getString(R.string.detail_medical_checkup));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ((Activity) context).startActivityForResult(i, 0, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                    } else {
                        ((Activity) context).startActivityForResult(i, 0);
                    }
                }
            });
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView tvClinic;
        public CustomTextView tvType;
        public CustomTextView tvPackage;
        public CustomTextView tvDate;
        public LinearLayout btnAdd;
        public CustomTextView btnDetail;

        public TextViewHolder(View v) {
            super(v);

            btnAdd = (LinearLayout) v.findViewById(R.id.btnAdd);
            btnDetail = (CustomTextView) v.findViewById(R.id.btnDetail);
            tvClinic = (CustomTextView) v.findViewById(R.id.clinic);
            tvType = (CustomTextView) v.findViewById(R.id.type);
            tvPackage = (CustomTextView) v.findViewById(R.id.package_medical);
            tvDate = (CustomTextView) v.findViewById(R.id.date);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
}