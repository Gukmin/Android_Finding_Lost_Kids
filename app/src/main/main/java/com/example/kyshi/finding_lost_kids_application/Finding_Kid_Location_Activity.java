package com.example.kyshi.finding_lost_kids_application;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyshi.finding_lost_kid_application.R;

import uk.co.senab.photoview.PhotoViewAttacher;


public class Finding_Kid_Location_Activity extends AppCompatActivity
         implements NavigationView.OnNavigationItemSelectedListener{
    private Context mContext = this;
    private Intent intenttolostchild = null;
    private  View view;
    private AppCompatActivity activity;
    //public  static ListViewAdapter listviewadapter;
    public static ListView listview;
   static String[] childs = {"건영","찬우","국민","민성","좀비","좀비","좀비","좀비","좀비"}; // 현재는 그냥 string list 로 해놓았지만 이후에 서버에서 값을 받을 때에는 가변 배열로 설정
    boolean[] checkedItems = new boolean[childs.length]; // childs 에서 chedcked (체크 표시된 ) 요소의 id 를 저장하는 배열
    private boolean isFabOpen = false; // floating button 이 'open ' 'close' 의 상태인지 boolean 형태로 알려줌.
    private FloatingActionButton fab,fab1,fab2; // fab: + 모양 floating button  나머지는 app_bar_main 참고
    private Animation fab_open, fab_close, rotate_forward, rotate_backward; // fab 의 애니메이션 트리거 변수

    ImageView map;
    PhotoViewAttacher attacher;
    ActionBarDrawerToggle drawerToggle;
    String child = "child";
    //private ArrayList<String> data = new ArrayList<String>();
    //private ArrayAdapter<String> string ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.finding_kid_location);
        //activity = this;
        //adapter 클래스 생성
        //listviewadapter = new ListViewAdapter(activity);
       // string = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //listview.setAdapter(string);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward); // res 의 anim  폴더의 동작 변수 확인

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "아직 개발중입니다.", Toast.LENGTH_LONG).show();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "아직 개발중입니다.", Toast.LENGTH_LONG).show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.fab:
                        animateFAB();
                        break;
                    case R.id.fab1:
                        Log.d("Raj", "Fab 1");
                        break;
                    case R.id.fab2:
                        Log.d("Raj", "Fab 2");
                        break;
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


 // drawerLayout 의 액션바 토글 설정 부분
        drawerToggle=new ActionBarDrawerToggle(this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close){


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);


            }
        };
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);  // 네비게이션 창을 열었을때 메뉴 아이템 클릭 리스너  밑의 onNavigationItemSelectedListener 참조



         /*Bitmap image1,image2;
        Paint paint = new Paint();
        image1 = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        Bitmap tempBitmap = Bitmap.createBitmap(image1.getWidth(), image1.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(tempBitmap);
        //paint.setColor(Color.WHITE);
        //canvas.drawPaint(paint);
        canvas.drawBitmap(image1,0,0,null);

        map.setImageBitmap(tempBitmap);
        //map.setVisibility(View.INVISIBLE);
        //loc =(ImageView)findViewById(R.id.location);
        attacher = new PhotoViewAttacher(map);

        attacher.setOnViewTapListener(viewTapListener);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } // 화면 우상단 구현

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"go to setting activity",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.contact){
            Toast.makeText(getApplicationContext(),"010-8922-5615/ mschanwoo@naver.com",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    } // setting , contact 구현

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            //Dialog 생성 Dialog 는 팝업창이라고 생각하면됨
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                    Finding_Kid_Location_Activity.this);
            alertBuilder.setIcon(R.drawable.ic_menu_camera); // 아이콘 설정
            alertBuilder.setTitle("아이를 선택하세요"); // 타이틀 설정

            //여러개 선택할 수있는 옵션
            alertBuilder.setMultiChoiceItems(childs, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            Bitmap image1, image2;
                            Bitmap image[] = new Bitmap[5];
                            map = (ImageView)findViewById(R.id.imageView2);
                            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.map);
                            //image2 = BitmapFactory.decodeResource(getResources(), R.drawable.location);
                            Bitmap tempBitmap = Bitmap.createBitmap(image1.getWidth(), image1.getHeight(), Bitmap.Config.RGB_565);
                            Canvas canvas = new Canvas(tempBitmap);
                            canvas.drawBitmap(image1,0,0,null);
                            Toast.makeText(getApplicationContext(),childs[which],Toast.LENGTH_SHORT).show();
                            if(isChecked) // 아이 check 활성화
                            {

                                child = "child";
                                child += Integer.toString(which+1);  // child 에 숫자 덧붙이는 항목
                                Toast.makeText(getApplicationContext(),child,Toast.LENGTH_LONG).show();
                                int resId = getResources().getIdentifier(child,"drawable",getPackageName());
                                if(resId == R.drawable.child1)
                                     Toast.makeText(getApplicationContext(),"same",Toast.LENGTH_LONG).show();
                                image[0] =BitmapFactory.decodeResource(getResources(), R.drawable.child1);
                                image[1] =BitmapFactory.decodeResource(getResources(), R.drawable.child2);
                                image[2] =BitmapFactory.decodeResource(getResources(), R.drawable.child3);
                                canvas.drawBitmap(image[0],150,150,null);
                                canvas.drawBitmap(image[1],500,500,null);
                                canvas.drawBitmap(image[2],850,850,null);
                            } // 추후수정
                           map.setImageBitmap(tempBitmap);
                            attacher = new PhotoViewAttacher(map);
                            attacher.setOnViewTapListener(viewTapListener);
                        }
                    });

                    //버튼 설정 부분
                    alertBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                        }
                    });
                    alertBuilder.setNegativeButton("cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }
                    );
            //adapter setting


            alertBuilder.show();


       }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }








    PhotoViewAttacher.OnViewTapListener viewTapListener = new PhotoViewAttacher.OnViewTapListener() {
        @Override
        public void onViewTap(View view, float x, float y) {
            //map.setVisibility(View.VISIBLE);

            /*
            Bitmap image1, image2, resize;
            image1 = BitmapFactory.decodeResource(getResources(), R.drawable.map);
            image2 = BitmapFactory.decodeResource(getResources(), R.drawable.location);
            Bitmap tempBitmap = Bitmap.createBitmap(map.getWidth(), map.getWidth(), Bitmap.Config.RGB_565);
            resize = Bitmap.createScaledBitmap(image1,map.getWidth(),map.getHeight(),true);
            Canvas canvas = new Canvas(tempBitmap);
            canvas.drawBitmap(resize,0,0,null);
            canvas.drawBitmap(image2,x,y,null);

            map.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));

            Toast.makeText(getApplicationContext(), "image_width: " + image1.getWidth() +  "image_height: " + image1.getHeight(), Toast.LENGTH_SHORT).show();
            */
        }

    };


public void animateFAB() {

    if (isFabOpen) {

        //fab.startAnimation(rotate_backward);
        fab1.startAnimation(fab_close);
        fab2.startAnimation(fab_close);

        fab1.setClickable(false);
        fab2.setClickable(false); // 아이템 선택가능  기능 비활성화
        isFabOpen = false;
        Log.d("Raj", "close");
    } else {
        //fab.startAnimation(rotate_forward);
        fab1.startAnimation(fab_open);
        fab2.startAnimation(fab_open);

        fab1.setClickable(true); // 아이템 선택가능  기능 비활성화
        fab2.setClickable(true);

        isFabOpen = true;
        Log.d("Raj", "open");
    }


}
    }

