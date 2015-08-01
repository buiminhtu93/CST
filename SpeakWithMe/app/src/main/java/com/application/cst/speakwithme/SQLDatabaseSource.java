package com.application.cst.speakwithme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class SQLDatabaseSource {
    SQLiteDatabase db;
    DatabaseHelper helper;

    public SQLDatabaseSource(Context context){
        helper=new DatabaseHelper(context);
        helper.createDatabase();
        db= helper.openDatabase();
    }
}
