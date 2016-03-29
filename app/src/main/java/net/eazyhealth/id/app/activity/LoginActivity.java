package net.eazyhealth.id.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomButton;
import net.eazyhealth.id.app.custom.CustomCheckBox;
import net.eazyhealth.id.app.custom.CustomEditText;

public class LoginActivity extends CustomAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        CustomButton btnLogin = (CustomButton) findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        final CustomEditText etPassword = (CustomEditText) findViewById(R.id.password_et);
        CustomCheckBox cbShowPassword = (CustomCheckBox) findViewById(R.id.show_password_cb);
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
    }
}
