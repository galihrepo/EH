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
import net.eazyhealth.id.app.application.MyApplication;
import net.eazyhealth.id.app.custom.CustomRadioButton;
import net.eazyhealth.id.app.custom.CustomRadioGroup;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;
import net.eazyhealth.id.app.model.response.backendless.Mcu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GALIH ADITYO on 4/14/2016.
 */
public class DialogFragmentScheduleDateTime extends DialogFragment {

    private SparseArray<String> listDate = new SparseArray<>();
    private SparseArray<String> listTime = new SparseArray<>();
    private Integer idDateSelected = null;
    private Integer idTimeSelected = null;
    private CustomRadioGroup groupRadioDate;
    private CustomRadioGroup groupRadioTime;
    private Mcu mcu;
    private OnDialogTemplateTwoButton listener;

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
        initView(rootView);
        initData();
        addViewDate();
        addViewTime();
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
        groupRadioDate = (CustomRadioGroup) rootView.findViewById(R.id.rg_date);
        groupRadioTime = (CustomRadioGroup) rootView.findViewById(R.id.rg_time);

        CustomTextView btnCancel = (CustomTextView) rootView.findViewById(R.id.cancel_btn);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        CustomTextView btnOk = (CustomTextView) rootView.findViewById(R.id.ok_btn);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idDateSelected == null) {
                    CustomToast.setMessage(getActivity(), getString(R.string.please_choose_date));
                    return;
                }

                if (idTimeSelected == null) {
                    CustomToast.setMessage(getActivity(), getString(R.string.please_choose_time));
                    return;
                }

                if (mcu == null) {
                    CustomToast.setMessage(getActivity(), getString(R.string.medical_not_valid));
                    return;
                }

                List<Mcu> list;
                if (MyApplication.getInstance().getDataPreferences().getMedicalChoosen() == null) {
                    list = new ArrayList<Mcu>();
                } else {
                    list = MyApplication.getInstance().getDataPreferences().getMedicalChoosen();
                }
                list.add(mcu);
                MyApplication.getInstance().getDataPreferences().setMedicalChoosen(list);

                listener.onTemplateDialogYes();
                dismiss();
            }
        });
    }

    private void addViewDate() {
        final SparseArray<CustomRadioButton> listRadio = new SparseArray<>();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < listDate.size(); i++) {
            View view = inflater.inflate(R.layout.item_radio_button, groupRadioDate, false);
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

            groupRadioDate.addView(view);
        }
    }

    private void addViewTime() {
        final SparseArray<CustomRadioButton> listRadio = new SparseArray<>();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < listTime.size(); i++) {
            View view = inflater.inflate(R.layout.item_radio_button, groupRadioTime, false);
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
            groupRadioTime.addView(view);
        }
    }

    public void setMcu(Mcu mcu) {
        this.mcu = mcu;
    }

    public void setListener(OnDialogTemplateTwoButton listener) {
        this.listener = listener;
    }
}
