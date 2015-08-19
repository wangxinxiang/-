package com.example.complain1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.complain1.bean.Comment;
import com.example.complain1.view.CommentListViewContentView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/27.
 */
public class CommentListViewAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private ReplyListener listener;
    public int count = 10;

    public CommentListViewAdapter(Context context,ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Comment comment = comments.get(i);
        if(view == null){
            view = new CommentListViewContentView(context);
        }
        CommentListViewContentView viewContentView = (CommentListViewContentView)view;
        viewContentView.showView(comment);
        viewContentView.setCommentListener(new CommentListViewContentView.CommentListener() {
            @Override
            public void replyOnClickListener() {
                if(listener != null){
                    listener.replyOnClickListener();
                    Log.d("setCommentListener","setCommentListener");
                }
            }
        });
        return view;
    }


    public void setReplyOnClickListener(ReplyListener listener){
        this.listener = listener;
    }
    public interface ReplyListener{
        public void replyOnClickListener();
    }
}
