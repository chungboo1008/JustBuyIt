package com.example.balaginjo.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
                 implements View.OnClickListener, AdapterView.OnItemClickListener {

    ImageView image1,image2,image3,image4,image5,image6;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6;
    Toast tos;
    Intent it;

    static final String database_name = "ShoppingCarDataBase";
    static final String table_name = "shoppingcar_list";
    static final String[] FROM = new String[]{"picture", "title", "money", "number"};
    SQLiteDatabase database;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Usamaru 代購區");
        setContentView(R.layout.activity_main);

        initial();
    }

    protected void initial(){
        image1 = (ImageView)findViewById(R.id.imageView_P1);
        image2 = (ImageView)findViewById(R.id.imageView_P2);
        image3 = (ImageView)findViewById(R.id.imageView_P3);
        image4 = (ImageView)findViewById(R.id.imageView_P4);
        image5 = (ImageView)findViewById(R.id.imageView_P5);
        image6 = (ImageView)findViewById(R.id.imageView_P6);
        layout1 = (LinearLayout)findViewById(R.id.linearlayout_P1);
        layout2 = (LinearLayout)findViewById(R.id.linearlayout_P2);
        layout3 = (LinearLayout)findViewById(R.id.linearlayout_P3);
        layout4 = (LinearLayout)findViewById(R.id.linearlayout_P4);
        layout5 = (LinearLayout)findViewById(R.id.linearlayout_P5);
        layout6 = (LinearLayout)findViewById(R.id.linearlayout_P6);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);

        tos = Toast.makeText(this ,"",Toast.LENGTH_SHORT);
        it = new Intent();

        database = openOrCreateDatabase(database_name, Context.MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + table_name +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "picture VARCHAR(16)," +
                "title VARCHAR(32)," + "money VARCHAR(16)," + "number VARCHAR(16))";
        database.execSQL(createTable);

        cursor = database.rawQuery("SELECT * FROM " + table_name, null);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping_car:
                it.setClass(this, ShoppingCarActivity.class);
                startActivity(it);
                return true;
            case R.id.star:
                it.setClass(this, StoreInformationActivity.class);
                startActivity(it);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageView_P1 || v.getId() == R.id.linearlayout_P1){
            it.setClass(this, Product1_Activity.class);
            startActivity(it);
        }
        else if(v.getId() == R.id.imageView_P2 || v.getId() == R.id.linearlayout_P2){
            it.setClass(this, Product2_Activity.class);
            startActivity(it);
        }
        else if(v.getId() == R.id.imageView_P3 || v.getId() == R.id.linearlayout_P3){
            it.setClass(this, Product3_Activity.class);
            startActivity(it);
        }
        else if(v.getId() == R.id.imageView_P4 || v.getId() == R.id.linearlayout_P4){
            it.setClass(this, Product4_Activity.class);
            startActivity(it);
        }
        else if(v.getId() == R.id.imageView_P5 || v.getId() == R.id.linearlayout_P5){
            it.setClass(this, Product5_Activity.class);
            startActivity(it);
        }
        else if(v.getId() == R.id.imageView_P6 || v.getId() == R.id.linearlayout_P6){
            it.setClass(this, Product6_Activity.class);
            startActivity(it);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
