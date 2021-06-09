package com.slg.android.listenbook.biz.bookcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.slg.android.listenbook.R;
import com.slg.android.listenbook.biz.home.BaseActivity;
import com.slg.android.listenbook.biz.bookcity.adapter.BookCityFragmentAdapter;

public class BookCityActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{
    private ViewPager pager;
    private View vCategory;
    private View vRecommend;
    private View vRank;
    private View vSpecial;
    private int position;
    private RadioGroup radioGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_city);

        View playImg = findViewById(R.id.title_bar_play_img);
        if(playImg != null){
            playImg.setOnClickListener(this);
        }

        pager = (ViewPager) findViewById(R.id.pager);
        radioGroup = (RadioGroup) findViewById(R.id.book_store_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);

        vSpecial = findViewById(R.id.book_store_activity_special_view);
        vRank = findViewById(R.id.book_store_activity_rank_view);
        vRecommend = findViewById(R.id.book_store_activity_recommend_view);
        vCategory = findViewById(R.id.book_store_activity_category_view);

        BookCityFragmentAdapter adapter = new BookCityFragmentAdapter(getSupportFragmentManager());
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
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.title_bar_play_img:
                Intent intent = new Intent(this,PlayDetailActivity.class);
                startActivity(intent);
                break;
        }
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
                vCategory.setBackgroundResource(R.color.main_menu_text_color_red);
                vRank.setBackgroundResource(R.color.gray);
                vRecommend.setBackgroundResource(R.color.gray);
                vSpecial.setBackgroundResource(R.color.gray);
                break;
            case 1:
                vCategory.setBackgroundResource(R.color.gray);
                vRank.setBackgroundResource(R.color.main_menu_text_color_red);
                vRecommend.setBackgroundResource(R.color.gray);
                vSpecial.setBackgroundResource(R.color.gray);
                break;
            case 2:
                vCategory.setBackgroundResource(R.color.gray);
                vRank.setBackgroundResource(R.color.gray);
                vRecommend.setBackgroundResource(R.color.main_menu_text_color_red);
                vSpecial.setBackgroundResource(R.color.gray);
                break;
            case 3:
                vCategory.setBackgroundResource(R.color.gray);
                vRank.setBackgroundResource(R.color.gray);
                vRecommend.setBackgroundResource(R.color.gray);
                vSpecial.setBackgroundResource(R.color.main_menu_text_color_red);
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
