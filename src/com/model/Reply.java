package com.model;

import com.sun.scenario.effect.impl.prism.PrImage;

public class Reply {
    private int replyId;
    private String replyContent;
    private int topicId;
    private  int userId;
    private  int parentId;
    private String replyPublishTime;
    private String replyModifyTime;

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUserId() {
        return userId;
    }


    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


    public String getReplyPublishTime() {
        return replyPublishTime;
    }

    public void setReplyPublishTime(String replyPublishTime) {
        this.replyPublishTime = replyPublishTime;
    }

    public String getReplyModifyTime() {
        return replyModifyTime;
    }

    public void setReplyModifyTime(String replyModifyTime) {
        this.replyModifyTime = replyModifyTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParentId() {
        return parentId;
    }
}
