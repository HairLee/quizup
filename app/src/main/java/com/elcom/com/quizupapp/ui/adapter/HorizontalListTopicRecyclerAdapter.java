package com.elcom.com.quizupapp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic;
import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class HorizontalListTopicRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Topic> mList;
    private OnItemClickListener mItemClickListener;

    HorizontalListTopicRecyclerAdapter(List<Topic> list) {
        this.mList = list;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imvIconGame;
        private TextView tvTopicTitle;
        private View viewBottom;
        private CellViewHolder(View itemView) {
            super(itemView);
            imvIconGame = (ImageView) itemView.findViewById(R.id.imv_game_icon);
            tvTopicTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
            viewBottom = (View)itemView.findViewById(R.id.viewBottom);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view,  1);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemLongClick(view, 1);
                return true;
            }
            return false;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_topic_list_layout, viewGroup, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            default: {
                CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
                cellViewHolder.tvTopicTitle.setText(mList.get(position).getName());

                if(position == mList.size()-1){
                    cellViewHolder.viewBottom.setVisibility(View.INVISIBLE);
                }

//                if(position%2 == 0){
//                    cellViewHolder.imvIconGame.setImageResource(R.drawable.quizup_icon);
//                } else {
//                    cellViewHolder.imvIconGame.setImageResource(R.drawable.beautiful_life);
//                }
//
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    // for both short and long click
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}