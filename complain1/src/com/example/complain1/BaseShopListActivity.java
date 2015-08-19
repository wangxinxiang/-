package com.example.complain1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.example.complain1.adapter.Tab1ListViewAdapter;
import com.example.complain1.bean.ShopDetailList;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/4/7.
 */
public abstract class BaseShopListActivity extends Activity{

    private Context context;
    protected ArrayList<ShopDetailList> shopDetailLists = new ArrayList<ShopDetailList>();
    private PullToRefreshListView mPullRefreshListView;
    private Handler endHandle;
    private Handler headHandler;
    private ListView listView;
    protected String key = null,from = null;    //key为搜索的关键字，from为实现这个类的界面
    private TextView title;
    protected String titleName;
    private ImageButton top_leftBtn,top_rightBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);
        init();
    }

//初始化
    protected void init(){
        context = this;
        setKey();
        setFrom();
        setShopDetailLists();
        title = (TextView)this.findViewById(R.id.tab2_title);
        top_leftBtn = (ImageButton)this.findViewById(R.id.tab2_top_left);
        top_rightBtn = (ImageButton)this.findViewById(R.id.tab2_top_right);
        top_leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    top_left_btnListener();
            }
        });
        top_rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    top_right_btnListener();
            }
        });
        initPullRefresh();
        initListView();
    }
//初始化listView
    private void initListView(){
        listView.setDivider(getResources().getDrawable(R.drawable.divider));
        listView.setDividerHeight(1);
        final Tab1ListViewAdapter adapter = new Tab1ListViewAdapter(context,shopDetailLists,from);
        showView(adapter);
        listView.setAdapter(adapter);
        endHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                adapter.count += 5;
                if(adapter.count > shopDetailLists.size()){
                    adapter.count = shopDetailLists.size();
                }
                adapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        };
        headHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mPullRefreshListView.onRefreshComplete();
            }
        };
    }
    //根据界面设置响应界面的view
    private void showView(Tab1ListViewAdapter adapter){
        if(from.equals(Tab1ListViewAdapter.TAB2)){
            adapter.setTab(from);   //如果是tab2
        }
        else {
            setTitleName();
            title.setText(titleName); //顶部标题名换
            top_leftBtn.setImageResource(R.drawable.back_key);
            setRightBtnHide();
        }
    }

    private void initPullRefresh(){
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_view);
        mPullRefreshListView.setFilterTouchEvents(false);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Message msg = headHandler.obtainMessage();
                headHandler.sendMessageDelayed(msg, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Message msg = endHandle.obtainMessage();
                endHandle.sendMessageDelayed(msg, 1000);
            }
        });
        listView = mPullRefreshListView.getRefreshableView();
    }
//设置是搜寻界面的顶部栏
    protected void setRightBtnHide(){
            top_rightBtn.setVisibility(View.INVISIBLE);
            top_rightBtn.setClickable(false);
    }

    protected abstract void top_left_btnListener();
    protected abstract void top_right_btnListener();
    //获取ListView所需的数据信息
    protected abstract void setShopDetailLists();
    protected abstract void setKey();
    protected abstract void setFrom();
    //设置标题名
    protected void setTitleName(){
        titleName = getResources().getString(R.string.search_title);
    }
}

