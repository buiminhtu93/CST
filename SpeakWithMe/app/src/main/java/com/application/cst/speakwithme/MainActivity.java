package com.application.cst.speakwithme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    ListView listview;
    ImageButton imgbuttonAdd;
    List<Item_Paragraph> itemParagraphList;
    SQLDatabaseSource db;
    Adapter_Paragraph adapter_paragraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.listViewPanagraph);
        imgbuttonAdd=(ImageButton)findViewById(R.id.imageButtonAddPanagraph);
        itemParagraphList=new ArrayList<>();
        imgbuttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogPanagraph();
            }
        });
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadData()
    {
        db=new SQLDatabaseSource(this);
        itemParagraphList=db.getListParagraph();
        adapter_paragraph=new Adapter_Paragraph(this, R.layout.item_paragraph_activity,itemParagraphList);
        listview.setAdapter(adapter_paragraph);
    }

    public void openDialogPanagraph()
    {
        Intent intent=new Intent(MainActivity.this, Activity_Dialog_Panagraph.class);
        startActivity(intent);
    }
}
