package com.application.cst.speakwithme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static  String DB_Name= "SpeakWithMe.db";
    private static  String DB_Path="/data/data/com.application.cst.speakwithme/database";
    private SQLiteDatabase myDatabase;
    private final Context myContext;


    public DatabaseHelper(Context context) {
        super(context,DB_Name,null,1);
        this.myContext=context;
    }

    public  void createDatabase()
    {
        boolean checkExits=checkDatabase();
        if(!checkExits)
        {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error when copy Database");
            }finally {
                this.close();
            }


        }
    }

    private void copyDatabase() throws IOException
    {
        try {
            InputStream inputDB = myContext.getAssets().open(DB_Name);
            String outFileName = DB_Path + DB_Name;
            OutputStream outputDB = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = inputDB.read(buffer)) > 0) {
                outputDB.write(buffer, 0, lenght);
            }
            outputDB.flush();
            outputDB.close();
        }catch (Exception ex){}
    }


    private boolean checkDatabase() {
        SQLiteDatabase checkDB=null;
        try
        {
            String strPath=DB_Path+DB_Name;
            checkDB=SQLiteDatabase.openDatabase(strPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (Exception ex){

        }
        if(checkDB!=null)
        {
            this.close();
        }
        return checkDB!=null? true:false;
    }


    public SQLiteDatabase openDatabase()
    {
        String strOpen=DB_Path+DB_Name;
        return SQLiteDatabase.openDatabase(strOpen,null,SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
