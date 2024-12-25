package com.example.wia2007_zerohunger.Part4;

public class Question {
    private String content; // 题目内容
    private String optionA; // 选项 A
    private String optionB; // 选项 B
    private String optionC; // 选项 C
    private String optionD; // 选项 D
    private String correctAnswer; // 正确答案

    // 构造函数
    public Question(String content, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.content = content;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Getter 方法
    public String getContent() {
        return content;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

