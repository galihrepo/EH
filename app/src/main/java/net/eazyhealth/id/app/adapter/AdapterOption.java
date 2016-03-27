package net.eazyhealth.id.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.interfaces.OnOptionInterface;

/**
 * Created by GALIH ADITYO on 3/25/2016.
 */
public class AdapterOption extends RecyclerView.Adapter<AdapterOption.ViewHolder> {

    private SparseArray<String> mDataset;
    private OnOptionInterface mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CustomTextView mTextView;
        public ViewHolder(View v) {
            super(v);

            mTextView = (CustomTextView) v.findViewById(R.id.item_tv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterOption(SparseArray<String> myDataset, OnOptionInterface listener) {
        mDataset = myDataset;
        mListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_option, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String data = mDataset.get(position);
        holder.mTextView.setText(data);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onResult(data);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
