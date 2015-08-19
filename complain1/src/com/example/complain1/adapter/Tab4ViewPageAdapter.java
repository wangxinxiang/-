package com.example.complain1.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/16.
 */
public class Tab4ViewPageAdapter extends PagerAdapter{

    private ArrayList<View> views = new ArrayList<View>();
    private Context context;

    public Tab4ViewPageAdapter(Context context, ArrayList<View> views) {
        this.views = views;
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    private void setListener(){

    }
}
