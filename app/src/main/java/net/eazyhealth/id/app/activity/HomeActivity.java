package net.eazyhealth.id.app.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.dialog.DialogFragmentTemplateTwoButton;
import net.eazyhealth.id.app.fragment.FragmentDashboard;
import net.eazyhealth.id.app.fragment.FragmentLogin;
import net.eazyhealth.id.app.fragment.patients.FragmentPatientsProfile;
import net.eazyhealth.id.app.fragment.patients.FragmentPatientsSchedule;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;
import net.eazyhealth.id.app.preferences.AccountPreferences;

public class HomeActivity extends CustomAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout placeholder;
    private AccountPreferences accountPreferences;
    private NavigationView navigationView;

    @Override
    protected void onResume() {
        super.onResume();
        if (accountPreferences.getUsername() == null) {
            navigationView.inflateMenu(R.menu.sidemenu);
        } else {
            navigationView.inflateMenu(R.menu.sidemenu_patients);
        }
        menuDashboard();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        accountPreferences = new AccountPreferences(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        includeView();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void includeView() {
        View include = findViewById(R.id.include);
        View insideInclude = include.findViewById(R.id.include);
        placeholder = (FrameLayout) insideInclude.findViewById(R.id.placeholder);
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
        if (accountPreferences.getPassword() != null) {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.sidemenu_schedule) {
            getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentPatientsSchedule()).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.sidemenu_profile) {
            getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentPatientsProfile()).commit();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.sidemenu_logout) {
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
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.show(getSupportFragmentManager(), "");
        } else if (id == R.id.sidemenu_login) {
            getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentLogin()).commit();
        } else if (id == R.id.sidemenu_dashboard) {
            getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentDashboard()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void menuDashboard() {
        navigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentDashboard()).commit();
    }
}
