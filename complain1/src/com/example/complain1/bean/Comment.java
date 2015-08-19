package com.example.complain1.bean;

/**
 * Created by wang on 2015/3/27.
 */
public class Comment {
    private String phoneNum;

    private String rank;

    private String time;

    private String commentText;

    private String repeatObject;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        if(rank.equals("1")){
            this.rank = "沙发";
        }else if(rank.equals("2")){
            this.rank = "板凳";
        }else {
            this.rank = rank;
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getRepeatObject() {
        return repeatObject;
    }

    public void setRepeatObject(String repeatObject) {
        this.repeatObject = repeatObject;
    }
}
