package com.slg.android.listenbook.biz.bookcity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.slg.android.listenbook.biz.bookcity.fragments.PlatListFragment;
import com.slg.android.listenbook.biz.bookcity.fragments.PlayDetailFragment;

/**
 * Created by Darker on 2015/3/23.
 */
public class PlayDetailFragmentAdapter extends FragmentPagerAdapter {


    public PlayDetailFragmentAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new PlatListFragment();
                break;
            case 1:
                fragment = new PlayDetailFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2 ;
    }

}
