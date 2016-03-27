package net.eazyhealth.id.app.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;
import net.eazyhealth.id.app.dialog.DialogFragmentTemplateTwoButton;
import net.eazyhealth.id.app.fragment.patients.FragmentPatientsProfile;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;

public class HomePatientsActivity extends CustomAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        includeView();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_patient, menu);

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.home_patient, menu);
//
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

//        if (id == R.id.search) {
////            CustomToast.setMessage(getApplicationContext(), "search");
//            startActivityForResult(new Intent(getApplicationContext(), SearchActivity.class), 69);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.sidemenu_profile) {
            getSupportFragmentManager().beginTransaction().replace(placeholder.getId(), new FragmentPatientsProfile()).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
