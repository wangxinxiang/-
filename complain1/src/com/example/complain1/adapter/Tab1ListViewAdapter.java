package com.example.complain1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.complain1.bean.ShopDetailList;
import com.example.complain1.view.Tab1ListViewContentView;

import java.util.ArrayList;

/**
 * Created by wang on 2015/3/17.
 */
public class Tab1ListViewAdapter extends BaseAdapter{
    private Context context;
    private ShopDetailList shopDetailList;
    private ArrayList<ShopDetailList> shopDetailLists;
    public int count = 10;
    //表示tab1,tab2使用.
    private String tab = "TAB1";
    private String from;
    public final static String TAB1 = "tab1";
    public final static String TAB2 = "tab2";

    public Tab1ListViewAdapter(Context context, ArrayList<ShopDetailList> shopDetailLists,String from) {
        this.from = from;
        this.context = context;
        this.shopDetailLists = shopDetailLists;
    }

    public void setTab(String tab){
        this.tab = tab;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        shopDetailList = shopDetailLists.get(i);

        if(view == null){
            view = new Tab1ListViewContentView(context);
        }
        Tab1ListViewContentView listViewContentView = (Tab1ListViewContentView)view;
        listViewContentView.initView(shopDetailList,from);
        if(tab.equals(TAB2)){
            listViewContentView.setTop_leftTextText(i + 1);
        }
        return view;
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

}
