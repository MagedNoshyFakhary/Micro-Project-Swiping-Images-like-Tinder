package com.mego.swipingcards;

public class ItemModel {
    private int image, topSpeed;
    private String mark, model;

    public ItemModel(int image, int topSpeed, String mark, String model) {
        this.image = image;
        this.topSpeed = topSpeed;
        this.mark = mark;
        this.model = model;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}


