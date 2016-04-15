package net.eazyhealth.id.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAutoCompleteTextView;
import net.eazyhealth.id.app.custom.CustomButton;
import net.eazyhealth.id.app.custom.CustomCheckBox;
import net.eazyhealth.id.app.custom.CustomEditText;
import net.eazyhealth.id.app.custom.CustomToast;
import net.steamcrafted.loadtoast.LoadToast;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class FragmentLogin extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
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

        CustomButton btnLogin = (CustomButton) view.findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().trim().length() == 0) {
                    CustomToast.showSnackbar(snackbar, "Username still empty");
                    return;
                }

                if (etPassword.getText().toString().trim().length() == 0) {
                    CustomToast.showSnackbar(snackbar, "Password still empty");
                    return;
                }

//                DataPreferences accountPreferences = new DataPreferences(getActivity());
//                accountPreferences.setUsername(etUsername.getText().toString());
//                accountPreferences.setPassword(etPassword.getText().toString());

                BackendlessUser user = new BackendlessUser();
                user.setEmail(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());

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
//                        CustomToast.setMessage(getActivity(), response.toString());
                        load.success();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().recreate();
                            }
                        }, 2000);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        super.handleFault(fault);
                        CustomToast.setMessage(getActivity(), fault.toString());
                        load.error();
                    }
                });
            }
        });

    }
}
