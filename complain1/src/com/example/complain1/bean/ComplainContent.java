package com.example.complain1.bean;

/**
 * Created by wang on 2015/3/24.
 */
public class ComplainContent {
    private String shopName;

    private String complainContent;

    private String appearInfo;

    private String phoneNum;

    private String imgPath;

    private String messageTime;


    private int messageNum;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getComplainContent() {
        return complainContent;
    }

    public void setComplainContent(String complainContent) {
        this.complainContent = complainContent;
    }

    public String getAppearInfo() {
        return appearInfo;
    }

    public void setAppearInfo(String appearInfo) {
        this.appearInfo = appearInfo;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }
}
