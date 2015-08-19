package com.example.complain1.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.complain1.MainActivity;
import com.example.complain1.R;
import com.example.complain1.ShopDetailActivity;
import com.example.complain1.bean.ShopDetailList;
import com.example.complain1.utils.Constant;

/**
 * Created by wang on 2015/3/17.
 */
public class Tab1ListViewContentView extends LinearLayout{
    private LinearLayout linearLayout;
    private ImageView leftImg;
    private TextView topText,midText,bottom_left,bottom_right,rightText,top_leftText;
    private ShopDetailList shopDetailList;
    private String from;

    public Tab1ListViewContentView(Context context) {
        super(context);
        init();
    }

    private void init(){
        Log.d("-------------------------------------","init()");
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab_listview,this);
        linearLayout = (LinearLayout)findViewById(R.id.tab1_listView);
        leftImg = (ImageView)findViewById(R.id.tab1_listView_img);
        topText = (TextView)findViewById(R.id.tab1_listView_top_text);
        midText = (TextView)findViewById(R.id.tab1_listView_mid_text);
        bottom_left = (TextView)findViewById(R.id.tab1_listView_bottom_left);
        bottom_right = (TextView)findViewById(R.id.tab1_listView_bottom_right);
        rightText = (TextView)findViewById(R.id.tab1_listView_rightText);
        top_leftText = (TextView)findViewById(R.id.tab_listView_top_left);
       // findViewById()

    }
//设置top_left_bg的属性，tab2中listVIew使用
    public void setTop_leftTextText(int position){
        top_leftText.setVisibility(VISIBLE);
        top_leftText.setText(position + "");
    }
//设置ListView里组件
    private void showView(){
        Log.d("shopDetailList","shopDetailList.getComplainNumber()" + shopDetailList.getComplainNumber());

        leftImg.setImageResource(R.drawable.tab1_listview_1_img);
        topText.setText(shopDetailList.getShopName());
        midText.setText(shopDetailList.getComplainNumber());
        if(shopDetailList.getComplainNumber().equals("0")){
            midText.setTextColor(getResources().getColor(R.color.main_tab_focused));
        }else{
            midText.setTextColor(getResources().getColor(R.color.main_red));
        }
        bottom_left.setText(shopDetailList.getShopKind());
        bottom_right.setText(shopDetailList.getShopAdd());
        rightText.setText(shopDetailList.getDistance());
        Log.d("-------------------------------------","stop showView()");
    }

    private void initClickListener(){
        //item被点击时进行跳转
        linearLayout.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                if(from != null && from.equals("main")){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.putExtra("shopName",shopDetailList.getShopName());
                    broadcastIntent.setAction(Constant.COMPLAIN_NAME);
                    getContext().sendBroadcast(broadcastIntent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopName",shopDetailList.getShopName());
                    intent.putExtra("complainNum",shopDetailList.getComplainNumber());
                    intent.putExtra("shopKind",shopDetailList.getShopKind());
                    intent.putExtra("distance",shopDetailList.getDistance());
                    intent.putExtra("address",shopDetailList.getShopAdd());
                    getContext().startActivity(intent);
                }
            }
        });
    }

    public void initView(ShopDetailList shopDetailList,String from){
        this.shopDetailList = shopDetailList;
        this.from = from;
        Log.d("from","Tab1ListViewContentView_from:" + from);
        initClickListener();
        showView();
    }
}
