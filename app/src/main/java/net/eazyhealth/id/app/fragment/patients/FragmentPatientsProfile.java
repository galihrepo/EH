package net.eazyhealth.id.app.fragment.patients;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.squareup.picasso.Picasso;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CircleTransform;
import net.eazyhealth.id.app.custom.CustomEditText;
import net.eazyhealth.id.app.custom.CustomImageView;
import net.eazyhealth.id.app.dialog.DialogFragmentOption;
import net.eazyhealth.id.app.helper.DateHelper;
import net.eazyhealth.id.app.helper.DimensionHelper;
import net.eazyhealth.id.app.helper.WidgetManipulation;
import net.eazyhealth.id.app.interfaces.OnOptionInterface;

/**
 * Created by GALIH ADITYO on 3/23/2016.
 */
public class FragmentPatientsProfile extends Fragment implements View.OnFocusChangeListener, View.OnClickListener {

    private DatePickerDialog dialogDate;
    private CustomImageView ivProfile;
    private CustomEditText btnDatePicker;
    private CustomEditText btnBloodType;
    private CustomEditText btnGender;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patients_profile, container, false);
        initView(view);
        initVariable();
        return view;
    }

    private void initView(View view) {
        ivProfile = (CustomImageView) view.findViewById(R.id.profile_picture_iv);
        btnDatePicker = (CustomEditText) view.findViewById(R.id.birthday_et);
        btnBloodType = (CustomEditText) view.findViewById(R.id.blood_type_et);
        btnGender = (CustomEditText) view.findViewById(R.id.gender_et);
    }

    private void initVariable() {
        initDatePicker();
        btnDatePicker.setInputType(InputType.TYPE_NULL);
        btnDatePicker.setOnFocusChangeListener(this);
        btnDatePicker.setOnClickListener(this);

        btnBloodType.setInputType(InputType.TYPE_NULL);
        btnBloodType.setOnFocusChangeListener(this);
        btnBloodType.setOnClickListener(this);

        btnGender.setInputType(InputType.TYPE_NULL);
        btnGender.setOnFocusChangeListener(this);
        btnGender.setOnClickListener(this);

        int size = DimensionHelper.getScreenWidth(getActivity())/4;
        Picasso.with(getActivity())
                .load(R.drawable.profile_picture)
                .resize(size, size)
                .transform(new CircleTransform())
                .into(ivProfile);
    }

    private void initDatePicker() {
        // spinner mode
        // remove style if want calendar shown android.R.style.Theme_Holo_Light_Dialog
        dialogDate = new DatePickerDialog(getActivity(),R.style.custom_date_picker_dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                int addedMonth = month + 1;
                String strMonth = "";
                String strDay = "";
                if (addedMonth < 10) {
                    strMonth = "0"+addedMonth;
                } else {
                    strMonth = ""+addedMonth;
                }
                if (day < 10) {
                    strDay = "0"+day;
                } else {
                    strDay = ""+day;
                }
                strMonth = DateHelper.getMonthName(addedMonth);
                btnDatePicker.setText(strDay+" "+strMonth+" "+year);
            }
        }, 1980, 0, 1);
        dialogDate.getDatePicker().setCalendarViewShown(false);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            if (dialogDate != null && !dialogDate.isShowing()) {
                showDatePicker();
            }
        } else if (v == btnBloodType) {
            showDialogBlood();
        } else if (v == btnGender) {
            showDialogGender();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (v == btnDatePicker) {
                if (dialogDate != null && !dialogDate.isShowing()) {
                    showDatePicker();
                }
            } else if (v == btnBloodType) {
                showDialogBlood();
            } else if (v == btnGender) {
                showDialogGender();
            }
        }
    }

    private void showDatePicker() {
        WidgetManipulation.hideKeyboard(getActivity(), btnDatePicker);
        dialogDate.show();
    }

    private void showDialogBlood() {
        WidgetManipulation.hideKeyboard(getActivity(), btnBloodType);

        SparseArray<String> data = new SparseArray<>();
        data.put(0, "A");
        data.put(1, "B");
        data.put(2, "AB");
        data.put(3, "O");

        final DialogFragmentOption dialog = new DialogFragmentOption();
        dialog.setTitle("Golongan Darah");
        dialog.setAdapter(data);
        dialog.setListener(new OnOptionInterface() {
            @Override
            public void onResult(String result) {
                dialog.dismiss();
                btnBloodType.setText(result);
            }
        });
        dialog.show(getFragmentManager(), "");
    }

    private void showDialogGender() {
        WidgetManipulation.hideKeyboard(getActivity(), btnBloodType);

        SparseArray<String> data = new SparseArray<>();
        data.put(0, "Pria");
        data.put(1, "Wanita");

        final DialogFragmentOption dialog = new DialogFragmentOption();
        dialog.setTitle("Jenis Kelamin");
        dialog.setAdapter(data);
        dialog.setListener(new OnOptionInterface() {
            @Override
            public void onResult(String result) {
                dialog.dismiss();
                btnGender.setText(result);
            }
        });
        dialog.show(getFragmentManager(), "");
    }
}
