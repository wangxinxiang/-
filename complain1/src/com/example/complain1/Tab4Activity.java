package com.example.complain1;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.*;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.example.complain1.adapter.Tab4ViewPageAdapter;
import com.example.complain1.bean.ComplainContent;
import com.example.complain1.view.Tab4ViewPageContentView;

import java.util.ArrayList;


/**
 * Created by wang on 2015/3/16.
 */
public class Tab4Activity  extends Activity{
    private TextView tab1,tab2;
    private ViewPager viewPager;
    private ArrayList<ComplainContent> complainContents = new ArrayList<ComplainContent>();
    private Context context;
    private ArrayList<View> views = new ArrayList<View>();
    private TextView cursor;
    private ImageButton searchBtn;
    private int cursorX1,cursorX2,cursorY1,cursorY2,offset;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab4);
        initBtn();
        initViewPage();
        setCursorLocation();
    }

    private void initBtn(){
        searchBtn = (ImageButton)this.findViewById(R.id.tab4_search);
        tab1 = (TextView)this.findViewById(R.id.tab4_tab1);
        tab2 = (TextView)this.findViewById(R.id.tab4_tab2);
        initTabListener();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tab4Activity.this,SearchActivity.class);
                intent.putExtra("from","tab4");
                startActivity(intent);
            }
        });
    }

//    //实例化TabPageIndicator，然后与ViewPager绑在一起（核心步骤）
//    private void initIndicator(){
//        TabPageIndicator indicator = (TabPageIndicator)this.findViewById(R.id.tab4_indicator);
//        indicator.setViewPager(viewPager);
//
//    }
    private void initViewPage(){
        context = this;
        viewPager = (ViewPager)this.findViewById(R.id.tab4_viewPage);
        setComplainContents();
        Tab4ViewPageContentView view1 = new Tab4ViewPageContentView(context,complainContents);
        Tab4ViewPageContentView view2 = new Tab4ViewPageContentView(context,complainContents);
        views.add(view1);
        views.add(view2);
        viewPager.setAdapter(new Tab4ViewPageAdapter(context,views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Animation animation = null;
                Log.d("Animation","cursorX1:" + cursorX1);
                Log.d("Animation","cursorX2:" + cursorX2);
                Log.d("Animation","cursorY1:" + cursorY1);
                Log.d("Animation","cursorY2:" + cursorY2);
                switch (i){
                    case 0:
                        if(currentIndex == 1){
                            animation = new TranslateAnimation(offset,0,0,0);
                        }
                        break;
                    case 1:
                        if(currentIndex == 0){
                            animation = new TranslateAnimation(0,offset,0,0);
                        }
                        break;
                }
                currentIndex = i;
                animation.setFillAfter(true);
                animation.setDuration(300);
                cursor.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
//获得数据
    private void setComplainContents(){
        for(int i = 0;i < 20;i++){
            ComplainContent complainContent = new ComplainContent();
            complainContent.setPhoneNum("18297****34");
            complainContent.setShopName("罗黑店的鱼馆");
            complainContent.setComplainContent("这里是投诉内容");
            complainContent.setAppearInfo("赔偿");
            complainContent.setMessageTime("15分钟前");
            complainContent.setMessageNum(i);
            complainContents.add(complainContent);
        }
    }
//初始化viewPager选择按钮
    private void initTabListener(){
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(0);
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(1);
            }
        });
    }
    //获取cursor的初始tab1下位置和移动tab2下位置
    private void setCursorLocation(){
        cursor = (TextView)this.findViewById(R.id.tab4_top_cursor);
        ViewTreeObserver vto = cursor.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = tab1.getWidth();
                cursor.setWidth(width);
                int[] location = new int[2];
                tab1.getLocationOnScreen(location);
                cursorX1 = location[0];
                cursorY1 = tab1.getBottom();
                tab2.getLocationOnScreen(location);
                cursorX2 = location[0];
                cursorY2 = cursorY1;
                cursor.setTranslationX(cursorX1);
                offset = cursorX2 - cursorX1;
            }
        });
    }
}
