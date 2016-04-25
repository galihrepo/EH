package net.eazyhealth.id.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.PaymentActivity;
import net.eazyhealth.id.app.adapter.AdapterCart;
import net.eazyhealth.id.app.application.MyApplication;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
import net.eazyhealth.id.app.model.response.backendless.Mcu;

import java.util.LinkedList;
import java.util.List;

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
        refreshData();
        return view;
    }

    private void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        CustomRippleView btnPay = (CustomRippleView) view.findViewById(R.id.btn_payment);
        btnPay.setOnRippleCompleteListener(new RippleViewAndroidM.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleViewAndroidM rippleView) {
                getActivity().startActivityForResult(new Intent(getActivity(), PaymentActivity.class), 0);
            }
        });
    }

    public void refreshData() {
        List<Mcu> data = MyApplication.getInstance().getDataPreferences().getMedicalChoosen();
        if (data == null || data.size() < 1) {
            data = new LinkedList<>();
        }
        adapter = new AdapterCart(getActivity(), data, this);
        rv.setAdapter(adapter);
    }
}
