package com.askjeffreyliu.expandablerecyclerviewtestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class ChildDiffUtilCallback extends DiffUtil.Callback {
    private List<MyGroupChild> newList;
    private List<MyGroupChild> oldList;

    public ChildDiffUtilCallback(List<MyGroupChild> oldList, List<MyGroupChild> newList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        if (oldList == null) {
            return 0;
        }
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        if (newList == null) {
            return 0;
        }
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MyGroupChild newMyGroupChild = newList.get(newItemPosition);
        MyGroupChild oldMyGroupChild = oldList.get(oldItemPosition);
        return newMyGroupChild.isSelected() == oldMyGroupChild.isSelected();
    }


    public static final String DIFF_KEY_SELECTED = "isSelected";

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        MyGroupChild newMyGroupChild = newList.get(newItemPosition);
        MyGroupChild oldMyGroupChild = oldList.get(newItemPosition);

        Bundle diff = new Bundle();
        if (newMyGroupChild.isSelected() != oldMyGroupChild.isSelected()) {
            diff.putBoolean(DIFF_KEY_SELECTED, newMyGroupChild.isSelected());
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}