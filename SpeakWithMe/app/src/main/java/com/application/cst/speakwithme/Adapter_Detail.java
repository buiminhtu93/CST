package com.application.cst.speakwithme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Angel-PC on 01/08/2015.
 */
public class Adapter_Detail extends ArrayAdapter {
    Context context;
    int resource;
    List<Item_Detail> list;
    int Person;

    public Adapter_Detail(Context context, int resource, List<Item_Detail> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context,resource,null);
        TextView tvContent=(TextView)view.findViewById(R.id.textViewBubbleDetail);
        TextView tvTitleContent=(TextView)view.findViewById(R.id.textViewBubbleTitleDetail);
        LinearLayout frmLayout=(LinearLayout)view.findViewById(R.id.framelayoutBubbleDetail);
        ImageView imgView=(ImageView)view.findViewById(R.id.imageViewBubbleItem);
        Item_Detail item=list.get(position);
        tvContent.setText(item.getContent());
        String person=item.getPerson();
        if (person.equals("teacher"))
        {
            frmLayout.setBackgroundResource(R.color.color_detail_left);
            imgView.setBackgroundResource(R.drawable.teacher_ico);
            tvTitleContent.setText("Me");
        }
        else
        {
            frmLayout.setBackgroundResource(R.color.color_detail_right);
            imgView.setBackgroundResource(R.drawable.student_icon);
            tvTitleContent.setText("You");
        }
        return view;
    }
}
