package com.askjeffreyliu.expandablerecyclerviewtestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class ParentDiffUtilCallback extends DiffUtil.Callback {
    private List<MyGroup> newList;
    private List<MyGroup> oldList;

    public ParentDiffUtilCallback(List<MyGroup> oldList, List<MyGroup> newList) {
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
        return oldList.get(oldItemPosition).getTitle().equals(newList.get(newItemPosition).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MyGroup newMyGroupChild = newList.get(newItemPosition);
        MyGroup oldMyGroupChild = oldList.get(oldItemPosition);

        if (newMyGroupChild.getChildren() != oldMyGroupChild.getChildren()) {
            return false;
        }

        for (int i = 0; i < oldMyGroupChild.getChildren().size(); i++) {
            MyGroupChild oldChild = oldMyGroupChild.getChildren().get(i);
            MyGroupChild newChild = newMyGroupChild.getChildren().get(i);

            if (oldChild.isSelected() != newChild.isSelected() ||
                    !oldChild.getTitle().equals(newChild.getTitle())) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return 1;
    }
}