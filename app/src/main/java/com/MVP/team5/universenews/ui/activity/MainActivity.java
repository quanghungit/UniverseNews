package com.MVP.team5.universenews.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Toast;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.fragment.AboutFragment;
import com.MVP.team5.universenews.ui.fragment.MainFragment;
import com.MVP.team5.universenews.ui.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //ft.replace(R.id.flContents,new MainFragment()).commit();
        ft.replace(R.id.flContents, new MainFragment()).commit();

        boolean checkNetwork = isOnline();
        if (checkNetwork == false) {
            Toast.makeText(this,getResources().getString(R.string.check_network).toString(),Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onBackPressed() {
        try {
            final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flContents);
            MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("Universe News");
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (fragment instanceof MainFragment) {
                    exit();
                    //super.onBackPressed();
                } else {
                    onBackStackChanged();
                    super.onBackPressed();
                }
            }
        } catch (Exception e) {

        }

    }


    public void onBackStackChanged() {
        int lastBackStackEntryCount = getSupportFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry lastBackStackEntry =
                getSupportFragmentManager().getBackStackEntryAt(lastBackStackEntryCount);

        setTitle(lastBackStackEntry.getName());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mainMenu) {
            changeFragment(new MainFragment());
            setTitle(R.string.app_name);
        }
        else if (id == R.id.nav_settings) {
            changeFragment(new SettingsFragment());
            setTitle("Settings");
        }
        else if (id == R.id.nav_about) {
            changeFragment(new AboutFragment());
            setTitle("About");
        } else if (id == R.id.nav_exit) {
            exit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContents, fragment).addToBackStack(getTitle().toString()).commit();
    }

    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.exit_icon);
        builder.setTitle(Html.fromHtml("<font color='#40C4FF'>Exit</font>"));
        builder.setMessage(Html.fromHtml(getString(R.string.exit_text).toString()));
        builder.setPositiveButton(Html.fromHtml("<font color='#40C4FF'>Yes</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        builder.setNegativeButton(Html.fromHtml("<font color='#40C4FF'>No</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
