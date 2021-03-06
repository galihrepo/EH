package net.eazyhealth.id.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.model.Datum;

import java.util.List;

/**
 * Created by GALIH ADITYO on 4/3/2016.
 */
public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {
    private List<Datum> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CustomTextView tvClinic;
        public CustomTextView tvType;
        public CustomTextView tvPackage;
        public CustomTextView tvDate;
//        public RelativeLayout cardView;

        public ViewHolder(View v) {
            super(v);

//            cardView = (RelativeLayout) v.findViewById(R.id.card_view);
            tvClinic = (CustomTextView) v.findViewById(R.id.clinic);
            tvType = (CustomTextView) v.findViewById(R.id.type);
            tvPackage = (CustomTextView) v.findViewById(R.id.package_medical);
            tvDate = (CustomTextView) v.findViewById(R.id.date);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterItem(Context context, List<Datum> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_adapter_patients_schedule, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Datum data = mDataset.get(position);
        holder.tvClinic.setText(data.getName());
        holder.tvType.setText("Cek Darah");
        holder.tvPackage.setText("Personal");
        holder.tvDate.setText("2010-10-31");

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(context.getApplicationContext(), DetailTemplateActivity.class);
////                i.putExtra("title", "Detail Schedule");
////                ((Activity)context).startActivityForResult(i, 0);
//////                context.startActivity(i);
//////                ((Activity)context).finish();
//            }
//        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}