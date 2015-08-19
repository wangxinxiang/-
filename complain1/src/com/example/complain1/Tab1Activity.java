package com.example.complain1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.*;
import com.baidu.location.LocationClient;
import com.example.complain1.adapter.Tab1ListViewAdapter;
import com.example.complain1.adapter.Tab1ViewPageAdapter;
import com.example.complain1.bean.ShopDetailList;
import com.example.complain1.utils.LocationApplication;
import com.example.complain1.view.Tab1ViewPage1ContentView;
import com.example.complain1.view.Tab1ViewPage2ContentView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/16.
 */
public class Tab1Activity  extends Activity{
    public LocationClient mLocationClient = null;

    private Context mContext;
    private ArrayList<ShopDetailList> shopDetailLists;
    private ArrayList<String> kind1s,kind2s;
    private ScrollView scrollView;
    private TextView masterAddress;
    private PullToRefreshScrollView mPullRefreshScrollView;
    private Handler listViewHandle;
    private int x = 0;
    private int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        init();
        startLocation();    //开始定位
    }

    private void init(){
        mContext = this;
        scrollView = (ScrollView) this.findViewById(R.id.tab1);
        masterAddress = (TextView)this.findViewById(R.id.tab1_top_midText);
        initPullRefresh();
        initViewPage();
        initListView();
        initTopListener();
        setScrollToTop();
    }
//设置刷新控件
    private void initPullRefresh(){
        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_view);
        //设置左右滑动无效
        mPullRefreshScrollView.setFilterTouchEvents(false);
        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                startLocation();
                Message msg = handler.obtainMessage();
                handler.sendMessageDelayed(msg, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                Message msg = listViewHandle.obtainMessage();
                listViewHandle.sendMessageDelayed(msg, 1000);
            }
        });
        scrollView = mPullRefreshScrollView.getRefreshableView();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mPullRefreshScrollView.onRefreshComplete();
        }
    };


    //初始化顶部按钮监听
    private void initTopListener(){
        ImageButton top_midBtn = (ImageButton)this.findViewById(R.id.tab1_top_midBtn);
        ImageButton top_rightBtn = (ImageButton)this.findViewById(R.id.tab1_top_rightBtn);
        ImageButton top_leftBtn = (ImageButton)this.findViewById(R.id.tab1_top_leftBtn);

        top_leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masterAddress.setText("");
                startLocation();
        }
        });
        top_midBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tab1Activity.this,SearchActivity.class);
                intent.putExtra("from","tab1");
                startActivity(intent);
            }
        });
        top_rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

//初始化中部的ViewPage
    private void initViewPage(){
        setViewPageIntentData();
        ArrayList<View> views = new ArrayList<View>();
        final ImageButton tab1_center_indicate1 = (ImageButton)this.findViewById(R.id.tab1_center_indicate1);
        final ImageButton tab1_center_indicate2 = (ImageButton)this.findViewById(R.id.tab1_center_indicate2);
        final ViewPager viewPager = (ViewPager)this.findViewById(R.id.tab1_center_guide);

        Tab1ViewPage1ContentView view1 = new Tab1ViewPage1ContentView(this,kind1s);
        View view2 = new Tab1ViewPage2ContentView(mContext);
        Tab1ViewPage1ContentView view3 = new Tab1ViewPage1ContentView(this,kind2s);
        View view0 = new Tab1ViewPage2ContentView(mContext);
        views.add(view0);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        final PagerAdapter adapter = new Tab1ViewPageAdapter(mContext,views);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int position;
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
                switch (i){
                    case 0:
                        tab1_center_indicate1.setImageResource(R.drawable.tab1_center_guider_normal);
                        tab1_center_indicate2.setImageResource(R.drawable.tab1_center_guider_focused);
                        break;
                    case 1:
                        tab1_center_indicate1.setImageResource(R.drawable.tab1_center_guider_focused);
                        tab1_center_indicate2.setImageResource(R.drawable.tab1_center_guider_normal);
                        break;
                    case 2:
                        tab1_center_indicate1.setImageResource(R.drawable.tab1_center_guider_normal);
                        tab1_center_indicate2.setImageResource(R.drawable.tab1_center_guider_focused);
                        break;
                    case 3:
                        tab1_center_indicate1.setImageResource(R.drawable.tab1_center_guider_focused);
                        tab1_center_indicate2.setImageResource(R.drawable.tab1_center_guider_normal);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i == ViewPager.SCROLL_STATE_IDLE){
                    if(position == adapter.getCount() - 1){
                        viewPager.setCurrentItem(1,false);
                    }
                    else if(position == 0){
                        viewPager.setCurrentItem(2,false);
                    }
                }
            }
        });
    }
    //获取ViewPage所需的按钮跳转的按钮信息
    private void setViewPageIntentData(){
        kind1s = new ArrayList<String>();
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_1));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_2));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_3));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_4));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_5));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_6));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_7));
        kind1s.add(getResources().getString(R.string.tab1_viewPage1_8));

        kind2s = new ArrayList<String>();
        kind2s = kind1s;
    }

//初始化下部的ListView
    private void initListView(){
        final ListView listView = (ListView)this.findViewById(R.id.tab1_bottom_listView);
        setListViewData();
        final Tab1ListViewAdapter adapter = new Tab1ListViewAdapter(mContext,shopDetailLists,null);
        listView.setAdapter(adapter);

        listViewHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                View scrollViewChild = scrollView.getChildAt(0);
                if(scrollView.getScrollY() >= (scrollViewChild.getHeight() - scrollView.getHeight())){
                    adapter.count += 5;
                    if(adapter.count > shopDetailLists.size()){
                        adapter.count = shopDetailLists.size();
                    }
                    adapter.notifyDataSetChanged();
                }
                mPullRefreshScrollView.onRefreshComplete();
            }
        };
//        SharedPreferences sharedPreferences = getSharedPreferences("preferences",Activity.MODE_PRIVATE);
//        int main_bottomHeight = sharedPreferences.getInt("bottomTab_height",0);
//        int listViewHeight = new ParamsUtil(listView).getBottomTab_height();
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)listView.getLayoutParams();
//        layoutParams.height = listViewHeight-main_bottomHeight;
//        Log.d("``````````````````````````",listViewHeight + "");
//        Log.d("``````````````````````````",main_bottomHeight + "");
//        listView.setLayoutParams(layoutParams);

//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            int lastItem = 0;
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int state) {
//                if(lastItem < shopDetailLists.size()){
//                    adapter.count += 5;
//                    if(adapter.count > shopDetailLists.size()){
//                        adapter.count = shopDetailLists.size();
//                    }
//                    adapter.notifyDataSetChanged();
//                    listView.setSelection(lastItem);
//                }
//            }
//            @Override
//            public void onScroll(AbsListView absListView, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
//                lastItem = firstVisibleItem + visibleItemCount - 1;
//                if(lastItem < shopDetailLists.size()){
//                    adapter.count += 5;
//                    if(adapter.count > shopDetailLists.size()){
//                        adapter.count = shopDetailLists.size();
//                    }
//                    adapter.notifyDataSetChanged();
//                    listView.setSelection(lastItem);
//                }
//            }
//        });
    }
//获取ListView所需的数据信息
    private void setListViewData(){
        Log.d("-------------------------------------","setListViewData()");
        shopDetailLists = new ArrayList<ShopDetailList>();
        for(int i = 0;i < 20;i++){
            ShopDetailList shopDetailList = new ShopDetailList();
            shopDetailList.setShopName("SINGYOUNG复合式KTV" + i);
            shopDetailList.setComplainNumber("" + i);
            shopDetailList.setShopKind("KTV");
            shopDetailList.setShopAdd("浙师大");
            shopDetailList.setDistance(i + "km");
            shopDetailList.setImgResource("");
            shopDetailList.setIntent(null);
            shopDetailLists.add(shopDetailList);
        }
    }

//    //动态设置ListView的高度
//    private void setListViewHeight(ListView listView){
//        ListAdapter listAdapter = listView.getAdapter();
//        if(listAdapter == null){
//            return;
//        }
//        int totalHeight = 0;
//        for(int i = 0;i < listAdapter.getCount();i++){
//            View listItem = listAdapter.getView(i,null,listView);
//            listItem.measure(0, 0);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//
//    }

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

    //设置定位的开始结束
    Handler LocationHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mLocationClient.stop();
        }
    };
    private void startLocation(){
        //注册监听函数已在LocationApplication中实现,获取的地址也在其中
        mLocationClient = ((LocationApplication)getApplication()).mLocationClient;
        ((LocationApplication)getApplication()).address = masterAddress;
        mLocationClient.start();
        LocationHandle.postDelayed(null,5000);
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
