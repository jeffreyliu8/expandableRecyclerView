package com.askjeffreyliu.expandablerecyclerviewtestapp;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ChildListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyGroupChild> mList;

    public ChildListAdapter() {
    }

    public void updateList(List<MyGroupChild> list) {
        this.mList = list;
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
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;

        cellViewHolder.mName.setText(mList.get(position).getTitle());
        cellViewHolder.mUpDown.setText(mList.get(position).isSelected() + "");
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName;
        private TextView mUpDown;

        CellViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mUpDown = itemView.findViewById(R.id.check);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
