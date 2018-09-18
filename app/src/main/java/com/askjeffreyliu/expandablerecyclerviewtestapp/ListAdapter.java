package com.askjeffreyliu.expandablerecyclerviewtestapp;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyGroup> mListOriginal;
    private List<MyGroup> mList;
    private Context mContext;
    private SparseBooleanArray listPosition = new SparseBooleanArray();
    private int oldParentId = -1;
    private int oldChildId = -1;

    public ListAdapter(List<MyGroup> list) {
        this.mList = list;
        updateIndex(list);

    }

//    public void updateList(List<MyGroup> list) {
//        final ParentDiffUtilCallback diffCallback = new ParentDiffUtilCallback(mList, list);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//        mList = list;
//        diffResult.dispatchUpdatesTo(this);
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        switch (viewType) {
            default: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
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
            cellViewHolder.adapter.notifyItemChanged((int) payloads.get(0), "set to false");
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;

        cellViewHolder.mName.setText(mList.get(position).getTitle());
        cellViewHolder.adapter.updateList(mList.get(position).getChildren(), oldParentId, oldChildId);

        cellViewHolder.mRecyclerView.setVisibility(listPosition.get(position) ? View.VISIBLE : View.GONE);
        cellViewHolder.mUpDown.setImageResource(listPosition.get(position) ? R.drawable.ic_arrow_drop_up_black_24dp : R.drawable.ic_arrow_drop_down_black_24dp);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        final int position = holder.getAdapterPosition();
        CellViewHolder cellViewHolder = (CellViewHolder) holder;
        listPosition.put(position, cellViewHolder.mRecyclerView.getVisibility() == View.VISIBLE);

        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName;
        private ImageView mUpDown;
        private RecyclerView mRecyclerView;
        private ChildListAdapter adapter;
        private FrameLayout frameLayout;


        CellViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mUpDown = itemView.findViewById(R.id.updown);
            frameLayout = itemView.findViewById(R.id.frameLayout);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);

            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new ChildListAdapter(listener);
            mRecyclerView.setAdapter(adapter);

            frameLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listPosition.put(getAdapterPosition(), mRecyclerView.getVisibility() != View.VISIBLE);
            notifyItemChanged(getAdapterPosition());
        }
    }


    private void updateIndex(List<MyGroup> inputList) {
        if (inputList == null) {
            return;
        }

        for (int i = 0; i < inputList.size(); i++) {
            inputList.get(i).setId(i);
            for (int j = 0; j < inputList.get(i).getChildren().size(); j++) {
                inputList.get(i).getChildren().get(j).setParentId(i);
                inputList.get(i).getChildren().get(j).setId(j);
            }
        }
    }


    private ChildListAdapter.OnChildClickListener listener = new ChildListAdapter.OnChildClickListener() {
        @Override
        public void onChildClicked(int parentId, int childId) {
            if (oldParentId >= 0 && oldChildId >= 0) {
                notifyItemChanged(oldParentId, oldChildId);
            }
            oldParentId = parentId;
            oldChildId = childId;
        }
    };
}
