package com.application.cst.speakwithme;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class Item_Paragraph {
    String IDParagraph;
    String Name;
    String Detail;

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public Item_Paragraph(String idParagraph,String name, String detail){
        IDParagraph=idParagraph;
        Name=name;
        Detail=detail;
    }

    public Item_Paragraph(){};
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIDParagraph() {
        return IDParagraph;
    }

    public void setIDParagraph(String IDParagraph) {
        this.IDParagraph = IDParagraph;
    }
}
