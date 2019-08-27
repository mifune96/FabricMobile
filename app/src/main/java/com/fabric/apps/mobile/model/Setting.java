package com.fabric.apps.mobile.model;

public class Setting {

    public int settingId;
    public int settingName;
    public int settingImg;

    public Setting() {
    }

    public int getSettingName() {
        return settingName;
    }

    public void setSettingName(int settingName) {
        this.settingName = settingName;
    }

    public int getSettingImg() {
        return settingImg;
    }

    public void setSettingImg(int settingImg) {
        this.settingImg = settingImg;
    }
}
