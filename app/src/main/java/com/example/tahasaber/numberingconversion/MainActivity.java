package com.example.tahasaber.numberingconversion;


import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tahasaber.numberingconversion.fragments.AboutFragment;
import com.example.tahasaber.numberingconversion.fragments.BinaryRepresentationFragment;
import com.example.tahasaber.numberingconversion.fragments.FloatingPointNotationFragment;
import com.example.tahasaber.numberingconversion.fragments.HelpFragment;
import com.example.tahasaber.numberingconversion.fragments.NumberConverterFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private TextView appName;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;

    private NumberConverterFragment numberConverterFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = (TextView) findViewById(R.id.app_name);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            appName.setText(savedInstanceState.getString("app"));

        }

        numberConverterFragment = new NumberConverterFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, numberConverterFragment, "number_tag").commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("app", appName.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.number) {

            appName.setText("Number Converter");
            NumberConverterFragment myFragment = (NumberConverterFragment) getFragmentManager().findFragmentByTag("number_tag");
            if (myFragment == null) {
                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new NumberConverterFragment(), "number_tag").commit();
                //  Toast.makeText(getApplicationContext(), "Number Converter", Toast.LENGTH_LONG).show();
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        } else if (id == R.id.binary) {

            appName.setText("Binary Representation");
            BinaryRepresentationFragment myFragment = (BinaryRepresentationFragment) getFragmentManager().findFragmentByTag("binary_tag");
            if (myFragment == null) {

                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new BinaryRepresentationFragment(), "binary_tag").commit();
                //  Toast.makeText(getApplicationContext(), "Binary Representation", Toast.LENGTH_LONG).show();
                navigationView.getMenu().getItem(1).setChecked(true);
            }


        } else if (id == R.id.floating) {

            appName.setText("Floating Point Formats");
            FloatingPointNotationFragment myFragment = (FloatingPointNotationFragment) getFragmentManager().findFragmentByTag("floating_tag");
            if (myFragment == null) {

                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new FloatingPointNotationFragment(), "floating_tag").commit();
                //   Toast.makeText(getApplicationContext(), "Floating Point Representation", Toast.LENGTH_LONG).show();
                navigationView.getMenu().getItem(2).setChecked(true);
            }
        } else if (id == R.id.help) {

            appName.setText("Help");
            HelpFragment myFragment = (HelpFragment) getFragmentManager().findFragmentByTag("help_tag");
            if (myFragment == null) {

                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new HelpFragment(), "help_tag").commit();
                //   Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_LONG).show();

                navigationView.getMenu().getItem(3).setChecked(true);
            }
        } else if (id == R.id.about) {

            appName.setText("About");
            AboutFragment myFragment = (AboutFragment) getFragmentManager().findFragmentByTag("about_tag");
            if (myFragment == null) {

                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new AboutFragment(), "about_tag").commit();
                //    Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show();

                navigationView.getMenu().getItem(4).setChecked(true);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            drawer.closeDrawer(GravityCompat.START);
        } else {

            NumberConverterFragment myFragment = (NumberConverterFragment) getFragmentManager().findFragmentByTag("number_tag");
            if (myFragment != null && myFragment.isVisible()) {
                this.finish();
            } else {
                appName.setText("Number Converter");
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment_container, numberConverterFragment, "number_tag");
                ft.commit();
                Toast.makeText(getApplicationContext(), "Click again to exit...", Toast.LENGTH_LONG).show();
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        }


    }
}
