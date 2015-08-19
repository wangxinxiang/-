package com.example.complain1.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.example.complain1.R;
import com.example.complain1.ShopListActivity;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/16.
 */
public class Tab1ViewPage1ContentView extends LinearLayout {
    //获取导航按钮
    private RadioButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    private ArrayList<String> kinds;

    public Tab1ViewPage1ContentView(Context context,ArrayList<String> kinds){
        super(context);
        this.kinds = kinds;
        init();
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab1_viewpage1,this);

        btn1 = (RadioButton)findViewById(R.id.tab1_viewPage1_1);
        btn2 = (RadioButton)findViewById(R.id.tab1_viewPage1_2);
        btn3 = (RadioButton)findViewById(R.id.tab1_viewPage1_3);
        btn4 = (RadioButton)findViewById(R.id.tab1_viewPage1_4);
        btn5 = (RadioButton)findViewById(R.id.tab1_viewPage1_5);
        btn6 = (RadioButton)findViewById(R.id.tab1_viewPage1_6);
        btn7 = (RadioButton)findViewById(R.id.tab1_viewPage1_7);
        btn8 = (RadioButton)findViewById(R.id.tab1_viewPage1_8);

    //响应按钮执行页面跳转
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(0));
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(1));
            }
        });
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(2));
            }
        });
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(3));
            }
        });
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(4));
            }
        });
        btn6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(5));
            }
        });
        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(6));
            }
        });
        btn8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToResult(kinds.get(7));
            }
        });
    }

    private void intentToResult(String kind){
        Intent intent = new Intent(getContext(),ShopListActivity.class);
        intent.putExtra("kind",kind);
        getContext().startActivity(intent);
    }

//    class ViewPageOnTouchListener implements OnTouchListener{
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            view.getParent().requestDisallowInterceptTouchEvent(true);
//            return false;
//        }
//    }
}
