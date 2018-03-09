package com.model;

public class Topic {
    private int topicId;
    private int boardId;
    private String topicTitle;
    private String topicContent;
    private int userId;
    private String topicPublishtime;
    private String topicModifytime;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTopicPublishtime() {
        return topicPublishtime;
    }

    public void setTopicPublishtime(String topicPublishtime) {
        this.topicPublishtime = topicPublishtime;
    }

    public String getTopicModifytime() {
        return topicModifytime;
    }

    public void setTopicModifytime(String topicModifytime) {
        this.topicModifytime = topicModifytime;
    }
}
