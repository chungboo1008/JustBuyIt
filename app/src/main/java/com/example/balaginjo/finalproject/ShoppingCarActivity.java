package com.example.balaginjo.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCarActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    static final String database_name = "ShoppingCarDataBase";
    static final String table_name = "shoppingcar_list";
    static final String[] FROM = new String[]{"picture", "title", "money", "number"};
    SQLiteDatabase database;
    Cursor cursor;
    SimpleCursorAdapter adapter;
    ListView listView;

    Intent it;

    Toast tos;

    String string_money, string_number;
    int money = 0, number = 0, total = 0, count = 0, index = 0;
    TextView output_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Shopping Car 購物車");
        setContentView(R.layout.activity_shopping_car);

        initial();
    }

    protected void initial(){

        database = openOrCreateDatabase(database_name, Context.MODE_PRIVATE, null);

        cursor = database.rawQuery("SELECT * FROM " + table_name, null);

        adapter = new SimpleCursorAdapter(this,
                R.layout.listview_item, cursor, FROM,
                new int[] {R.id.imageView, R.id.textView_title, R.id.textView_money, R.id.textView_number}, 0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(){
            /** Binds the Cursor column defined by the specified index to the specified view */
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                if(view.getId() == R.id.imageView){

                    switch (cursor.getString(cursor.getColumnIndex(FROM[0]))){
                        case "product1":
                            ((ImageView)view).setImageResource(R.drawable.product1);
                            break;
                        case "product2":
                            ((ImageView)view).setImageResource(R.drawable.product2);
                            break;
                        case "product3":
                            ((ImageView)view).setImageResource(R.drawable.product3);
                            break;
                        case "product4":
                            ((ImageView)view).setImageResource(R.drawable.product4);
                            break;
                        case "product5":
                            ((ImageView)view).setImageResource(R.drawable.product5);
                            break;
                        case "product6":
                            ((ImageView)view).setImageResource(R.drawable.product6);
                            break;
                    }
                    return true; //true because the data was bound to the view
                }
                else
                    return false;
            }
        });

        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        tos = Toast.makeText(this ,"",Toast.LENGTH_SHORT);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        it = new Intent();

        money = 0;
        number = 0;
        total = 0;
        count = 0;

        output_total = findViewById(R.id.textView_total);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        cursor = database.rawQuery("SELECT  *  FROM  " + table_name , null);

        if (cursor.moveToPosition(position)){
            switch (cursor.getString(cursor.getColumnIndex(FROM[0]))){
                case "product1":
                    it.setClass(this, Product1_Activity.class);
                    startActivity(it);
                    break;
                case "product2":
                    it.setClass(this, Product2_Activity.class);
                    startActivity(it);
                    break;
                case "product3":
                    it.setClass(this, Product3_Activity.class);
                    startActivity(it);
                    break;
                case "product4":
                    it.setClass(this, Product4_Activity.class);
                    startActivity(it);
                    break;
                case "product5":
                    it.setClass(this, Product5_Activity.class);
                    startActivity(it);
                    break;
                case "product6":
                    it.setClass(this, Product6_Activity.class);
                    startActivity(it);
                    break;
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        database.delete(table_name,"_id="+ cursor.getInt(0),null);
        cursor = database.rawQuery("SELECT  *  FROM  " + table_name , null);

        adapter = new SimpleCursorAdapter(this,
                R.layout.listview_item, cursor, FROM,
                new int[] {R.id.imageView, R.id.textView_title, R.id.textView_money, R.id.textView_number}, 0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(){
            /** Binds the Cursor column defined by the specified index to the specified view */
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                if(view.getId() == R.id.imageView){

                    switch (cursor.getString(cursor.getColumnIndex(FROM[0]))){
                        case "product1":
                            ((ImageView)view).setImageResource(R.drawable.product1);
                            break;
                        case "product2":
                            ((ImageView)view).setImageResource(R.drawable.product2);
                            break;
                        case "product3":
                            ((ImageView)view).setImageResource(R.drawable.product3);
                            break;
                        case "product4":
                            ((ImageView)view).setImageResource(R.drawable.product4);
                            break;
                        case "product5":
                            ((ImageView)view).setImageResource(R.drawable.product5);
                            break;
                        case "product6":
                            ((ImageView)view).setImageResource(R.drawable.product6);
                            break;
                    }
                    return true; //true because the data was bound to the view
                }
                else
                    return false;
            }
        });

        listView.setAdapter(adapter);

        tos.setText("已刪除訂單");
        tos.show();
        return true;
    }

    public void Pay(View view){
        if(view.getId() == R.id.button_pay && count == 0){
            cursor = database.rawQuery("SELECT  *  FROM  " + table_name , null);
            total = 0;
            index = 0;

            while(cursor.moveToNext()){
                string_money = cursor.getString(cursor.getColumnIndex(FROM[2])).substring(1);
                string_number = cursor.getString(cursor.getColumnIndex(FROM[3])).substring(4);
                money = Integer.parseInt(string_money);
                number = Integer.parseInt(string_number);

                total = total + money*number;
                database.delete(table_name,"_id=" + cursor.getInt(0), null);
                index++;
            }

            cursor = database.rawQuery("SELECT  *  FROM  " + table_name , null);
            adapter = new SimpleCursorAdapter(this,
                    R.layout.listview_item, cursor, FROM,
                    new int[] {R.id.imageView, R.id.textView_title, R.id.textView_money, R.id.textView_number}, 0);

            listView.setAdapter(adapter);

            count = 1;
            if(total == 0){
                tos.setText("沒有任何訂單資料!");
                tos.show();
            }
            else
                output_total.setText("總共: " + String.valueOf(total) + " 元");
        }
        else if(count == 1) {
            tos.setText("已結帳囉!");
            tos.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                it.setClass(this, MainActivity.class);
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
        inflater.inflate(R.menu.actionbar_buy, menu);
        return true;
    }
}
