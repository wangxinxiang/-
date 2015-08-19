package com.example.complain1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.*;
import com.example.complain1.adapter.ComplainListViewAdapter;
import com.example.complain1.bean.ComplainContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/26.
 */
public class ShopDetailActivity extends Activity{
    private ArrayList<ComplainContent> complainContents = new ArrayList<ComplainContent>();
    private TextView shopName,complainNum,shopKind,distance,address;
    private LinearLayout shopEntry;
    private ImageButton phone,back;
    private Button complain;
    private ListView listView;
    private Context context;
    private PullToRefreshScrollView mPullRefreshScrollView;
    private Handler endHandle;
    private Handler headHandler;
    private ScrollView scrollView;
    private int x = 0;
    private int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_detail);
        init();

    }

    private void init(){
        scrollView = (ScrollView)this.findViewById(R.id.shop_detail_scrollView);
        shopEntry = (LinearLayout)this.findViewById(R.id.shop_detail_shopEntry);
        phone = (ImageButton)this.findViewById(R.id.shop_detail_phone);
        back = (ImageButton)this.findViewById(R.id.shop_detail_back);
        complain = (Button)this.findViewById(R.id.shop_detail_complainBtn);
        context = this;
        initShopIntroduce();
        initPullRefresh();
        initListView();
        initListener();
        setScrollToTop();
    }
//初始化投诉列表
    private void initListView(){
        setComplainContents();
        listView = (ListView)this.findViewById(R.id.shop_detail_listView);
        listView = (ListView)findViewById(R.id.shop_detail_listView);
        final ComplainListViewAdapter adapter = new ComplainListViewAdapter(context,complainContents);
        listView.setAdapter(adapter);

        endHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                adapter.count += 5;
                if(adapter.count > complainContents.size()){
                    adapter.count = complainContents.size();
                }
                adapter.notifyDataSetChanged();
                mPullRefreshScrollView.onRefreshComplete();
            }
        };
        headHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mPullRefreshScrollView.onRefreshComplete();
            }
        };
    }
//初始化商家介绍
    private void initShopIntroduce(){
        shopName = (TextView)this.findViewById(R.id.shop_detail_name);
        complainNum = (TextView)this.findViewById(R.id.shop_detail_complainNum);
        shopKind = (TextView)this.findViewById(R.id.shop_detail_kind);
        distance = (TextView)this.findViewById(R.id.shop_detail_distance);
        address = (TextView)this.findViewById(R.id.shop_detail_address);
        shopName.setText(getIntent().getStringExtra("shopName"));
        complainNum.setText(getIntent().getStringExtra("complainNum"));
        shopKind.setText(getIntent().getStringExtra("shopKind"));
        distance.setText(getIntent().getStringExtra("distance"));
        address.setText(getIntent().getStringExtra("address"));
    }
    //事件监听
    private void initListener(){
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopDetailActivity.this,Tab3Activity.class);
                intent.putExtra("shopName",getIntent().getStringExtra("shopName"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        shopEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopDetailActivity.this,MapActivity.class);
                intent.putExtra("shopName",getIntent().getStringExtra("shopName"));
                startActivity(intent);
            }
        });
    }

    private void setComplainContents(){
        for(int i = 0;i < 20;i++){
            ComplainContent complainContent = new ComplainContent();
            complainContent.setPhoneNum("18297****34");
            complainContent.setShopName("罗黑店的鱼馆");
            complainContent.setComplainContent("这里是投诉内容");
            complainContent.setAppearInfo("赔偿");
            complainContent.setMessageTime("2015-4-" + i);
            complainContent.setMessageNum(i);
            complainContents.add(complainContent);
        }
    }

    private void initPullRefresh(){
        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_view);
        //设置左右滑动无效
        mPullRefreshScrollView.setFilterTouchEvents(false);
        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                Message msg = headHandler.obtainMessage();
                headHandler.sendMessageDelayed(msg, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                Message msg = endHandle.obtainMessage();
                endHandle.sendMessageDelayed(msg, 1000);
            }
        });
        scrollView = mPullRefreshScrollView.getRefreshableView();
    }

    //解决scrollView嵌套listView初始化焦点自动于listView问题
    private boolean initial = true;
    private void setScrollToTop(){
        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(initial == true){
                    scrollView.smoothScrollTo(x,y);
                    initial = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        scrollView.smoothScrollTo(x,y);
    }
    @Override
    protected void onPause() {
        super.onPause();
        x = scrollView.getScrollX();
        y = scrollView.getScrollY();
    }
}
