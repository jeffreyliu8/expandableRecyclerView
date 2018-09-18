package com.askjeffreyliu.expandablerecyclerviewtestapp;

import java.util.List;

public class MyGroup {
    private int id;
    private String title;
    private List<MyGroupChild> children;

    public MyGroup(String title, List<MyGroupChild> children) {
        this.title = title;
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public List<MyGroupChild> getChildren() {
        return children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
