package com.elcom.eonline.quizupapp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elcom.eonline.quizupapp.R;
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.Topic;
import com.elcom.eonline.quizupapp.ui.listener.OnHistoryListListener;
import com.elcom.eonline.quizupapp.ui.listener.OnSeeMoreTopicsListener;
import com.elcom.eonline.quizupapp.utils.ConstantsApp;
import com.elcom.eonline.quizupapp.utils.PreferUtils;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created Hailpt on 3/21/17.
 */
public class HomeHistoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Topic> mList;
    private OnItemClickListener mItemClickListener;
    private OnHistoryListListener onHistoryListListener ;
    private static final int HISTORY_SOLO = 1;
    private static final int HISTORY_CHALLENGE = 2;
    private boolean clickable = true;
    private Context mContext;
    public HomeHistoryRecyclerAdapter(List<Topic> list, Context context) {
        mContext = context;
        this.mList = list;
    }


    private class SoloCellViewHolder extends RecyclerView.ViewHolder {


        private SoloCellViewHolder(View itemView) {
            super(itemView);


        }

    }

    private class ChallengeCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private CircleImageView imvAva;
        private TextView tvNameOfTopic,tvNumberOfWins,tvCoins,tvEx,tvName,tvTime;
        private RelativeLayout rlBottom;
        private  ImageView imvRemove;


        private ChallengeCellViewHolder(View itemView) {
            super(itemView);
            imvAva = (CircleImageView) itemView.findViewById(R.id.imvAva);
            tvNameOfTopic = (TextView)itemView.findViewById(R.id.tvNameOfTopic);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvNumberOfWins = (TextView)itemView.findViewById(R.id.tvNumberOfWins);
            tvCoins = (TextView)itemView.findViewById(R.id.tvCoins);
            rlBottom = itemView.findViewById(R.id.rlBottom);
            tvEx = (TextView)itemView.findViewById(R.id.tvEx);
            tvTime = (TextView)itemView.findViewById(R.id.tvTime);
            imvRemove = itemView.findViewById(R.id.imvRemove);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            imvRemove.setOnLongClickListener(this);
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

        switch (viewType) {
            case HISTORY_SOLO: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_play_chalenge, viewGroup, false);
                return new SoloCellViewHolder(v1);
            }

            default: {
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_play_chalenge, viewGroup, false);
                return new ChallengeCellViewHolder(v2);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        switch (viewHolder.getItemViewType()) {
            default: {

                ChallengeCellViewHolder cellViewHolder = (ChallengeCellViewHolder) viewHolder;
                cellViewHolder.tvNameOfTopic.setText(mList.get(position).getName());
                cellViewHolder.rlBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickable) {
                            onHistoryListListener.onPlayTopicAgain(mList.get(position));
                            clickable = false;
                        }
                    }
                });

                cellViewHolder.imvRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onHistoryListListener.onRemoveHistory(mList.get(position));
                    }
                });

//                if(!ConstantsApp.USER_AVATAR_ME.equals("")){
                    Picasso.get()
                            .load(new PreferUtils().getAvatar(mContext))
                            .into(cellViewHolder.imvAva);
//                }

                cellViewHolder.tvEx.setText("+"+mList.get(position).getBonus());
//                cellViewHolder.tvName.setText(PreferUtils.get);
                cellViewHolder.tvNumberOfWins.setText("Chuỗi thắng : "+mList.get(position).getJump_wins());
                cellViewHolder.tvTime.setText(mList.get(position).getTime_ago());

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

    public void setOnHistoryListListener(final OnHistoryListListener onHistoryListListener) {
        this.onHistoryListListener = onHistoryListListener;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return HISTORY_CHALLENGE;

            default:
                return HISTORY_CHALLENGE;
        }
    }
}