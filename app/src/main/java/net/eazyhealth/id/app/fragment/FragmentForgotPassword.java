package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomEditText;
import net.eazyhealth.id.app.custom.CustomProgressDialog;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;

/**
 * Created by GALIH ADITYO on 5/1/2016.
 */
public class FragmentForgotPassword extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final CustomEditText etEmail = (CustomEditText) view.findViewById(R.id.et_email);
        final View snackbar = view.findViewById(R.id.sb);
        CustomRippleView btnReset = (CustomRippleView) view.findViewById(R.id.btn_reset);
        btnReset.setOnRippleCompleteListener(new RippleViewAndroidM.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleViewAndroidM rippleView) {
                if (etEmail.getText().toString().trim().length() == 0) {
                    CustomToast.showSnackbar(snackbar, "Email still empty");
                    etEmail.requestFocus();
                    etEmail.invalidate();
                    return;
                }

                final CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity());
                if (customProgressDialog != null && !customProgressDialog.isShowing()) {
                    customProgressDialog.show();
                }

                Backendless.UserService.restorePassword(etEmail.getText().toString().trim(), new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        if (customProgressDialog != null && customProgressDialog.isShowing()) {
                            customProgressDialog.dismiss();
                        }

                        CustomToast.showSnackbar(snackbar, "Reset password succeed, check your email");
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        if (customProgressDialog != null && customProgressDialog.isShowing()) {
                            customProgressDialog.dismiss();
                        }

                        try {
                            CustomToast.showSnackbar(snackbar, fault.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
