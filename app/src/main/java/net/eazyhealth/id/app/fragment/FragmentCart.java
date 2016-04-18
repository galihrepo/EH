package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.adapter.AdapterCart;
import net.eazyhealth.id.app.application.MyApplication;

/**
 * Created by GALIH ADITYO on 4/18/2016.
 */
public class FragmentCart extends Fragment {

    private RecyclerView rv;
    private AdapterCart adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new AdapterCart(getActivity(), MyApplication.getInstance().getDataPreferences().getMedicalChoosen());
        rv.setAdapter(adapter);
    }
}
