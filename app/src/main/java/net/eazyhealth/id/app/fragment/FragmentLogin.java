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
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class FragmentLogin extends Fragment {

    private final boolean stayLoggedIn = true;
    private HomeActivity parentActivity;
    private CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        parentActivity = ((HomeActivity) getActivity());
        initView(view);
        initFacebook();
        return view;
    }

    private void initFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String gender = object.getString("gender");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
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
                loginFb();
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

    private void loginFb() {

        LoginManager.getInstance()
                .setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK)
                // used in fragment using 'this' keyword instead of 'getActivity'
                .logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
