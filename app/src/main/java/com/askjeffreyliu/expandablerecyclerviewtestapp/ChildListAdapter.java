package com.askjeffreyliu.expandablerecyclerviewtestapp;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class ChildListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyGroupChild> mList;
    private OnChildClickListener listener;

    public ChildListAdapter(OnChildClickListener listener) {
        this.listener = listener;
    }

    public void updateList(List<MyGroupChild> list) {
//        final ChildDiffUtilCallback diffCallback = new ChildDiffUtilCallback(mList, list);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        mList = list;
//        diffResult.dispatchUpdatesTo(this);
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

        cellViewHolder.mName.setText(mList.get(position).getTitle());
        cellViewHolder.mRadio.setChecked(mList.get(position).isSelected());

        cellViewHolder.mName.setTag(mList.get(position).getParentId());
        cellViewHolder.mRadio.setTag(mList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName;
        private RadioButton mRadio;


        CellViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mRadio = itemView.findViewById(R.id.radioButton);

            mRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        if (listener != null) {
                            listener.onChildClicked((int) mName.getTag(), (int) mRadio.getTag());
                        }
                    }
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!mRadio.isChecked()) {
                mRadio.setChecked(true);
//                if (listener != null) {
//                    listener.onChildClicked(view, (int) mName.getTag(), (int) mRadio.getTag());
//                }
            }
        }
    }

    public interface OnChildClickListener {
        void onChildClicked(int parentId, int childId);
    }
}
