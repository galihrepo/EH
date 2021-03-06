package net.eazyhealth.id.app.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.fragment.payment.FragmentPayment;

public class PaymentActivity extends CustomAppCompatActivity {

    private View includeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_activity_for_result);
        includeView();
        initVariable();
    }

    private void initVariable() {
        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentPayment()).commit();
    }

    private void includeView() {
        includeToolbar = findViewById(R.id.include_toolbar);

        CustomTextView tvTitle = (CustomTextView) includeToolbar.findViewById(R.id.title_tv);
        tvTitle.setText(getString(R.string.payment_method));

        hideCart(includeToolbar);

        // remove shadow elevation
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(0);
        }

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
