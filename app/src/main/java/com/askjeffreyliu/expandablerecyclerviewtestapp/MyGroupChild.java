package com.askjeffreyliu.expandablerecyclerviewtestapp;

public class MyGroupChild {
    private String title;
    private boolean isSelected;

    public MyGroupChild(String title, boolean isSelected) {
        this.title = title;
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
