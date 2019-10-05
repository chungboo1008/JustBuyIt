package com.example.balaginjo.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;


public class Product3_Activity extends AppCompatActivity
        implements View.OnClickListener {

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    ViewPagerIndicator viewPagerIndicator;

    Button button_add, button_sub, button_add_shopping_car;
    TextView textView_title, textView_money, textView_number;
    int count = 1;
    Toast tos;

    static final String database_name = "ShoppingCarDataBase";
    static final String table_name = "shoppingcar_list";
    static final String[] FROM = new String[]{"picture", "title", "money", "number"};
    SQLiteDatabase database;
    Cursor cursor;

    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_product3_);

        initial();
    }

    protected void initial() {
        viewPager = findViewById(R.id.viewpager);
        //index要修改
        adapter = new ViewPagerAdapter(this,3);
        viewPager.setAdapter(adapter);

        viewPagerIndicator = findViewById(R.id.indicator);
        viewPagerIndicator.setViewPager(viewPager);

        button_add = findViewById(R.id.button_add);
        button_sub = findViewById(R.id.button_sub);
        button_add_shopping_car = findViewById(R.id.button_add_shopping_car);

        button_add.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_add_shopping_car.setOnClickListener(this);

        textView_title = findViewById(R.id.textView_title);
        textView_money = findViewById(R.id.textView_money);
        textView_number = findViewById(R.id.textView_number);
        count = 1;
        textView_number.setText(String.valueOf(count));
        tos = Toast.makeText(this ,"",Toast.LENGTH_SHORT);

        database = openOrCreateDatabase(database_name, Context.MODE_PRIVATE, null);
        cursor = database.rawQuery("SELECT * FROM " + table_name, null);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping_car:
                Intent it = new Intent();
                it.setClass(this, ShoppingCarActivity.class);
                startActivity(it);
                return true;
            case R.id.keyboard_backspace:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_back, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_add:
                count = Integer.parseInt(textView_number.getText().toString());
                if(count < 10)
                    count++;
                else{
                    tos.setText("最大選購數量只能到10唷\n如有特殊需求請另下訂單");
                    tos.show();
                }
                textView_number.setText(String.valueOf(count));
                break;
            case R.id.button_sub:
                count = Integer.parseInt(textView_number.getText().toString());
                if(count > 1)
                    count--;
                else{
                    tos.setText("最小選購數量為1");
                    tos.show();
                }
                textView_number.setText(String.valueOf(count));
                break;
            case R.id.button_add_shopping_car:
                addData("product3",
                        textView_title.getText().toString(),
                        textView_money.getText().toString(),
                        "數量: " + textView_number.getText().toString());
                tos.setText("加入購物車中囉~");
                tos.show();
                break;
        }
    }

    private void addData(String picture, String title, String money, String number){
        ContentValues cv = new ContentValues(4);
        cv.put(FROM[0], picture);
        cv.put(FROM[1], title);
        cv.put(FROM[2], money);
        cv.put(FROM[3], number);

        database.insert(table_name, null, cv);
    }
}
