package com.application.cst.speakwithme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KenBui on 02/08/2015.
 */
public class Adapter_Template extends ArrayAdapter {
    Context context;
    int resource;
    List<Item_Template> listTemplate;
    public Adapter_Template(Context context, int resource, List<Item_Template> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listTemplate=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, resource, null);
        TextView tvFirst=(TextView)view.findViewById(R.id.textViewFirstLanguage);
        final Item_Template template=listTemplate.get(position);
        tvFirst.setText(template.getSecond().toString());
        TextView tvSecond=(TextView)view.findViewById(R.id.textViewSeconLanguage);
        tvSecond.setText(template.getFirst().toString());
        return view;
    }
}
