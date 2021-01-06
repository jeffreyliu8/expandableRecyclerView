package com.askjeffreyliu.expandablerecyclerviewtestapp;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ChildListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyGroupChild> mList;
    private OnChildClickListener listener;

    private int parentId = -1;
    private int childId = -1;

    public ChildListAdapter(OnChildClickListener listener) {
        this.listener = listener;
    }

    public void updateList(List<MyGroupChild> list, int parentId, int childId) {
        this.parentId = parentId;
        this.childId = childId;
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            default: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_child_item, viewGroup, false);
                return new CellViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads == null || payloads.size() == 0) {
            onBindViewHolder(holder, position);
        } else {
            CellViewHolder cellViewHolder = (CellViewHolder) holder;
            cellViewHolder.mRadio.setChecked(false);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;

        final MyGroupChild child = mList.get(position);
        cellViewHolder.mName.setText(child.getTitle());
        if (child.getParentId() == parentId && child.getId() == childId) {
            cellViewHolder.mRadio.setChecked(true);
        } else {
            cellViewHolder.mRadio.setChecked(false);
        }
//        cellViewHolder.mRadio.setChecked(child.isSelected());
        cellViewHolder.mName.setTag(child.getParentId());
        cellViewHolder.mRadio.setTag(child.getId());
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName;
        private MyRadioButton mRadio;

        CellViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mRadio = itemView.findViewById(R.id.radioButton);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!mRadio.isChecked()) {
                mRadio.setChecked(true);
                if (listener != null) {
                    listener.onChildClicked((int) mName.getTag(), (int) mRadio.getTag());
                }
            }
        }
    }

    public interface OnChildClickListener {
        void onChildClicked(int parentId, int childId);
    }
}
