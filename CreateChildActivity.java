package com.example.android.finding_lost_kids_application;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateChildActivity extends AppCompatActivity {
    private Context mContext = this;
    private Intent intenttouserhomeactivity = null;
    private static final int REQUEST_CODE_USERHOME= 3002;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_USERHOME){
            setResult(resultCode,data);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*** 여기서 서버 연결을 확인하고 서버 연결이 되는 환경이면 User_Home_Activity 로 넘겨주자 ***/

        if(true){
            intenttouserhomeactivity = new Intent(mContext, User_Home_Activity.class);
            startActivityForResult(intenttouserhomeactivity,REQUEST_CODE_USERHOME);
        } else{

        }

    }
}
