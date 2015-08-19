package com.example.complain1.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by wang on 2015/4/3.
 */
public class Tab1ViewPageForScrollView extends ViewPager{
    private int mLastMotionY;
    private int mLastMotionX;
    private int deltaY,deltaX;

    public Tab1ViewPageForScrollView(Context context) {
        super(context);
    }

    public Tab1ViewPageForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("PullToRefreshView","Tab1ViewPageForScrollView:ACTION_DOWN");
                mLastMotionX = x;
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                deltaY = y - mLastMotionY;
                deltaX = x - mLastMotionX;
                Log.d("PullToRefreshView","Tab1ViewPageForScrollView:deltaY,deltaX:" + deltaY + "," + deltaX);
            case MotionEvent.ACTION_UP:
                Log.d("PullToRefreshView","Tab1ViewPageForScrollView:ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int x = (int)ev.getX();
//        int y = (int)ev.getY();
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.d("PullToRefreshView","Tab1ViewPageForScrollView:ACTION_DOWN");
//                mLastMotionX = x;
//                mLastMotionY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                deltaY = y - mLastMotionY;
//                deltaX = x - mLastMotionX;
//                Log.d("PullToRefreshView","Tab1ViewPageForScrollView:deltaY,deltaX:" + deltaY + "," + deltaX);
//                if(Math.abs(deltaY) > Math.abs(deltaX)){
//                    return false;
//                }
//                else {
//                    return true;
//                }
//            case MotionEvent.ACTION_UP:
//                Log.d("PullToRefreshView","Tab1ViewPageForScrollView:ACTION_UP");
//                break;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("PullToRefreshView","Tab1ViewPageForScrollView:onTouchEvent:" + (Math.abs(deltaY) < Math.abs(deltaX)));
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            if(Math.abs(deltaY) < Math.abs(deltaX)){
                getParent().requestDisallowInterceptTouchEvent(true);
            }

        }
        return super.onTouchEvent(ev);
    }
}
