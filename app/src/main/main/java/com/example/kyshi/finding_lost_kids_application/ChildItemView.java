package com.example.kyshi.finding_lost_kids_application;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kyshi.finding_lost_kid_application.R;

/**
 * Created by android on 2018-05-12.
 */

public class ChildItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;


    public ChildItemView(Context context) {
        super(context);
        init(context);
    }

    public ChildItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.child_item, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setTag(String tag) {
        textView2.setText(tag);
    }

    public void setTime(String time) {
        textView3.setText(time);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

}
