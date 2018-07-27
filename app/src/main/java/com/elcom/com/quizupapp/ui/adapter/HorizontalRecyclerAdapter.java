package com.elcom.com.quizupapp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Topic> mList;
    private OnItemClickListener mItemClickListener;

    public HorizontalRecyclerAdapter(List<Topic> list) {
        this.mList = list;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private CircularImageView imvIconGame;
        private TextView tvTopicTitle;
        private TextView txt_topic;
        private ImageView imvFavor;
        private CellViewHolder(View itemView) {
            super(itemView);
            imvIconGame = (CircularImageView) itemView.findViewById(R.id.imv_game_icon);
            tvTopicTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
            txt_topic = (TextView)itemView.findViewById(R.id.txt_topic);
            imvFavor = itemView.findViewById(R.id.imvFavor);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view,  mList.get(getLayoutPosition()));
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemLongClick(view, getLayoutPosition());
                return true;
            }
            return false;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_type_title, viewGroup, false);
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
                cellViewHolder.txt_topic.setText(mList.get(position).getNumber_played() + " lượt chơi");

                Picasso.get()
                        .load(mList.get(position).getUrl())
                        .into(cellViewHolder.imvIconGame);

                if(mList.get(position).getStatusFollow().equals("0")){
                    ((CellViewHolder) viewHolder).imvFavor.setVisibility(View.GONE);
                } else {
                    ((CellViewHolder) viewHolder).imvFavor.setVisibility(View.VISIBLE);
                }
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
        void onItemClick(View view, Topic pTopic);

        void onItemLongClick(View view, int position);
    }

    // for both short and long click
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}