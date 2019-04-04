package com.MVP.team5.universenews.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.model.SettingsModel;

public class SplashScreen extends AppCompatActivity {

    SettingsModel settingsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Utilities.setStatusBarColor(this, Utilities.getTheme(this));

        settingsModel = new SettingsModel(
                Utilities.getTheme(getApplicationContext()),
                Utilities.getFont(getApplicationContext()),
                Utilities.getNight(getApplicationContext())
        );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.putExtra("setting", settingsModel);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        }, 1000);
    }
}
