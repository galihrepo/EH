package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;

/**
 * Created by GALIH ADITYO on 4/13/2016.
 */
public class FragmentDatePicker extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        return view;
    }
}
