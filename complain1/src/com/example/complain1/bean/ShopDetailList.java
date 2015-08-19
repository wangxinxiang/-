package com.example.complain1.bean;

import android.content.Intent;

/**
 * Created by wang on 2015/3/17.
 */
public class ShopDetailList {
    //商家头像
    private String imgResource;
    //商家名称
    private String shopName;
    //被投诉次数
    private String complainNumber;
    //商家类别
    private String shopKind;
    //商家地址
    private String shopAdd;
    //距离
    private String distance;
    //要跳转的商家详情界面
    private Intent intent;

    public String getImgResource() {
        return imgResource;
    }

    public void setImgResource(String imgResource) {
        this.imgResource = imgResource;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getComplainNumber() {
        return complainNumber;
    }

    public void setComplainNumber(String complainNumber) {
        this.complainNumber = complainNumber;
    }

    public String getShopKind() {
        return shopKind;
    }

    public void setShopKind(String shopKind) {
        this.shopKind = shopKind;
    }

    public String getShopAdd() {
        return shopAdd;
    }

    public void setShopAdd(String shopAdd) {
        this.shopAdd = shopAdd;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
