package com.example.android.finding_lost_kids_application;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.finding_lost_kids_application.R;


public class Finding_Kid_Location_Activity extends AppCompatActivity {
    private Context mContext = this;
    private Intent intenttolostchild = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        intenttolostchild = Intent(mContext, );
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_child);

        ImageView map = (ImageView) findViewById(R.id.mapView);


        /**
         * 지도 이미지를 드래그 했을 경우 지도 화면을 움직일 수 있다.
         */

        map.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(getApplicationContext(),"Map Untouched! From X: " + event.getX() + ", Y: " + event.getY(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    //Toast.makeText(getApplicationContext(),"Map Draged! From X: " + event.getX() + ", Y: " + event.getY(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getApplicationContext(),"Map Touched! From X: " + event.getX() + ", Y: " + event.getY(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });



        //setContentView(R.layout.activity_over_map);
    }
}


