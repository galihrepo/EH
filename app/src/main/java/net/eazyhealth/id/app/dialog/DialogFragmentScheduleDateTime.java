package net.eazyhealth.id.app.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomRadioButton;
import net.eazyhealth.id.app.custom.CustomRadioGroup;

/**
 * Created by GALIH ADITYO on 4/14/2016.
 */
public class DialogFragmentScheduleDateTime extends DialogFragment {

    private SparseArray<String> listDate = new SparseArray<>();
    private SparseArray<String> listTime = new SparseArray<>();
    private Integer idDateSelected = null;
    private Integer idTimeSelected = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_schedule_date_time, container, false);
        initData();
        initView(rootView);
        return rootView;
    }

    private void initData() {
        listDate.put(0, "21 Jan 2016");
        listDate.put(1, "21 Jan 2016");
        listDate.put(2, "21 Jan 2016");
        listDate.put(3, "21 Jan 2016");
        listDate.put(4, "21 Jan 2016");
        listDate.put(5, "21 Jan 2016");
        listDate.put(6, "21 Jan 2016");
        listDate.put(7, "21 Jan 2016");
        listDate.put(8, "21 Jan 2016");
        listDate.put(9, "21 Jan 2016");
        listDate.put(10, "21 Jan 2016");
        listDate.put(11, "21 Jan 2016");
        listDate.put(12, "21 Jan 2016");
        listDate.put(13, "21 Jan 2016");
        listDate.put(14, "21 Jan 2016");

        listTime.put(0, "13:00");
        listTime.put(1, "13:00");
        listTime.put(2, "13:00");
    }

    private void initView(View rootView) {
        addViewDate(rootView);
        addViewTime(rootView);
    }

    private void addViewDate(View rootView) {
        final SparseArray<CustomRadioButton> listRadio = new SparseArray<>();
        CustomRadioGroup group = (CustomRadioGroup) rootView.findViewById(R.id.rg_date);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < listDate.size(); i++) {
            View view = inflater.inflate(R.layout.item_radio_button, group, false);
            final CustomRadioButton radioButton = (CustomRadioButton) view.findViewById(R.id.rb);
            listRadio.put(i, radioButton);
            radioButton.setText(listDate.get(i));
            final int a = i;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (radioButton.isChecked()) {
                        for(int x=0; x<listRadio.size(); x++) {
                            if (x == a) {
                                listRadio.get(x).setChecked(true);
                                idDateSelected = x;
                            } else {
                                listRadio.get(x).setChecked(false);
                            }
                        }
                    }
                }
            });

            group.addView(view);
        }
    }

    private void addViewTime(View rootView) {
        final SparseArray<CustomRadioButton> listRadio = new SparseArray<>();
        CustomRadioGroup group = (CustomRadioGroup) rootView.findViewById(R.id.rg_time);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < listTime.size(); i++) {
            View view = inflater.inflate(R.layout.item_radio_button, group, false);
            final CustomRadioButton radioButton = (CustomRadioButton) view.findViewById(R.id.rb);
            listRadio.put(i, radioButton);
            radioButton.setText(listTime.get(i));
            final int a = i;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (radioButton.isChecked()) {
                        for(int x=0; x<listRadio.size(); x++) {
                            if (x == a) {
                                listRadio.get(x).setChecked(true);
                                idTimeSelected = x;
                            } else {
                                listRadio.get(x).setChecked(false);
                            }
                        }
                    }
                }
            });
            group.addView(view);
        }
    }
}
