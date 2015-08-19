package com.example.complain1.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.complain1.R;
import com.example.complain1.bean.Comment;

/**
 * Created by wang on 2015/3/27.
 */
public class CommentListViewContentView extends LinearLayout{
    private TextView phoneNum,commentRank,time,commentText;
    private LinearLayout reply;
    private CommentListener listener;
    private TextView object_reply,object_replyObject;

    public CommentListViewContentView(Context context) {
        super(context);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_listview,this);
        phoneNum = (TextView)findViewById(R.id.complain_detail_listView_phoneNum);
        commentRank = (TextView)findViewById(R.id.complain_detail_listView_rank);
        time = (TextView)findViewById(R.id.complain_detail_listView_time);
        commentText = (TextView)findViewById(R.id.complain_detail_listView_comment);
        object_reply = (TextView)findViewById(R.id.complain_detail_listView_object_reply);
        object_replyObject = (TextView)findViewById(R.id.complain_detail_listView_object_replyObject);
        reply = (LinearLayout)findViewById(R.id.complain_detail_listView_messageReply);
        reply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.replyOnClickListener();
                    Log.d("setCommentListener","adapter.replyOnClickListener");
                }
            }
        });
    }

    public void showView(Comment comment){
        phoneNum.setText(comment.getPhoneNum());
        commentRank.setText(comment.getRank());
        time.setText(comment.getTime());
        Log.d("replyObject","replyObject:" + comment.getRepeatObject());
        if(comment.getRepeatObject() != null){
            object_replyObject.setVisibility(View.VISIBLE);
            object_reply.setVisibility(VISIBLE);
            char symbol = 64;
            String replyObject = "";
            replyObject += symbol;
            replyObject += comment.getRepeatObject();
            object_replyObject.setText(replyObject);
        }
        else {
            object_replyObject.setVisibility(View.GONE);
            object_reply.setVisibility(GONE);
        }
        commentText.setText(comment.getCommentText());
    }

    public void setCommentListener(CommentListener listener){
        this.listener = listener;
    }
    public interface CommentListener{
        public void replyOnClickListener();
    }

}
