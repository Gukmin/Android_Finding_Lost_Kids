package com.example.kyshi.finding_lost_kids_application;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.kyshi.finding_lost_kid_application.R;

public class NewMainActivity extends AppCompatActivity {
    ScrollView scrollView;
    String dbname = "childDB";
    String tablename = "childTable";
    SQLiteDatabase db;
    Cursor resultset;

    public static final int REQUEST_CODE_CREATECHILD = 3000;

    // 어댑터
    class ChildAdapter extends BaseAdapter {
        ArrayList<ChildItem> items = new ArrayList<>();
        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(ChildItem item) {
            items.add(item);
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            ChildItemView view = new ChildItemView(getApplicationContext());
            ChildItem item = items.get(position);
            view.setName(item.getName());
            view.setTime(item.getTime());
            view.setImage(item.getResId());
            view.setTag(item.getTag());

            return view;
        }
    }

    ListView listView;
    ChildAdapter adapter = new ChildAdapter();

    //액티비티 결과값
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //new String(String.valueOf(new Date(System.currentTimeMillis())))
        if (requestCode == REQUEST_CODE_CREATECHILD) {
            ChildItem newChild = new ChildItem(data.getExtras().getString("name"),data.getExtras().getString("tag"), "time", R.drawable.child1);
            adapter.addItem(newChild);
            insertIntoDB(newChild);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //listView
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        db = openOrCreateDatabase(dbname,MODE_PRIVATE,null);
        createTable();
        initView();

        //하나 클릭 했을때
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChildItem item = (ChildItem) adapter.getItem(i);
                Intent intenttofindinglocationactivity = new Intent(getApplicationContext(), Finding_Kid_Location_Activity.class);  // Finding_Kid_Location_Activity 로 넘어가기 위한 intent
                Toast.makeText(getApplicationContext(), "아이의 이름: " + item.getName(), Toast.LENGTH_LONG).show();
                startActivity(intenttofindinglocationactivity);
            }
        });



        //scroll
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setHorizontalScrollBarEnabled(true);

        //+ fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCreateChild = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intentCreateChild, REQUEST_CODE_CREATECHILD);
            }
        });
    }

    /*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createTable(){
        try{
            db.execSQL("create table if not exists "+tablename+"("
                    +"tag text primary key, "
                    +"name text, "
                    +"resId integer);");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void insertIntoDB(ChildItem newChild){
        try{
            db.execSQL("insert into "+ tablename +"(tag, name, resId) values ('"
                    +newChild.getTag()+"','"
                    +newChild.getName()+"',"
                    +newChild.getResId()+");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initView(){
        try{
            resultset = db.rawQuery("select * from "+tablename,null);

            int count = resultset.getCount();
            for(int i=0;i<count;i++){
                resultset.moveToNext();
                ChildItem child = new ChildItem(resultset.getString(1), resultset.getString(0), "time", resultset.getInt(2));
                adapter.addItem(child);
            }
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

