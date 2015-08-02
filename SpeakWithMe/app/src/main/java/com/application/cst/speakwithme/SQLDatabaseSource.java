package com.application.cst.speakwithme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class SQLDatabaseSource {
    SQLiteDatabase db;
    DatabaseHelper helper;

    public SQLDatabaseSource(Context context) {
        helper = new DatabaseHelper(context);
        helper.createDatabase();
        db = helper.openDatabase();
    }
    //Get List
    public List<Item_Paragraph> getListParagraph() {
        List<Item_Paragraph> list = new ArrayList<>();
        Cursor c = db.query("PARAGRAPH", null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Item_Paragraph paragraph = new Item_Paragraph();
            paragraph.setIDParagraph(c.getString(0));
            paragraph.setName(c.getString(1));
            paragraph.setDetail(c.getString(2));
            list.add(paragraph);
            c.moveToNext();
        }
        return list;
    }

    public List<Item_Detail> getlistDetail(String IDparagraph)
    {
        List<Item_Detail> list=new ArrayList<>();
        String lenh="SELECT * FROM DETAIL WHERE IDPanagraph='"+IDparagraph+"' ORDER BY IDDetail ASC";
        Cursor c=db.rawQuery(lenh, null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            Item_Detail item=new Item_Detail();
            item.setIDDetail(c.getString(0));
            item.setIDParagraph(c.getString(1));
            item.setContent(c.getString(2));
            item.setPerson(c.getString(3));
            list.add(item);
            c.moveToNext();
        }
        return list;
    }


    //update database
    public void update_Paragraph_Name(Item_Paragraph paragraph)
    {
        String lenh="UPDATE PARAGRAPH SET Name='"+paragraph.getName()+"' WHERE IDParagraph='"+paragraph.getIDParagraph()+"'";
        db.execSQL(lenh);
    }


    public void update_Paragraph_Detail(Item_Paragraph paragraph)
    {
        String lenh="UPDATE PARAGRAPH SET Detail='"+paragraph.getDetail()+"' WHERE IDParagraph='"+paragraph.getIDParagraph()+"'";
        db.execSQL(lenh);
    }

    public void update_Detail(Item_Detail detail)
    {
        String lenh="UPDATE DETAIL SET Content='"+detail.getContent() +"', Person='"+ detail.getPerson()+"' WHERE IDDetail='"+detail.getIDDetail()+"'";
        db.execSQL(lenh);
    }


    public void update_Setting_FirstLanguage(String value)
    {
        String lenh="UPDATE SETTING SET FirstLanguage='"+value+"' WHERE IDSetting='1'";
        db.execSQL(lenh);
    }

    public void update_Setting_SecondLanguage(String value)
    {
        String lenh="UPDATE SETTING SET SecondLanguage='"+value+"' WHERE IDSetting='1'";
        db.execSQL(lenh);
    }

    public void update_Setting_Speed(String value)
    {
        String lenh="UPDATE SETTING SET Speed='"+value+"' WHERE IDSetting='1'";
        db.execSQL(lenh);
    }


    //Insert Database
    public void Insert_Paragraph(Item_Paragraph paragraph)
    {
        String lenh="INSERT INTO PARAGRAPH VALUES('"+paragraph.getIDParagraph()+"', '" + paragraph.getName()+ "', '"+paragraph.getDetail()+"')";
        db.execSQL(lenh);
    }

    public void Insert_Detail(Item_Detail detail)
    {
        String lenh="INSERT INTO DETAIL VALUES ('"
                +detail.getIDDetail() +"', '"
                +detail.getIDParagraph() +"', '"
                +detail.getContent()+"', '"
                +detail.getPerson()+"')";
        db.execSQL(lenh);
    }

    //delete database
    public void Delete_Paragraph(Item_Paragraph paragraph)
    {
        String lenh="DELETE FROM PARAGRAPH WHERE IDParagraph='"+paragraph.getIDParagraph()+"'";
        db.execSQL(lenh);
    }

    public void deleteDetail(Item_Detail detail)
    {
        String lenh="DELETE FROM DETAIL WHERE IDDetail='"+detail.getIDDetail()+"'";
        db.execSQL(lenh);
    }

    //setting
    public Item_Setting getSettingItem()
    {
        String lenh="SELECT * FROM SETTING WHERE IDSetting='1'";
        Cursor c=db.rawQuery(lenh,null);
        c.moveToFirst();
        Item_Setting item=new Item_Setting();
        item.setIDSetting(c.getString(0));
        item.setFirstLanguage(c.getString(1));
        item.setSecondLanguage(c.getString(2));
        item.setSpeed(c.getString(3));
        return  item;
    }

    public List<Item_Template> getlistTemplate(String first, String second)
    {
        List<Item_Template> list=new ArrayList<>();
        String lenh="SELECT " + first +" , " + second +" FROM TEMPLATE";
        Cursor c=db.rawQuery(lenh, null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            Item_Template item=new Item_Template();
            item.setFirst(c.getString(0));
            item.setSecond(c.getString(1));
            list.add(item);
            c.moveToNext();
        }
        return list;
    }


}
