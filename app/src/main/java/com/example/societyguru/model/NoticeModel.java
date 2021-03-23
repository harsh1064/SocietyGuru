package com.example.societyguru.model;

import java.security.Timestamp;

public class NoticeModel {
    String title;
    String description;
    String noticeId;
    String from;
    Timestamp createdAt;

    public NoticeModel(String title, String description) {
        this.title = title;
        this.description = description;
        this.noticeId = noticeId;
        this.from = from;
        this.createdAt = createdAt;
    }

    public NoticeModel(){
        
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}