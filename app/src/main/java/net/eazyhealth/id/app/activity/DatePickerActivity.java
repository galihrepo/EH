package net.eazyhealth.id.app.activity;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.thinkcool.circletextimageview.CircleTextImageView;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.fragment.FragmentDatePicker;
import net.eazyhealth.id.app.fragment.FragmentListData;
import net.eazyhealth.id.app.helper.WidgetHelper;

public class DatePickerActivity extends CustomAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_activity_for_result);
        initView();
        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentDatePicker()).commit();
    }

    private void initView() {
        toolbar();
    }

    private void toolbar() {
        View include = findViewById(R.id.include_toolbar);

        Toolbar toolbar = (Toolbar) include.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        notificationShoppingCart(include);
    }

    private void notificationShoppingCart(View templateToolbar) {
        RelativeLayout shoppingCartButton = (RelativeLayout) templateToolbar.findViewById(R.id.shopping_cart);
        shoppingCartButton.setVisibility(View.GONE);
    }
}
