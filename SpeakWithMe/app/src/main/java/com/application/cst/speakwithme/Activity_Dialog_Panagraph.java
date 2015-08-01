package com.application.cst.speakwithme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by KenBui on 01/08/2015.
 */
public class Activity_Dialog_Panagraph extends Activity {
    Button btnExit;
    Button btnOk;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_paragraph);
        btnExit=(Button)findViewById(R.id.buttonExitDialogPanagraph);
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
    }

    public void ExitDialog()
    {
        finish();
    }

    public void OkDialog()
    {

    }

    public void DeleteDialog()
    {

    }
}
