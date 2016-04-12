package net.eazyhealth.id.app.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import net.danlew.android.joda.DateUtils;
import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.DatePickerActivity;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;

import org.joda.time.DateTime;

/**
 * Created by GALIH ADITYO on 4/9/2016.
 */
public class FragmentItemDetail extends Fragment implements RippleViewAndroidM.OnRippleCompleteListener {

    private CustomRippleView btnAdd;
    private final int ADD_ONE_DAY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
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
//            DialogPlus dialog = DialogPlus.newDialog(getActivity())
//                    .setAdapter(new ArrayAdapter<>(getActivity(), R.layout.content_dialog, new String[]{"07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"}))
//                    .setCancelable(true)
//                    .setGravity(Gravity.BOTTOM)
//                    .setContentBackgroundResource(R.color.colorPrimaryDark)
//                    .setInAnimation(R.anim.slide_in_bottom)
//                    .setOutAnimation(R.anim.slide_out_bottom)
//                    .setHeader(R.layout.header_dialog)
//                    .setOnItemClickListener(new OnItemClickListener() {
//                        @Override
//                        public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
//                            CustomToast.setMessage(getActivity(), position+"");
//                        }
//                    })
//                    .create();
//            dialog.show();

            startActivityForResult(new Intent(getActivity(), DatePickerActivity.class), 0);
        }
    }
}
