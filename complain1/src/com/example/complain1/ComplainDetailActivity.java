package com.example.complain1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.complain1.adapter.CommentListViewAdapter;
import com.example.complain1.adapter.ImgViewPagerAdapter;
import com.example.complain1.bean.Comment;
import com.example.complain1.bean.ComplainContent;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/27.
 */
public class ComplainDetailActivity extends Activity{
    private ImageButton back;
    private TextView complainPhoneNum,complainShopName,complainTime,complainContent,complainAppeal;
    private TextView handleTime,handleState,handler,handlerResult,shopReply;
    private ListView comment;
    private ComplainContent complainInfo;
    private LinearLayout chat;
    private EditText chat_text;
    private Button chat_send;
    private ViewPager imgList;
    private ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
    private Context context;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private PullToRefreshScrollView mPullRefreshScrollView;
    private Handler endHandle;
    private Handler headHandler;
    private ScrollView scrollView;
    private boolean chatVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_detail);
        context = this;
        init();
    }

    private void init(){
        back = (ImageButton)this.findViewById(R.id.complain_detail_back);
        scrollView = (ScrollView)this.findViewById(R.id.complain_detail_scrollView);
        initComplainView();
        initHandle();
        initChat();
        initListView();
        initImgViewPager();
        initListener();
        initPullRefresh();
    }

//初始化投诉信息
    private void initComplainView(){
        complainPhoneNum = (TextView)this.findViewById(R.id.complain_detail_phoneNum);
        complainShopName = (TextView)this.findViewById(R.id.complain_detail_shopName);
        complainTime = (TextView)this.findViewById(R.id.complain_detail_time);
        complainContent = (TextView)this.findViewById(R.id.complain_detail_complainContent);
        complainAppeal = (TextView)this.findViewById(R.id.complain_detail_appealContent);
        setComplainInfo();
        complainPhoneNum.setText(complainInfo.getPhoneNum() + "");
        complainShopName.setText(complainInfo.getShopName());
        complainTime.setText(complainInfo.getMessageTime());
        complainContent.setText(complainInfo.getComplainContent());
        complainAppeal.setText(complainInfo.getAppearInfo());
    }

//获取上个界面传来的投诉信息
    private void setComplainInfo(){
        complainInfo = new ComplainContent();
        complainInfo.setPhoneNum(getIntent().getStringExtra("phoneNum"));
        complainInfo.setShopName(getIntent().getStringExtra("shopName"));
        complainInfo.setComplainContent(getIntent().getStringExtra("complainText"));
        complainInfo.setAppearInfo(getIntent().getStringExtra("appealText"));
        complainInfo.setMessageNum(getIntent().getIntExtra("messageNum", 0));
        complainInfo.setMessageTime(getIntent().getStringExtra("timeText"));
    }

//初始化商家处理信息
    private void initHandle(){
        handleTime = (TextView)this.findViewById(R.id.complain_detail_handle_time);
        handleState = (TextView)this.findViewById(R.id.complain_detail_handle_state);
        handler = (TextView)this.findViewById(R.id.complain_detail_handle_handler);
        handlerResult = (TextView)this.findViewById(R.id.complain_detail_handle_result);
        shopReply = (TextView)this.findViewById(R.id.complain_detail_handle_reply);

    }

    //初始化聊天框
    private void initChat(){
        chat = (LinearLayout)this.findViewById(R.id.complain_detail_chat);
        chat_text = (EditText)this.findViewById(R.id.complain_detail_chat_edit);
        chat_send = (Button)this.findViewById(R.id.complain_detail_chat_send);
        chat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == false){
                    chat.setVisibility(View.GONE);
                }
            }
        });
    }
//设置评论回复信息列表
    private void initListView(){
        setComment();
        comment = (ListView)this.findViewById(R.id.complain_detail_listView);
        final CommentListViewAdapter adapter = new CommentListViewAdapter(context,comments);
        adapter.setReplyOnClickListener(new CommentListViewAdapter.ReplyListener() {
            @Override
            public void replyOnClickListener() {
                chat.setVisibility(View.VISIBLE);
            }
        });
        comment.setAdapter(adapter);
        endHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                adapter.count += 5;
                if(adapter.count > comments.size()){
                    adapter.count = comments.size();
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

    //设置chat_send的延迟消失时间
    Handler chatHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            chat.setVisibility(View.GONE);
        }
    };
    private void initListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatHandle.postDelayed(null,1000);
            }
        });
    }

//设置商家投诉照片展示
    private void initImgViewPager(){
        imgList = (ViewPager)this.findViewById(R.id.complain_detail_imgList);
        setImageViews();
        ImgViewPagerAdapter adapter = new ImgViewPagerAdapter(imageViews);
        imgList.setAdapter(adapter);
    }
//获取要展示的投诉图片
    private void setImageViews(){
        for(int i = 0;i < 5;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.tab1_listview_1_img);
            imageViews.add(imageView);
        }
    }
    //获取评论信息
    private void setComment(){
        for(int i = 0;i < 10; i++){
            Comment comment = new Comment();
            comment.setPhoneNum("18296****3" + i);
            comment.setRank(i + "");
            comment.setTime("8分钟前");
            comment.setCommentText("商家处理的还不错" + i);
            if(i > 0){
                comment.setRepeatObject("18296****3" + (i - 1));
            }
            comments.add(comment);
        }
    }

    //设置刷新控件
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
}
