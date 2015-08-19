package com.example.complain1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.complain1.bean.ComplainContent;
import com.example.complain1.view.ComplainListViewContentView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/25.
 */
public class ComplainListViewAdapter extends BaseAdapter{
    private ArrayList<ComplainContent> complainContents;
    private Context context;
    private String TAB4 = "TAB4";
    private String TAB5 = "TAB5";
    private String tab = TAB4;
    public int count = 10;

    public ComplainListViewAdapter(Context context, ArrayList<ComplainContent> complainContents) {
        this.context = context;
        this.complainContents = complainContents;
    }

    @Override
    public int getCount() {
        if(count > complainContents.size())
        {
            count = complainContents.size();
        }
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ComplainContent complainContent = complainContents.get(i);
        if(view == null){
            view = new ComplainListViewContentView(context);
        }
        ComplainListViewContentView complainListViewContentView = (ComplainListViewContentView)view;
        complainListViewContentView.initView(complainContent);
        complainListViewContentView.setListener(new ComplainListViewContentView.Tab4ListViewListener() {
            @Override
            public void imgOnClick() {

            }

            @Override
            public void messageOnClick() {

            }

        });
        if(tab.equals(TAB5)){
            complainListViewContentView.setTab5ListView();
        }
        return view;
    }

    public void setTab5(){
        tab = TAB5;
    }
}
