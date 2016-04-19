package net.eazyhealth.id.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.CartActivity;
import net.eazyhealth.id.app.application.MyApplication;
import net.eazyhealth.id.app.custom.CustomImageButton;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.fragment.FragmentCart;
import net.eazyhealth.id.app.model.response.backendless.Mcu;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GALIH ADITYO on 4/18/2016.
 */
public class AdapterCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Mcu> mDataset;
    private Context context;

    private CartActivity parentActivity;
    private FragmentCart parentFragment;

    public AdapterCart(Context context, List<Mcu> myDataSet, FragmentCart fragmentCart) {
        this.context = context;
        if (myDataSet == null) {
            mDataset = new LinkedList<>();
        } else {
            mDataset = myDataSet;
        }

        parentActivity = ((CartActivity) context);
        parentFragment = fragmentCart;
    }

    public void clear() {
        if (mDataset.size() > 0) {
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    public void addItem(List<Mcu> list) {
        for (Mcu data : list) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_cart, parent, false);
        RecyclerView.ViewHolder vh = new TextViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Mcu data = mDataset.get(position);
        ((TextViewHolder) holder).tvClinic.setText(data.getProductName());
        ((TextViewHolder) holder).tvType.setText(data.getClinic().getName());
        ((TextViewHolder) holder).tvPackage.setText(data.getClinic().getAddress() + ", " + data.getClinic().getCity());
        ((TextViewHolder) holder).tvDate.setText(data.getPrice() + "");
        ((TextViewHolder) holder).btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Mcu> list = MyApplication.getInstance().getDataPreferences().getMedicalChoosen();
                list.remove(position);
                MyApplication.getInstance().getDataPreferences().setMedicalChoosen(list);
                parentFragment.refreshData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView tvClinic;
        public CustomTextView tvType;
        public CustomTextView tvPackage;
        public CustomTextView tvDate;
        public CustomImageButton btnDelete;

        public TextViewHolder(View v) {
            super(v);

            btnDelete = (CustomImageButton) v.findViewById(R.id.btn_delete);
            tvClinic = (CustomTextView) v.findViewById(R.id.clinic);
            tvType = (CustomTextView) v.findViewById(R.id.type);
            tvPackage = (CustomTextView) v.findViewById(R.id.package_medical);
            tvDate = (CustomTextView) v.findViewById(R.id.date);
        }
    }
}
