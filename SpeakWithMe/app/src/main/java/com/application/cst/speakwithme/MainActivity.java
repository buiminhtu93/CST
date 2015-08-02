package com.application.cst.speakwithme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    ListView listview;
    ImageButton imgbuttonAdd;
    ImageButton imgbuttonSetting;
    ImageButton imgbuttonTexttoSpeech;
    ImageButton imgbuttonTemplate;
    List<Item_Paragraph> itemParagraphList;
    SQLDatabaseSource db;
    Adapter_Paragraph adapter_paragraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.listViewPanagraph);
        imgbuttonAdd=(ImageButton)findViewById(R.id.imageButtonAddPanagraph);
        imgbuttonSetting=(ImageButton)findViewById(R.id.imagebuttonSetting);
        imgbuttonTexttoSpeech=(ImageButton)findViewById(R.id.imageButtonTexttoSpeech);
        imgbuttonTemplate=(ImageButton)findViewById(R.id.imageButtonTemplate);
        itemParagraphList=new ArrayList<>();
        imgbuttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogPanagraphInsert();
            }
        });
        imgbuttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Activity_Setting.class);
                startActivity(intent);
            }
        });
        imgbuttonTexttoSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Activity_Text_to_Speech.class);
                startActivity(intent);
            }
        });
        imgbuttonTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Activity_Template.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(listview);
        loadData();

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    public void openDialogPanagraphUpdate(Item_Paragraph item)
    {
        Intent intent=new Intent(MainActivity.this, Activity_Dialog_Panagraph.class);
        Bundle bundle=new Bundle();
        bundle.putString("ID",item.getIDParagraph().toString());
        bundle.putString("NAME",item.getName().toString());
        intent.putExtra("UPDATE", bundle);
        startActivity(intent);
    }

    public void openDialogPanagraphInsert()
    {
        Intent intent=new Intent(MainActivity.this, Activity_Paragraph_Dialog_Insert.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_paragraph, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos=info.position;
        Item_Paragraph Item=itemParagraphList.get(pos);
        switch (item.getItemId()){
            case R.id.itemMenu_delete:
                itemParagraphList.remove(pos);
                adapter_paragraph.notifyDataSetChanged();
                db.Delete_Paragraph(Item);
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                break;
            case R.id.itemMenu_update:
                openDialogPanagraphUpdate(Item);
                break;
            case R.id.itemMenu_share:
                Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
