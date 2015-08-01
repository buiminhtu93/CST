package com.application.cst.speakwithme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by KenBui on 01/08/2015.
 */
public class Activity_Paragraph_Dialog_Insert extends Activity {
    Button btnOk;
    Button btnCancle;
    EditText edtInput;
    SQLDatabaseSource db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paragraph_dialog_insert);
        btnOk=(Button)findViewById(R.id.buttonOkInsertDialogPanagraph);
        btnCancle=(Button)findViewById(R.id.buttonCancleInsertDialogPanagraph);
        edtInput=(EditText)findViewById(R.id.editTextInputInsertParagraphDialog);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ok();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancle();
            }
        });
    }

    private void Ok()
    {
        String id=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        String name=edtInput.getText().toString();
        db=new SQLDatabaseSource(this);
        Item_Paragraph item=new Item_Paragraph(id,name,name);
        db.Insert_Paragraph(item);
        finish();
    }

    private  void Cancle()
    {
        finish();
    }
}
