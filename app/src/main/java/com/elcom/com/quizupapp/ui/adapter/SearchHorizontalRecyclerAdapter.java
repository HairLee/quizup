package com.elcom.com.quizupapp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Search;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class SearchHorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Search> mList;
    private OnItemClickListener mItemClickListener;
    private static final int USER_ITEM = 1;
    private static final int TOPIC_ITEM = 2;
    public SearchHorizontalRecyclerAdapter(List<Search> list) {
        this.mList = list;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private CircularImageView imvIconGame;
        private TextView tvTopicTitle;
        private TextView txt_topic;
        private CellViewHolder(View itemView) {
            super(itemView);
            imvIconGame = (CircularImageView) itemView.findViewById(R.id.imv_game_icon);
            tvTopicTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
            txt_topic = (TextView)itemView.findViewById(R.id.txt_topic);
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


    private class TopicCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private CircularImageView imvIconGame;
        private TextView tvTopicTitle;
        private TextView txt_topic,tvLevel;
        private View viewTop;
        private ImageView imvFollow;
        private TopicCellViewHolder(View itemView) {
            super(itemView);
            imvIconGame = (CircularImageView) itemView.findViewById(R.id.imv_game_icon);
            tvTopicTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
            txt_topic = (TextView)itemView.findViewById(R.id.txt_topic);
            viewTop = itemView.findViewById(R.id.viewTop);
            imvFollow = itemView.findViewById(R.id.imvFollow);
            tvLevel = itemView.findViewById(R.id.tvLevel);
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

            case USER_ITEM : {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_user_item_type_title, viewGroup, false);
                return new CellViewHolder(v1);
            }

            case TOPIC_ITEM : {
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_topic_item_type_title, viewGroup, false);
                return new TopicCellViewHolder(v2);
            }

            default: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_user_item_type_title, viewGroup, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {

            case TOPIC_ITEM: {
                TopicCellViewHolder topicCellViewHolder = (TopicCellViewHolder) viewHolder;

                final Search topicSearch = mList.get(position);

                if (topicSearch != null){
                    if (position == 0) {
                        topicCellViewHolder.viewTop.setVisibility(View.GONE);
                    }

                    topicCellViewHolder.txt_topic.setText(topicSearch.getDescription());
                    topicCellViewHolder.tvLevel.setText("Level "+ topicSearch.getLevel());

                    topicCellViewHolder.tvTopicTitle.setText(mList.get(position).getName());

                    if (topicSearch.getStatusFollow() == 1) {
                        topicCellViewHolder.imvFollow.setImageResource(R.drawable.search_follow_ic);
                    } else {
                        topicCellViewHolder.imvFollow.setImageResource(R.drawable.search_unfollow_ic);
                    }

                    topicCellViewHolder.imvFollow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mItemClickListener.onItemClick(view,topicSearch);
                        }
                    });


                    Picasso.get()
                            .load(mList.get(position).getUrl())
                            .into(topicCellViewHolder.imvIconGame);


                }


                break;
            }


            case USER_ITEM : {
                CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;

                if (mList.get(position) != null){
                    cellViewHolder.tvTopicTitle.setText(mList.get(position).getName());

                    if (mList.get(position).getStatusFollow() == 1){
                        cellViewHolder.txt_topic.setText("Following");
                    } else  {
                        cellViewHolder.txt_topic.setText("");
                    }


                    Picasso.get()
                            .load(mList.get(position).getUrl())
                            .into(cellViewHolder.imvIconGame);
                }

                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(mList.get(position).getTopicId().equals("")){

            return USER_ITEM;
        } else  {
            return TOPIC_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Search search);

        void onItemLongClick(View view, int position);
    }

    // for both short and long click
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}