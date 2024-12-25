package com.example.wia2007_zerohunger.Part4;

import java.io.Serializable;

public class Consultation implements Serializable {
    private int id; // 添加 id 字段
    private String expertName;
    private String date;
    private String topicSummary;
    private String status;

    // 修改构造函数
    public Consultation(int id, String expertName, String date, String topicSummary, String status) {
        this.id = id;
        this.expertName = expertName;
        this.date = date;
        this.topicSummary = topicSummary;
        this.status = status;
    }

    // Getter 方法
    public int getId() { return id; }
    public String getExpertName() { return expertName; }
    public String getDate() { return date; }
    public String getTopicSummary() { return topicSummary; }
    public String getStatus() { return status; }
}

