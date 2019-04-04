package com.MVP.team5.universenews.ui.model;

import android.graphics.Color;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    int themeApp;
    int fontSize;
    int nightShift;

    public SettingsModel(int themeApp, int fontSize, boolean nightShift) {
        this.themeApp = themeApp;
        this.fontSize = fontSize;
        if (nightShift) {
            this.nightShift = Color.parseColor("#d1cca5")
        }
    }

    public int getThemeApp() {
        return themeApp;
    }

    public void setThemeApp(int themeApp) {
        this.themeApp = themeApp;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int isNightShift() {
        return nightShift;
    }

    public void setNightShift(int nightShift) {
        this.nightShift = nightShift;
    }

}
