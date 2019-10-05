package com.example.balaginjo.finalproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;

import java.util.Timer;
import java.util.TimerTask;

public class StoreInformationActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    ViewPagerIndicator viewPagerIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("店家資訊 :3");
        setContentView(R.layout.activity_store_information);

        initial();
    }

    protected void initial(){
        viewPager = findViewById(R.id.viewpager);
        //index要修改
        adapter = new ViewPagerAdapter(this,0);
        viewPager.setAdapter(adapter);

        viewPagerIndicator = findViewById(R.id.indicator);
        viewPagerIndicator.setViewPager(viewPager);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),1500,5000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
        inflater.inflate(R.menu.actionbar_only_back, menu);
        return true;
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            StoreInformationActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0)
                        viewPager.setCurrentItem(1);
                    else if(viewPager.getCurrentItem() == 1)
                        viewPager.setCurrentItem(2);
                    else if(viewPager.getCurrentItem() == 2)
                        viewPager.setCurrentItem(3);
                    else if(viewPager.getCurrentItem() == 3)
                        viewPager.setCurrentItem(4);
                    else //if(viewPager.getCurrentItem() == 4)
                        viewPager.setCurrentItem(0);
                }
            });
        }
    }
}
