package net.eazyhealth.id.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.HomeActivity;
import net.eazyhealth.id.app.activity.RegistrationActivity;
import net.eazyhealth.id.app.custom.CustomAutoCompleteTextView;
import net.eazyhealth.id.app.custom.CustomCheckBox;
import net.eazyhealth.id.app.custom.CustomEditText;
import net.eazyhealth.id.app.custom.CustomProgressDialog;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.custom.CustomToast;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class FragmentLogin extends Fragment {

    private final boolean stayLoggedIn = true;
    private HomeActivity parentActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        parentActivity = ((HomeActivity) getActivity());
        initView(view);
        return view;
    }

    private void initView(View view) {
        final CustomAutoCompleteTextView etUsername = (CustomAutoCompleteTextView) view.findViewById(R.id.username_et);
        final View snackbar = view.findViewById(R.id.sb);
        final CustomEditText etPassword = (CustomEditText) view.findViewById(R.id.password_et);

        CustomCheckBox cbShowPassword = (CustomCheckBox) view.findViewById(R.id.show_password_cb);
        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    etPassword.setTransformationMethod(null);
                }
                etPassword.setSelection(etPassword.length());
            }
        });

        CustomRippleView btnLogin = (CustomRippleView) view.findViewById(R.id.login_btn);
        btnLogin.setOnRippleCompleteListener(new RippleViewAndroidM.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleViewAndroidM rippleView) {
                if (etUsername.getText().toString().trim().length() == 0) {
                    CustomToast.showSnackbar(snackbar, "Username still empty");
                    etUsername.requestFocus();
                    etUsername.invalidate();
                    return;
                }

                if (etPassword.getText().toString().trim().length() == 0) {
                    CustomToast.showSnackbar(snackbar, "Password still empty");
                    etPassword.requestFocus();
                    etPassword.invalidate();
                    return;
                }

                final CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity());
                if (customProgressDialog != null && !customProgressDialog.isShowing()) {
                    customProgressDialog.show();
                }

                Backendless.UserService.login(
                        etUsername.getText().toString().trim(),
                        etPassword.getText().toString().trim(),
                        new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                if (customProgressDialog != null && customProgressDialog.isShowing()) {
                                    customProgressDialog.dismiss();
                                }

                                CustomToast.showSnackbar(snackbar, "Login succeed");
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
                        },
                        true
                );
            }
        });

        CustomRippleView btnLoginFb = (CustomRippleView) view.findViewById(R.id.btn_login_fb);
        btnLoginFb.setOnRippleCompleteListener(new RippleViewAndroidM.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleViewAndroidM rippleView) {

            }
        });

        CustomTextView btnRegister = (CustomTextView) view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RegistrationActivity.class);
                i.putExtra(RegistrationActivity.REQUEST_BUNDLE, RegistrationActivity.REQUEST_REGISTRATION);
                startActivityForResult(i, RegistrationActivity.REQUEST_REGISTRATION);
            }
        });

        CustomTextView btnForgotPw = (CustomTextView) view.findViewById(R.id.btn_forgot_password);
        btnForgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RegistrationActivity.class);
                i.putExtra(RegistrationActivity.REQUEST_BUNDLE, RegistrationActivity.REQUEST_RESET_PASSWORD);
                startActivityForResult(i, RegistrationActivity.REQUEST_RESET_PASSWORD);
            }
        });
    }
}
