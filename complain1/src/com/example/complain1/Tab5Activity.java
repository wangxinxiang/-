package com.example.complain1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import com.example.complain1.adapter.ComplainListViewAdapter;
import com.example.complain1.bean.ComplainContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;


/**
 * Created by wang on 2015/3/16.
 */
public class Tab5Activity  extends Activity{
    private ArrayList<ComplainContent> complainContents = new ArrayList<ComplainContent>();
    private Context context;
    private ListView listView;
    private PullToRefreshListView mPullRefreshListView;
    private Handler endHandle;
    private Handler headHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab5);
        initPullRefresh();
        initListView();

    }

    private void initListView(){
        setComplainContents();
        context = this;
        final ComplainListViewAdapter adapter = new ComplainListViewAdapter(context,complainContents);
        adapter.setTab5();
        listView.setAdapter(adapter);
        listView.setDivider(getResources().getDrawable(R.drawable.divider));
        listView.setDividerHeight(10);
        endHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                adapter.count += 5;
                if(adapter.count > complainContents.size()){
                    adapter.count = complainContents.size();
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

    private void setComplainContents(){
            ComplainContent complainContent = new ComplainContent();
            complainContent.setShopName("罗黑店的鱼馆");
            complainContent.setComplainContent("这里是投诉内容");
            complainContent.setAppearInfo("赔偿");
            complainContent.setMessageTime("15分钟前");
            complainContent.setMessageNum(20);
            complainContents.add(complainContent);

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


}
