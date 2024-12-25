package com.example.wia2007_zerohunger.Part4;


public class Expert {
    private String name;
    private String specialization;
    private double rating;
    private int avatarResId;


    public Expert(String name, String specialization, double rating, int avatarResId) {
        this.name = name;
        this.specialization = specialization;
        this.rating = rating;
        this.avatarResId = avatarResId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getRating() {
        return rating;
    }

    //这个是我们用来加载专家头像的，假如有多个照片我们改掉avatarId就可以了
    public int getAvatarResId() {
        return avatarResId;
    }
}
