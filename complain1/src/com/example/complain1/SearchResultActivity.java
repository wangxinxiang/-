package com.example.complain1;

import android.util.Log;
import com.example.complain1.bean.ShopDetailList;

/**
 * Created by wang on 2015/4/7.
 */
public class SearchResultActivity extends BaseShopListActivity{
    @Override
    protected void top_left_btnListener() {
        finish();
    }
//结果界面没有
    @Override
    protected void top_right_btnListener() {

    }

    @Override
    protected void setShopDetailLists() {
        for(int i = 0;i < 20;i++){
            ShopDetailList shopDetailList = new ShopDetailList();
            shopDetailList.setShopName("text" + i);
            shopDetailList.setComplainNumber("" + i);
            shopDetailList.setShopKind("KTV");
            shopDetailList.setShopAdd("浙师大");
            shopDetailList.setDistance(i + "km");
            shopDetailList.setImgResource("");
            shopDetailList.setIntent(null);
            shopDetailLists.add(shopDetailList);
        }
    }

    @Override
    protected void setKey() {
        if(getIntent().getStringExtra("key") != null){
            key = getIntent().getStringExtra("key");
        }
    }

    @Override
    protected void setFrom() {
        if(getIntent().getStringExtra("from") != null){
            from = getIntent().getStringExtra("from");
            Log.d("from","SearchResultActivity_from:" + from);
        }
    }
}
