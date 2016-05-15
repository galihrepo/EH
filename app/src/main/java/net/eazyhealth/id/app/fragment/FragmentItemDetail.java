package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.DetailTemplateActivity;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
import net.eazyhealth.id.app.dialog.DialogFragmentScheduleDateTime;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;
import net.eazyhealth.id.app.preferences.DataPreferences;

/**
 * Created by GALIH ADITYO on 4/9/2016.
 */
public class FragmentItemDetail extends Fragment implements RippleViewAndroidM.OnRippleCompleteListener {

    private CustomRippleView btnAdd;
    private final int ADD_ONE_DAY = 1;
    private DialogFragmentScheduleDateTime dialog;
    private DataPreferences pref;
    private DetailTemplateActivity parentActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        dialog = new DialogFragmentScheduleDateTime();
        pref = new DataPreferences(getActivity());
        parentActivity = ((DetailTemplateActivity) getActivity());
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnAdd = (CustomRippleView) view.findViewById(R.id.btnAdd);
        btnAdd.setOnRippleCompleteListener(this);
    }

    @Override
    public void onComplete(RippleViewAndroidM rippleView) {
        if (rippleView == btnAdd) {
            if (!dialog.isVisible()) {
                dialog = new DialogFragmentScheduleDateTime();
                dialog.setMcu(pref.getMcuChoosen());
                dialog.setListener(new OnDialogTemplateTwoButton() {
                    @Override
                    public void onTemplateDialogNo() {

                    }

                    @Override
                    public void onTemplateDialogYes() {
                        parentActivity.notificationShoppingCart();
                    }
                });
                dialog.show(getFragmentManager(), "");
            }
        }
    }
}
