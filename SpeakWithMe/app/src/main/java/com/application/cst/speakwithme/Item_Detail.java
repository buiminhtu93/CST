package com.application.cst.speakwithme;

import android.content.Context;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class Item_Detail {
    String IDDetail;
    String IDParagraph;
    String Content;
    String Person;


    public Item_Detail(String idDetail,String idParagraph,String content, String person )
    {
        IDDetail=idDetail;
        IDParagraph=idParagraph;
        Content=content;
        Person=person;
    }

    public Item_Detail(){};
    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getIDParagraph() {
        return IDParagraph;
    }

    public void setIDParagraph(String IDParagraph) {
        this.IDParagraph = IDParagraph;
    }

    public String getIDDetail() {
        return IDDetail;
    }

    public void setIDDetail(String IDDetail) {
        this.IDDetail = IDDetail;
    }
}
