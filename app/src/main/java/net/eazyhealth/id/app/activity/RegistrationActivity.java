package net.eazyhealth.id.app.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.fragment.FragmentForgotPassword;
import net.eazyhealth.id.app.fragment.FragmentRegistration;

public class RegistrationActivity extends CustomAppCompatActivity {

    public static final int REQUEST_REGISTRATION = 0;
    public static final int REQUEST_RESET_PASSWORD = 1;
    public static final String REQUEST_BUNDLE = RegistrationActivity.class.getSimpleName() + "bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_activity_for_result);
        includeView();
        initVariable();
    }

    private void initVariable() {
        if (getIntent().getIntExtra(REQUEST_BUNDLE, 0) == REQUEST_REGISTRATION) {
            getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentRegistration()).commit();
        } else if (getIntent().getIntExtra(REQUEST_BUNDLE, 0) == REQUEST_RESET_PASSWORD) {
            getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentForgotPassword()).commit();
        }
    }

    private void includeView() {
        View includeToolbar = findViewById(R.id.include_toolbar);

        CustomTextView tvTitle = (CustomTextView) includeToolbar.findViewById(R.id.title_tv);
        if (getIntent().getIntExtra(REQUEST_BUNDLE, 0) == REQUEST_REGISTRATION) {
            tvTitle.setText(getResources().getString(R.string.registration));
        } else if (getIntent().getIntExtra(REQUEST_BUNDLE, 0) == REQUEST_RESET_PASSWORD) {
            tvTitle.setText(getResources().getString(R.string.forgot_password));
        }

        hideCart(includeToolbar);

        Toolbar toolbar = (Toolbar) includeToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    private void hideCart(View includeToolbar) {
        RelativeLayout btnCart = (RelativeLayout) includeToolbar.findViewById(R.id.shopping_cart);
        btnCart.setVisibility(View.GONE);
    }

    private void backToHome() {
        setResult(RESULT_OK);
        finish();
    }
}
