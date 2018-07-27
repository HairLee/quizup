package com.elcom.com.quizupapp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class HomePlayingRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Topic> mList;
    private OnItemClickListener mItemClickListener;
    private static final int HISTORY_SOLO = 1;
    private static final int HOME_PLAYING = 2;

    public HomePlayingRecyclerAdapter(List<Topic> list) {
        this.mList = list;
    }

    private class ChallengeCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imvAva;
        private TextView tvNumberOfWin,tvTime;
        private Button btnContinueToPlay;
        private View viewId;

        private ChallengeCellViewHolder(View itemView) {
            super(itemView);
            imvAva = itemView.findViewById(R.id.imvAva);
            tvNumberOfWin = itemView.findViewById(R.id.tvNuberOfWin);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnContinueToPlay = itemView.findViewById(R.id.btnContinueToPlay);
            viewId = itemView.findViewById(R.id.viewId);

        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {

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

        View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_playing_child_vertical, viewGroup, false);
        return new ChallengeCellViewHolder(v2);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        switch (viewHolder.getItemViewType()) {
            default: {
                ChallengeCellViewHolder cellViewHolder = (ChallengeCellViewHolder) viewHolder;
                cellViewHolder.tvNumberOfWin.setText(mList.get(position).getJump_wins());

                Picasso.get()
                        .load(mList.get(position).getUrl())
                        .into(cellViewHolder.imvAva);

                cellViewHolder.btnContinueToPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemClickListener.onItemClick(view, mList.get(position));
                    }
                });

                if(position == mList.size() - 1){
                    cellViewHolder.viewId.setVisibility(View.GONE);
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

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return HOME_PLAYING;

            default:
                return HOME_PLAYING;
        }
    }
}