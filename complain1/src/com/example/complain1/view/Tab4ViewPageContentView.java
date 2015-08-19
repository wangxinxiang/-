package com.example.complain1.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.complain1.R;
import com.example.complain1.adapter.ComplainListViewAdapter;
import com.example.complain1.bean.ComplainContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/16.
 */
public class Tab4ViewPageContentView extends LinearLayout {
    private ArrayList<ComplainContent> complainContents;
    private ListView listView;
    private PullToRefreshListView mPullRefreshListView;
    private Handler endHandle;
    private Handler headHandler;

    public Tab4ViewPageContentView(Context context, ArrayList<ComplainContent> complainContents){
        super(context);
        this.complainContents =complainContents;
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab4_viewpage1,this);
        initPullRefresh();
        initListView();

    }

    private void initListView(){

        final ComplainListViewAdapter adapter = new ComplainListViewAdapter(getContext(),complainContents);
        listView.setAdapter(adapter);
        listView.setDivider(getResources().getDrawable(R.drawable.divider));
        listView.setDividerHeight(20);
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
