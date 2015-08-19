package com.example.complain1.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by wang on 2015/3/19.
 */
public class ParamsUtil {

    private View view;
    private Context context;
    private int view_height = 0;
    private int view_width = 0;
//既得组件信息，也存入SharedPreferences中
    public ParamsUtil(View view,Context context) {
        this.view = view;
        this.context = context;
    }
//只存信息进去
    public ParamsUtil(Context context) {
        this.context = context;
    }
//只获得组件信息
    public ParamsUtil(View view) {
        this.view = view;
    }

    public ParamsUtil(){}

    //获取组件高度
    public int getBottomTab_height(){
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view_height = view.getHeight();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return view_height;
    }
    //获取组件宽度
    public int getViewWidth(){
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view_width = view.getWidth();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return view_width;
    }
    //获取组件X,Y坐标,和子类相对于父类的上下左右坐标
    public int[] getViewLocation(){
        int[] location = new int[6];
        view.getLocationOnScreen(location);
        location[2] = view.getLeft();
        location[3] = view.getRight();
        location[4] = view.getTop();
        location[5] = view.getBottom();
        return location;
    }

    //将信息存入SharedPreferences中
    public void setParamInPreference(String xmlName,String paramName){
        SharedPreferences preferences = context.getSharedPreferences(xmlName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(paramName,view_height);
        editor.commit();
    }

//        private void setTab1MarginBottom(Context context){
//        SharedPreferences sharedPreferences = getSharedPreferences("preferences",Activity.MODE_PRIVATE);
//        int main_bottomHeight = sharedPreferences.getInt("bottomTab_height",0);
//        LinearLayout tab1 = (LinearLayout)this.findViewById(R.id.tab1);
//        LinearLayout.LayoutParams  layoutParams= (LinearLayout.LayoutParams)tab1.getLayoutParams();
//        layoutParams.bottomMargin = main_bottomHeight;
//        tab1.setLayoutParams(layoutParams);
//    }
}
