package net.eazyhealth.id.app.fragment.payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomEditText;
import net.eazyhealth.id.app.custom.CustomRadioButton;
import net.eazyhealth.id.app.custom.CustomTextView;

/**
 * Created by GALIH ADITYO on 4/22/2016.
 */
public class FragmentPayment extends Fragment {
    private CustomRadioButton rbMandiri;
    private CustomRadioButton rbBca;
    private CustomEditText etBankAccount;
    private CustomEditText etAccountName;
    private CustomEditText etAccountNumber;
    private CustomTextView tvPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rbMandiri = (CustomRadioButton) view.findViewById(R.id.rb_mandiri);
        rbBca = (CustomRadioButton) view.findViewById(R.id.rb_bca);
        etBankAccount = (CustomEditText) view.findViewById(R.id.et_bank_account);
        etAccountName = (CustomEditText) view.findViewById(R.id.et_account_name);
        etAccountNumber = (CustomEditText) view.findViewById(R.id.et_account_number);
        tvPrice = (CustomTextView) view.findViewById(R.id.price);
    }

}