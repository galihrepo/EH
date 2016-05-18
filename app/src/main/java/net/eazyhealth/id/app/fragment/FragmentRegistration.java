package net.eazyhealth.id.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomEditText;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
import net.eazyhealth.id.app.model.BackendlessProperties;
import net.steamcrafted.loadtoast.LoadToast;

/**
 * Created by GALIH ADITYO on 5/1/2016.
 */
public class FragmentRegistration extends Fragment {

    private CustomEditText etName;
    private CustomEditText etEmail;
    private CustomEditText etPassword;
    private CustomEditText etConfirmPassword;
    private View snackbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        snackbar = view.findViewById(R.id.sb);
        etName = (CustomEditText) view.findViewById(R.id.et_name);
        etEmail = (CustomEditText) view.findViewById(R.id.et_email);
        etPassword = (CustomEditText) view.findViewById(R.id.et_password);
        etConfirmPassword = (CustomEditText) view.findViewById(R.id.et_confirm_password);

        CustomRippleView btnRegister = (CustomRippleView) view.findViewById(R.id.btn_register);
        btnRegister.setOnRippleCompleteListener(new RippleViewAndroidM.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleViewAndroidM rippleView) {
                register();
            }
        });
    }

    private void register() {
        if (etName.getText().toString().trim().length() == 0) {
            CustomToast.showSnackbar(snackbar, "Name still empty");
            etName.requestFocus();
            etName.invalidate();
            return;
        }

        if (etEmail.getText().toString().trim().length() == 0) {
            CustomToast.showSnackbar(snackbar, "Email still empty");
            etEmail.requestFocus();
            etEmail.invalidate();
            return;
        }

        if (etPassword.getText().toString().trim().length() == 0) {
            CustomToast.showSnackbar(snackbar, "Password still empty");
            etPassword.requestFocus();
            etPassword.invalidate();
            return;
        }

        if (etConfirmPassword.getText().toString().trim().length() == 0) {
            CustomToast.showSnackbar(snackbar, "Confirm password still empty");
            etConfirmPassword.requestFocus();
            etConfirmPassword.invalidate();
            return;
        }

        if (!etPassword.getText().toString().trim().equals(etConfirmPassword.getText().toString().trim())) {
            CustomToast.showSnackbar(snackbar, "Password not match");
            etPassword.requestFocus();
            etPassword.invalidate();
            return;
        }

        BackendlessUser user = new BackendlessUser();
        user.setEmail(etEmail.getText().toString().trim());
        user.setPassword(etPassword.getText().toString().trim());

        user.setProperty(BackendlessProperties.USERS_NAME, etName.getText().toString().trim());
        user.setProperty(BackendlessProperties.USERS_PASSWORD, etPassword.getText().toString().trim());

        final LoadToast load = new LoadToast(getActivity());
        load.setText("Sending data..");
        load.setTranslationY(100);
        load.setTextColor(Color.WHITE);
        load.setBackgroundColor(getResources().getColor(R.color.green_6));
        load.setProgressColor(getResources().getColor(R.color.colorPrimaryDark));
        load.show();

        Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                load.success();
                try {
                    CustomToast.showSnackbar(snackbar, "Registration succeed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
//                super.handleFault(fault);
                load.error();
                try {
                    CustomToast.showSnackbar(snackbar, fault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}