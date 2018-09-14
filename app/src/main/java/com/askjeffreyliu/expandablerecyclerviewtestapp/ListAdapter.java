package com.askjeffreyliu.expandablerecyclerviewtestapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyGroup> mList;

    public ListAdapter(List<MyGroup> list) {
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            default: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
                return new CellViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;

        cellViewHolder.mName.setText(mList.get(position).getTitle());
//        cellViewHolder.mTextView.setTag(mList[position]);
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
        private RecyclerView mRecyclerView;

        CellViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mUpDown = itemView.findViewById(R.id.updown);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            mTextView.setChecked(true);
//            if (listener != null) {
//                listener.onTipSelected((float) mTextView.getTag());
//            }
        }
    }
}
