package com.slg.android.listenbook.biz.home.bookcity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.slg.android.listenbook.R;
import com.slg.android.listenbook.biz.home.BaseActivity;
import com.slg.android.listenbook.biz.home.bookcity.adapter.PlayDetailFragmentAdapter;

public class PlayDetailActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private View vList;
    private View vDetail;
    private int position;
    private RadioGroup radioGroup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_detail);

        ImageView searchImg = (ImageView) findViewById(R.id.title_bar_search_img);
        searchImg.setVisibility(View.INVISIBLE);

        pager = (ViewPager) findViewById(R.id.play_detail_pager);
        radioGroup = (RadioGroup) findViewById(R.id.play_detail_radiogroup);
        if(radioGroup!= null){
            radioGroup.setOnCheckedChangeListener(this);
        }

        vList = findViewById(R.id.play_detail_activity_list_view);
        vDetail= findViewById(R.id.play_detail_activity_detail_view);

        PlayDetailFragmentAdapter adapter = new PlayDetailFragmentAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(this);

        if(position == 0){
            View v = radioGroup.getChildAt(position);
            if(v!= null){
                if(v instanceof RadioButton){
                    RadioButton rb = (RadioButton) v;
                    rb.setChecked(true);
                }
            }
        }
        pager.setCurrentItem(position);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        int rbCount = radioGroup.getChildCount();

        int position = -1;

        for (int index = 0; index < rbCount; index++) {
            View v = radioGroup.getChildAt(index);
            if (v != null && v instanceof RadioButton) {
                RadioButton rb = (RadioButton) v;
                // 可以通过 RadioButton 的 isChecked() 判断是否选中了
                if (rb.isChecked()) {
                    position = index;
                    break;
                }
            }
        }
        switch (position){
            case 0:
                vList.setBackgroundResource(R.color.main_menu_text_color_red);
                vDetail.setBackgroundResource(R.color.gray);
                break;
            case 1:
                vList.setBackgroundResource(R.color.gray);
                vDetail.setBackgroundResource(R.color.main_menu_text_color_red);
                break;
        }

        if (position > -1) {
            pager.setCurrentItem(position, true);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        View v = radioGroup.getChildAt(i);
        if (v != null) {
            if (v instanceof RadioButton) {
                RadioButton rb = (RadioButton) v;
                rb.setChecked(true);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }



}
