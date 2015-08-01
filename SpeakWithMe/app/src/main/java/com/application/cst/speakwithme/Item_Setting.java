package com.application.cst.speakwithme;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class Item_Setting {

    String IDSetting;
    String Language;
    String Speed;
    String Sex;


    public Item_Setting(String idSetting,String language,String speed,String sex)
    {
        IDSetting=idSetting;
        Language=language;
        Speed=speed;
        Sex=sex;
    }
    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getIDSetting() {
        return IDSetting;
    }

    public void setIDSetting(String IDSetting) {
        this.IDSetting = IDSetting;
    }
}
