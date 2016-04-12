package net.eazyhealth.id.app.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.thinkcool.circletextimageview.CircleTextImageView;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.fragment.FragmentItemDetail;
import net.eazyhealth.id.app.helper.WidgetHelper;

public class DetailTemplateActivity extends CustomAppCompatActivity {

    private CustomTextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_activity_for_result);

        initView();
        bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentItemDetail()).commit();
    }

    private void bundle() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("title")) {
                tvTitle.setText(getIntent().getExtras().getString("title"));
            }
        }
    }

    private void initView() {
        tvTitle = (CustomTextView) findViewById(R.id.title_tv);
        includeView();
    }

    private void includeView() {
        View includeToolbar = findViewById(R.id.include_toolbar);
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

        notificationShoppingCart(includeToolbar);
    }

    private void notificationShoppingCart(View templateToolbar) {
        RelativeLayout shoppingCartButton = (RelativeLayout) templateToolbar.findViewById(R.id.shopping_cart);
        CircleTextImageView notificationNumber = (CircleTextImageView) templateToolbar.findViewById(R.id.notification_number);
        WidgetHelper.setNotificationNumber(this, notificationNumber, shoppingCartButton);
    }

    @Override
    public void onBackPressed() {
        backToHome();
    }

    private void backToHome() {
//        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(i);
//        finish();

        setResult(RESULT_OK);
        finish();
    }
}