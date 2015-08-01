package com.application.cst.speakwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by KenBui on 01/08/2015.
 */
public class Activity_Dialog_Panagraph extends Activity {
    Button btnExit;
    Button btnOk;
    Button btnDelete;
    EditText edtInput;
    Intent intent;
    Bundle bundle;
    String ID;
    String Name;
    SQLDatabaseSource db;
    Item_Paragraph item_paragraph=new Item_Paragraph();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_paragraph);
        edtInput=(EditText)findViewById(R.id.editTextInputParagraphDialog);
        db=new SQLDatabaseSource(this);
        btnExit=(Button)findViewById(R.id.buttonCancleDialogPanagraph);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitDialog();
            }
        });

        btnOk=(Button)findViewById(R.id.buttonOkDialogPanagraph);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkDialog();
            }
        });

        btnDelete=(Button)findViewById(R.id.buttonDeleteDialogPanagraph);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog();
            }
        });
        intent=getIntent();
        bundle=intent.getBundleExtra("UPDATE");
        ID=bundle.getString("ID");
        Name=bundle.getString("NAME");
        edtInput.setText(Name)  ;
    }

    public void ExitDialog()
    {
        finish();
    }

    public void OkDialog()
    {
        item_paragraph.setIDParagraph(ID);
        Name=edtInput.getText().toString();
        item_paragraph.setName(Name);
        item_paragraph.setDetail(" ");
        db.update_Paragraph_Name(item_paragraph);
        finish();
    }

    public void DeleteDialog()
    {
        item_paragraph.setIDParagraph(ID);
        item_paragraph.setName(Name);
        item_paragraph.setDetail("");
        db.Delete_Paragraph(item_paragraph);
        finish();
    }
}
