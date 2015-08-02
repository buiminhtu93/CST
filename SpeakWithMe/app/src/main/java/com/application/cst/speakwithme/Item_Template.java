package com.application.cst.speakwithme;

/**
 * Created by KenBui on 02/08/2015.
 */
public class Item_Template {
    String IDTemplate;
    String First;
    String Second;


    public Item_Template() {
    }

    public Item_Template(String IDTemplate, String first, String second) {
        this.IDTemplate = IDTemplate;
        First = first;
        Second = second;
    }

    public String getIDTemplate() {
        return IDTemplate;
    }

    public void setIDTemplate(String IDTemplate) {
        this.IDTemplate = IDTemplate;
    }

    public String getFirst() {
        return First;
    }

    public void setFirst(String first) {
        First = first;
    }

    public String getSecond() {
        return Second;
    }

    public void setSecond(String second) {
        Second = second;
    }
}
