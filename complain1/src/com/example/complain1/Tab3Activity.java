package com.example.complain1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.complain1.bean.ComplainContent;
import com.example.complain1.utils.Constant;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/16.
 */
public  class Tab3Activity  extends Activity{
    private ImageView searchView;
    private TextView inputView;
    private EditText editText1,editText2,editText4,editText5;
    private ComplainContent complainContent;
    private String shopName;
    private GridView photo;
    private ArrayList<Integer> photos = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3);
        init();
        initListener();
    }

    private void init(){
        searchView = (ImageView)this.findViewById(R.id.tab3_1_search);
        inputView = (TextView)this.findViewById(R.id.tab3_top_rightBtn);
        editText1 = (EditText)this.findViewById(R.id.tab3_1_edit);
        editText2 = (EditText)this.findViewById(R.id.tab3_2_edit);
        editText4 = (EditText)this.findViewById(R.id.tab3_4_edit);
        editText5 = (EditText)this.findViewById(R.id.tab3_5_edit);
        complainContent = new ComplainContent();
        shopDetailTo();
    }

    private void initListener(){
        inputView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tab3Activity.this,PhoneVerificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
        takePhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tab3Activity.this, Tab3_photoActivity.class);
                intent.putExtra("from","main");
                startActivityForResult(intent, Constant.RequestCode);
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tab3Activity.this,SearchActivity.class);
                intent.putExtra("from","main");
                startActivity(intent);
            }
        });
    }
//如果是商家详情界面跳转过来的
    private void shopDetailTo(){
        if(getIntent() != null){
            editText1.setText(getIntent().getStringExtra("shopName"));
            editText2.requestFocus();
        }
    }
//获取输入
    private void setComplainContent(){
        complainContent.setShopName(editText1.getText().toString());
        complainContent.setComplainContent(editText2.getText().toString());
        complainContent.setAppearInfo(editText4.getText().toString());
        complainContent.setPhoneNum(editText5.getText().toString());
    }

    @Override
    protected void onResume() {
        Log.d("BroadcastReceiver","shopName:" + shopName);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.COMPLAIN_NAME);
        registerReceiver(ShopNameReceiver,filter);
        super.onResume();
    }
//注册广播，接受从获取商家名称页面回来的商家名
    public BroadcastReceiver ShopNameReceiver = new  BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            shopName = intent.getStringExtra("shopName");
            Log.d("BroadcastReceiver","BroadcastReceiver");
            editText1.setText(shopName);
            editText2.requestFocus();
        }
    };

    //照片显示
    private void initPhoto(){
        photo = (GridView)this.findViewById(R.id.tab3_3_photo);


    }
    private void setPhotos(){
        photos.add(R.drawable.tab3_takephoto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Constant.ResultCode){
            String imgKey = data.getStringExtra("imgkey");
            if(imgKey != null){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
