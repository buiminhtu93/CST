package com.application.cst.speakwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by KenBui on 01/08/2015.
 */
public class Activity_Detail_Dialog extends Activity{
    Intent intent;
    Bundle bundle;
    EditText edtInput;
    Button btnOk;
    Button btnCancle;
    Button btnDelete;
    Switch aSwitch;
    String ID; String Name; String person;
    SQLDatabaseSource db=new SQLDatabaseSource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dialog);
        btnOk=(Button)findViewById(R.id.buttonOkDialogDetail);
        btnCancle=(Button)findViewById(R.id.buttonCancleDialogDetail);
        btnDelete=(Button)findViewById(R.id.buttonDeleteDialogDetail);
        edtInput=(EditText)findViewById(R.id.editTextInputDetailDialog);
        aSwitch=(Switch)findViewById(R.id.switchDetail);
        intent=getIntent();
        bundle=intent.getBundleExtra("DETAIL_UPDATE");
        ID=bundle.getString("ID");
        Name=bundle.getString("NAME");
        person=bundle.getString("PERSON");
        if(person.equals("student")) aSwitch.setChecked(true);
        else aSwitch.setChecked(false);
        edtInput.setText(Name);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDetail();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void updateDetail()
    {
        if (aSwitch.isChecked()) person="student";
        else person="teacher";
        Item_Detail item=new Item_Detail();
        item.setIDDetail(ID);
        item.setContent(edtInput.getText().toString());
        item.setPerson(person);
        db.update_Detail(item);
        finish();
    }

    public void deleteDetail()
    {
        Item_Detail item=new Item_Detail();
        item.setIDDetail(ID);
        db.deleteDetail(item);
        finish();
    }


}
