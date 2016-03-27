package net.eazyhealth.id.app.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.adapter.AdapterOption;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.interfaces.OnOptionInterface;

/**
 * Created by GALIH ADITYO on 3/25/2016.
 */
public class DialogFragmentOption extends DialogFragment {

    private String title;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SparseArray<String> data;
    private OnOptionInterface listener;

    private CustomTextView tvTitle;
    private CustomTextView btnCancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_option, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
        tvTitle = (CustomTextView) rootView.findViewById(R.id.title_tv);
        btnCancel = (CustomTextView) rootView.findViewById(R.id.cancel_btn);
    }

    private void initVariable() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterOption(data, listener);
        recyclerView.setAdapter(adapter);

        tvTitle.setText(title);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setAdapter(SparseArray<String> data) {
        this.data = data;
    }

    public void setListener(OnOptionInterface listener) {
        this.listener = listener;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
