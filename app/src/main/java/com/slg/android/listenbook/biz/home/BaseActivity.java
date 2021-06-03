package com.slg.android.listenbook.biz.home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.slg.android.listenbook.R;

public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private View textView;
    private View imgBack;
    private View playImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        textView =  findViewById(R.id.title_bar_title);

        imgBack =  findViewById(R.id.title_bar_back_img);
        playImg = findViewById(R.id.title_bar_play_img);
        View searchImg = findViewById(R.id.title_bar_search_img);
        if(searchImg != null){
            searchImg.setOnClickListener(this);
        }
        if (playImg != null){
            playImg.setOnClickListener(this);
        }

        if(textView!=null){
            textView.setOnClickListener(this);
        }
        if(imgBack != null){
            imgBack.setOnClickListener(this);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if(textView != null){
            if(textView instanceof TextView){
                TextView textTitle = (TextView)textView;
                textTitle.setText(title);
            }
        }else{
            super.setTitle(title);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.title_bar_back_img:
            case R.id.title_bar_title:
                finish();
                break;

        }
    }
}
