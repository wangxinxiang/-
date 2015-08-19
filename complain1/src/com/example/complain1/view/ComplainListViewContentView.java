package com.example.complain1.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.complain1.ComplainDetailActivity;
import com.example.complain1.R;
import com.example.complain1.bean.ComplainContent;

/**
 * Created by wang on 2015/3/25.
 */
public class ComplainListViewContentView extends LinearLayout {
    private TextView phoneNum,shopName,complainText,appealText,timeText,messageNum;
    private ImageView imageView;
    private LinearLayout message;
    private Tab4ListViewListener listener;
    private LinearLayout listView;
    private ComplainContent complainContent;

    public ComplainListViewContentView(Context context) {
        super(context);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.complain_listview,this);
        phoneNum = (TextView)findViewById(R.id.tab4_listView_phone);
        shopName = (TextView)findViewById(R.id.tab4_listView_shopName);
        complainText = (TextView)findViewById(R.id.tab4_listView_complainContent);
        timeText = (TextView)findViewById(R.id.tab4_listView_time);
        appealText = (TextView)findViewById(R.id.tab4_listView_appealContent);
        messageNum = (TextView)findViewById(R.id.tab4_listView_messageNum);
        message = (LinearLayout)findViewById(R.id.tab4_listView_message);
        imageView = (ImageView)findViewById(R.id.tab4_listView_img);
        listView = (LinearLayout)findViewById(R.id.tab4_listView);

    }

    public void initView(ComplainContent complainContent){
        this.complainContent = complainContent;
        initListener();
        showView();
    }

    private void initListener(){
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.imgOnClick();
                }
            }
        });
        message.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.messageOnClick();
                }
            }
        });
        listView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ComplainDetailActivity.class);
                intent.putExtra("phoneNum",complainContent.getPhoneNum() + "");
                intent.putExtra("shopName",complainContent.getShopName());
                intent.putExtra("complainText",complainContent.getComplainContent());
                intent.putExtra("appealText",complainContent.getAppearInfo());
                intent.putExtra("messageNum",complainContent.getMessageNum() + "");
                intent.putExtra("timeText",complainContent.getMessageTime());
                getContext().startActivity(intent);
            }
        });
    }
    public void showView(){
        phoneNum.setText(complainContent.getPhoneNum() + "");
        shopName.setText(complainContent.getShopName());
        complainText.setText(complainContent.getComplainContent());
        appealText.setText(complainContent.getAppearInfo());
        messageNum.setText(complainContent.getMessageNum() + "");
        timeText.setText(complainContent.getMessageTime());
        //imageView
    }

    public void setListener(Tab4ListViewListener listener){
        this.listener = listener;
    }

    public interface Tab4ListViewListener{
        public void imgOnClick();
        public void messageOnClick();
    }

    public void setTab5ListView(){
        phoneNum.setVisibility(GONE);

    }


}
