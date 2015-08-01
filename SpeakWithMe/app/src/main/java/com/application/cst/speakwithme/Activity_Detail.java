package com.application.cst.speakwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by KenBui on 01/08/2015.
 */
public class Activity_Detail extends Activity {
    SQLDatabaseSource db;
    List<Item_Detail> detailList;
    Adapter_Detail adapter;
    String ID;
    String NAME;
    Bundle bundle;
    Intent intent;
    ListView listView;
    TextView tvTitleTextBar;
    EditText edtInput;
    String person="student";
    ImageButton btnSendDetail;
    ImageButton imgbuttonPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        db=new SQLDatabaseSource(this);
        detailList=new ArrayList<>();
        intent=getIntent();
        edtInput=(EditText)findViewById(R.id.editTextInputDetail);
        bundle=intent.getBundleExtra("DETAIL");
        ID=bundle.getString("ID");
        NAME=bundle.getString("NAME");
        tvTitleTextBar=(TextView)findViewById(R.id.textViewDetailTitle);
        tvTitleTextBar.setText(NAME);
        loadData(ID);
        btnSendDetail=(ImageButton)findViewById(R.id.imageButtonSendDetail);
        btnSendDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDetail();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strid = detailList.get(position).getIDDetail();
                String strcontent = detailList.get(position).getContent();
                String strperson = detailList.get(position).getPerson();
                openDialog(strid, strcontent, strperson);
            }
        });
        imgbuttonPlay=(ImageButton)findViewById(R.id.imageButtonPlayDetail);
        imgbuttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlay(ID,NAME);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        loadData(ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(ID);
    }

    private void loadData(String id)
    {

        detailList=db.getlistDetail(id);
        listView=(ListView)findViewById(R.id.listViewDetail);
        adapter=new Adapter_Detail(this, R.layout.item_detail,detailList);
        listView.setAdapter(adapter);
    }

    private  void insertDetail()
    {
        Item_Detail item=new Item_Detail();
        String id=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        item.setIDDetail(id);
        item.setContent(edtInput.getText().toString());
        if (person.equals("teacher")) person="student";
        else person="teacher";
        item.setPerson(person);
        item.setIDParagraph(ID);
        db.Insert_Detail(item);
        detailList.add(item);
        adapter.notifyDataSetChanged();
        edtInput.setText("");
    }

    public void openDialog(String id, String name, String person)
    {
        Intent intent=new Intent(Activity_Detail.this, Activity_Detail_Dialog.class);
        Bundle bundle=new Bundle();
        bundle.putString("ID", id);
        bundle.putString("NAME", name);
        bundle.putString("PERSON", person);
        intent.putExtra("DETAIL_UPDATE", bundle);
        startActivity(intent);
    }

    public void openPlay(String id, String name)
    {
        Intent intent=new Intent(Activity_Detail.this, Activity_Detail_Play.class);
        Bundle bundle=new Bundle();
        bundle.putString("ID",id);
        bundle.putString("NAME",name);
        intent.putExtra("DETAIL_PLAY", bundle);
        startActivity(intent);
    }


}
