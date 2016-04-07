package net.eazyhealth.id.app.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.fragment.FragmentListData;

public class ListDataActivity extends CustomAppCompatActivity {

    public static final String BUNDLE_TITLE = ListDataActivity.class.getSimpleName() + "title";

    private FrameLayout placeholder;

    private String strTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_activity_for_result);
        bundle();
        initView();
        initVariable();
    }

    private void initVariable() {
        getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentListData()).commit();
    }

    private void bundle() {
        if (getIntent().getExtras() != null) {
            strTitle = getIntent().getStringExtra(BUNDLE_TITLE);
        }
    }

    private void initView() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(0);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        CustomTextView tvTitle = (CustomTextView) findViewById(R.id.title_tv);
        tvTitle.setText(strTitle);

        placeholder = (FrameLayout) findViewById(R.id.placeholder);
    }
}
