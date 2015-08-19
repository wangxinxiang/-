package com.example.complain1.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.example.complain1.R;

/**
 * Created by wang on 2015/3/16.
 */
public class Tab1ViewPage2ContentView extends LinearLayout {
    //获取导航按钮
    private RadioButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;

    public Tab1ViewPage2ContentView(Context context){
        super(context);
        init();
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab1_viewpage2,this);

        btn1 = (RadioButton)findViewById(R.id.tab1_viewPage2_1);
        btn2 = (RadioButton)findViewById(R.id.tab1_viewPage2_2);
        btn3 = (RadioButton)findViewById(R.id.tab1_viewPage2_3);
        btn4 = (RadioButton)findViewById(R.id.tab1_viewPage2_4);
        btn5 = (RadioButton)findViewById(R.id.tab1_viewPage2_5);
        btn6 = (RadioButton)findViewById(R.id.tab1_viewPage2_6);
        btn7 = (RadioButton)findViewById(R.id.tab1_viewPage2_7);
        btn8 = (RadioButton)findViewById(R.id.tab1_viewPage2_8);
        //响应按钮执行页面跳转
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
