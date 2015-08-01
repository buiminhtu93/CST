package com.application.cst.speakwithme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class Adapter_Paragraph extends ArrayAdapter {
    Context context;
    int resource;
    List<Item_Paragraph> listParagraph;
    public Adapter_Paragraph(Context context, int resource, List<Item_Paragraph> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listParagraph=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context,resource,null);
        TextView tvName=(TextView)view.findViewById(R.id.textViewNameParagraph);
        final Item_Paragraph paragraph=listParagraph.get(position);
        tvName.setText(paragraph.getName().toString());
        TextView tvDetail=(TextView)view.findViewById(R.id.textViewDetailParagraph);
        tvDetail.setText(paragraph.getDetail().toString());
        ImageButton btnShow=(ImageButton)view.findViewById(R.id.imageButtonShowDialogParagraph);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Activity_Dialog_Panagraph.class);
                Bundle bundle=new Bundle();
                bundle.putString("ID",paragraph.getIDParagraph().toString());
                bundle.putString("NAME",paragraph.getName().toString());
                intent.putExtra("UPDATE", bundle);
                context.startActivity(intent);
            }
        });

        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail(paragraph.getIDParagraph().toString(),paragraph.getName().toString());
            }
        });

        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail(paragraph.getIDParagraph().toString(),paragraph.getName().toString());
            }
        });
        return view;
    }

    private void openDetail(String id, String name)
    {
        Intent intent=new Intent(context, Activity_Detail.class);
        Bundle bundle=new Bundle();
        bundle.putString("ID",id);
        bundle.putString("NAME",name);
        intent.putExtra("DETAIL", bundle);
        context.startActivity(intent);
    }
}
