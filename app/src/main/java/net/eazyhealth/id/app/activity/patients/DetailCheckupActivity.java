package net.eazyhealth.id.app.activity.patients;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomTextView;

public class DetailCheckupActivity extends CustomAppCompatActivity {

    private CustomTextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_activity_for_result);

        initView();
        bundle();
    }

    private void bundle() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("title")) {
                tvTitle.setText(getIntent().getExtras().getString("title"));
            }
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        tvTitle = (CustomTextView) findViewById(R.id.title_tv);
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