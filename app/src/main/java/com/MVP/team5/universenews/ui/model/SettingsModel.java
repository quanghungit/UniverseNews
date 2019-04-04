package com.MVP.team5.universenews.ui.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    int themeApp;
    int fontSize;
    boolean nightShift;

    public SettingsModel(int themeApp, int fontSize, boolean nightShift) {
        this.themeApp = themeApp;
        this.fontSize = fontSize;
        this.nightShift = nightShift;
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

    public boolean isNightShift() {
        return nightShift;
    }

    public void setNightShift(boolean nightShift) {
        this.nightShift = nightShift;
    }

}
