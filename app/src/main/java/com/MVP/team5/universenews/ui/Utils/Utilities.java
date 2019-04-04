package com.MVP.team5.universenews.ui.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.MVP.team5.universenews.R;

public class Utilities {
    public static void saveFont(Context context, int font) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("FontSize", font);
        editor.apply();
    }

    public static int getFont(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("FontSize", 30);
    }

    public static void saveTheme(Context context, int theme) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Theme", theme);
        editor.apply();
    }

    public static int getTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("Theme", Color.parseColor("#00FF00"));
    }

    public static void saveNight(Context context, boolean night) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Night", night);
        editor.apply();
    }

    public static boolean getNight(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Night", false);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        Window window = activity.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        //window.setStatusBarColor(ContextCompat.getColor(activity, color));
        window.setNavigationBarColor(color);

    }
}
