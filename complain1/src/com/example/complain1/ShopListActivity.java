package com.example.complain1;

import android.content.Intent;
import com.example.complain1.bean.ShopDetailList;

/**
 * Created by wang on 2015/4/8.
 */
public class ShopListActivity extends BaseShopListActivity{


    @Override
    protected void top_left_btnListener() {
        finish();
    }

    @Override
    public void top_right_btnListener() {
        Intent intent = new Intent(ShopListActivity.this,SearchActivity.class);
        intent.putExtra("from",from);
        startActivity(intent);
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
        key = "";
    }

    @Override
    protected void setFrom() {
        from = "shopList";
    }
//使顶部搜寻按钮不消失
    @Override
    protected void setRightBtnHide() {
    }

    @Override
    protected void setTitleName() {
        titleName = getIntent().getStringExtra("kind");
    }
}
