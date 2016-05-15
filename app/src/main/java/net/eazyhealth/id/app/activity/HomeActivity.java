package net.eazyhealth.id.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.thinkcool.circletextimageview.CircleTextImageView;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.custom.CustomRippleView;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.custom.RippleViewAndroidM;
import net.eazyhealth.id.app.dialog.DialogFragmentTemplateTwoButton;
import net.eazyhealth.id.app.fragment.FragmentDashboard;
import net.eazyhealth.id.app.fragment.FragmentLogin;
import net.eazyhealth.id.app.fragment.patients.FragmentPatientsProfile;
import net.eazyhealth.id.app.fragment.patients.FragmentPatientsSchedule;
import net.eazyhealth.id.app.helper.WidgetHelper;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;
import net.eazyhealth.id.app.preferences.DataPreferences;

public class HomeActivity extends CustomAppCompatActivity implements RippleViewAndroidM.OnRippleCompleteListener {

    private FrameLayout placeholder;
    private DataPreferences dataPreferences;
    //    private NavigationView navigationView;
    private Toolbar toolbar;
    private View templateToolbar;

    private CustomRippleView btnDashboard;
    private CustomRippleView btnLogin;
    private CustomRippleView btnSchedule;
    private CustomRippleView btnHistory;
    private CustomRippleView btnProfile;
    private CustomRippleView btnLogout;

    private CustomTextView tvTitle;

    @Override
    protected void onResume() {
        super.onResume();
        notificationShoppingCart(templateToolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataPreferences = new DataPreferences(this);

        includeView();
        navigationView();
    }

    private void navigationView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View sidemenu = findViewById(R.id.sidemenu);
        btnDashboard = (CustomRippleView) sidemenu.findViewById(R.id.rv_dashboard);
        btnLogin = (CustomRippleView) sidemenu.findViewById(R.id.rv_login);
        btnSchedule = (CustomRippleView) sidemenu.findViewById(R.id.rv_schedule);
        btnHistory = (CustomRippleView) sidemenu.findViewById(R.id.rv_history);
        btnProfile = (CustomRippleView) sidemenu.findViewById(R.id.rv_profile);
        btnLogout = (CustomRippleView) sidemenu.findViewById(R.id.rv_logout);

        btnDashboard.setOnRippleCompleteListener(this);
        btnLogin.setOnRippleCompleteListener(this);
        btnSchedule.setOnRippleCompleteListener(this);
        btnHistory.setOnRippleCompleteListener(this);
        btnProfile.setOnRippleCompleteListener(this);
        btnLogout.setOnRippleCompleteListener(this);

//        if (dataPreferences.getUsername() == null) {
//            navigationView.inflateMenu(R.menu.sidemenu);
//        } else {
//            navigationView.inflateMenu(R.menu.sidemenu_patients);
//        }
        menuDashboard();
    }

    private void includeView() {
        View include = findViewById(R.id.include);
        View insideInclude = include.findViewById(R.id.include);
        templateToolbar = include.findViewById(R.id.include_toolbar);

        toolbar = (Toolbar) templateToolbar.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tvTitle = (CustomTextView) templateToolbar.findViewById(R.id.title_tv);
        placeholder = (FrameLayout) insideInclude.findViewById(R.id.placeholder);
    }

    private void notificationShoppingCart(View templateToolbar) {
        RelativeLayout shoppingCartButton = (RelativeLayout) templateToolbar.findViewById(R.id.shopping_cart);
        CircleTextImageView notificationNumber = (CircleTextImageView) templateToolbar.findViewById(R.id.notification_number);
        WidgetHelper.setNotificationNumber(this, notificationNumber, shoppingCartButton);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (dataPreferences.getPassword() != null) {
            getMenuInflater().inflate(R.menu.home_patients, menu);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void menuDashboard() {
        //noinspection deprecation
        btnDashboard.setBackgroundColor(getResources().getColor(R.color.grey_3));
        tvTitle.setText(getString(R.string.menu_dashboard));

        getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentDashboard()).commit();
    }

    @Override
    public void onComplete(final RippleViewAndroidM rippleView) {
        btnDashboard.setBackgroundColor(Color.WHITE);
        btnLogin.setBackgroundColor(Color.WHITE);
        btnSchedule.setBackgroundColor(Color.WHITE);
        btnHistory.setBackgroundColor(Color.WHITE);
        btnProfile.setBackgroundColor(Color.WHITE);
        btnLogout.setBackgroundColor(Color.WHITE);

        //noinspection deprecation
        rippleView.setBackgroundColor(getResources().getColor(R.color.grey_3));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        new CountDownTimer(250, 250) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (rippleView == btnDashboard) {
                    tvTitle.setText(getString(R.string.menu_dashboard));
                    getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentDashboard()).commit();

                } else if (rippleView == btnLogin) {
                    tvTitle.setText(getString(R.string.menu_login));
                    getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentLogin()).commit();

                } else if (rippleView == btnSchedule) {
                    tvTitle.setText(getString(R.string.menu_schedule));
                    getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentPatientsSchedule()).commit();

                } else if (rippleView == btnHistory) {
                    tvTitle.setText(getString(R.string.menu_history));

                } else if (rippleView == btnProfile) {
                    tvTitle.setText(getString(R.string.menu_profile));
                    getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentPatientsProfile()).commit();

                } else if (rippleView == btnLogout) {
                    final DialogFragmentTemplateTwoButton dialog = new DialogFragmentTemplateTwoButton();
                    dialog.setTitle("Logout");
                    dialog.setContent("Logout dari aplikasi?");
                    dialog.setButtonNoText("Tidak");
                    dialog.setButtonYesText("Ya");
                    dialog.setListener(new OnDialogTemplateTwoButton() {
                        @Override
                        public void onTemplateDialogNo() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onTemplateDialogYes() {
                            dataPreferences.setUsername(null);
                            dataPreferences.setPassword(null);
                            dialog.dismiss();
                            recreate();
                            //                    finish();
                        }
                    });
                    dialog.show(getSupportFragmentManager(), "");
                }
            }
        }.start();
    }
}
