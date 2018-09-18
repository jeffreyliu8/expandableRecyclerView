package com.askjeffreyliu.expandablerecyclerviewtestapp;

public class MyGroupChild {
    private int parentId;
    private int id;
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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
