package com.application.cst.speakwithme;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class Item_Setting {

    String IDSetting;
    String FirstLanguage;
    String SecondLanguage;
    String Speed;


    public Item_Setting() {
    }

    public Item_Setting(String IDSetting, String firstLanguage, String secondLanguage, String speed) {
        this.IDSetting = IDSetting;
        FirstLanguage = firstLanguage;
        SecondLanguage = secondLanguage;
        Speed = speed;
    }

    public String getIDSetting() {
        return IDSetting;
    }

    public void setIDSetting(String IDSetting) {
        this.IDSetting = IDSetting;
    }

    public String getFirstLanguage() {
        return FirstLanguage;
    }

    public void setFirstLanguage(String firstLanguage) {
        FirstLanguage = firstLanguage;
    }

    public String getSecondLanguage() {
        return SecondLanguage;
    }

    public void setSecondLanguage(String secondLanguage) {
        SecondLanguage = secondLanguage;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }
}
