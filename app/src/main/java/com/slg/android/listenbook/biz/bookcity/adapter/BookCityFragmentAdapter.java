package com.slg.android.listenbook.biz.bookcity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.slg.android.listenbook.biz.bookcity.fragments.CategoryFragment;
import com.slg.android.listenbook.biz.bookcity.fragments.RankFragment;
import com.slg.android.listenbook.biz.bookcity.fragments.RecommandFragment;
import com.slg.android.listenbook.biz.bookcity.fragments.SpecialFragment;

/**
 * Created by Darker on 2015/3/23.
 */
public class BookCityFragmentAdapter extends FragmentPagerAdapter {


    public BookCityFragmentAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new CategoryFragment();
                break;
            case 1:
                fragment = new RankFragment();
                break;
            case 2:
                fragment = new RecommandFragment();
                break;
            case 3:
                fragment = new SpecialFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4 ;
    }

}
