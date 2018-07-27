package com.elcom.com.quizupapp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy;
import com.elcom.com.quizupapp.ui.listener.OnHistoryListListener;
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreTopicsListener;

import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements Filterable {

    private List<Caterogy> mList;
    private SparseIntArray listPosition = new SparseIntArray();
    private HomePlayingRecyclerAdapter.OnItemClickListener mItemClickListener;
    private Context mContext;
    private OnSeeMoreTopicsListener onSeeMoreTopicsListener;
    private OnHistoryListListener onHistoryListListener;

    private static final int HISTORY_TOPIC = 1;
    private static final int PLAYING_TOPIC = 2;
    private static final int TOP_TOPIC = 3;

    public HomeAdapter(List<Caterogy> list, OnSeeMoreTopicsListener pOnSeeMoreTopicsListener,OnHistoryListListener onHistoryListListener) {
        this.mList = list;
        onSeeMoreTopicsListener = pOnSeeMoreTopicsListener;
        this.onHistoryListListener = onHistoryListListener;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    private class TopCellViewHolder extends RecyclerView.ViewHolder {


        private TopCellViewHolder(View itemView) {
            super(itemView);


        }

    }


    private class PlayingCellViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mTitle,tvMore;
        private PlayingCellViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            mTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
            tvMore = (TextView)itemView.findViewById(R.id.tvMore);

        }

    }



    private class HistoryCellViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mTitle,tvMore;
        private HistoryCellViewHolder(View itemView) {
            super(itemView);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            mTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
            tvMore = (TextView)itemView.findViewById(R.id.tvMore);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();

        switch (viewType) {

//            case TOP_TOPIC:
//                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_vertical_top, viewGroup, false);
//                return new TopCellViewHolder(v1);
//
//            case PLAYING_TOPIC:
//                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_playing_vertical, viewGroup, false);
//                return new PlayingCellViewHolder(v2);

            case HISTORY_TOPIC:
                View v3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_history_vertical, viewGroup, false);
                return new HistoryCellViewHolder(v3);

            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {


        switch (getItemViewType(position)) {

            case PLAYING_TOPIC:
                PlayingCellViewHolder playingCellViewHolder = (PlayingCellViewHolder) viewHolder;
                playingCellViewHolder.mRecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext);
                layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                playingCellViewHolder.mRecyclerView.setLayoutManager(layoutManager1);
                HomePlayingRecyclerAdapter adapter1 = new HomePlayingRecyclerAdapter(mList.get(0).getTopics());
                adapter1.SetOnItemClickListener(mItemClickListener);
                playingCellViewHolder.mRecyclerView.setAdapter(adapter1);

                break;

            case HISTORY_TOPIC:
                HistoryCellViewHolder cellViewHolder = (HistoryCellViewHolder) viewHolder;
                cellViewHolder.mRecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                cellViewHolder.mRecyclerView.setLayoutManager(layoutManager);
                HomeHistoryRecyclerAdapter adapter = new HomeHistoryRecyclerAdapter(mList.get(1).getTopics());
                adapter.setOnHistoryListListener(onHistoryListListener);
                cellViewHolder.mRecyclerView.setAdapter(adapter);

                cellViewHolder.tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onSeeMoreTopicsListener.onSeeMoreTopics(1);
                    }
                });

                break;
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
//        final int position = viewHolder.getAdapterPosition();
//        if (position == 3){
//            HistoryCellViewHolder cellViewHolder = (HistoryCellViewHolder) viewHolder;
//            LinearLayoutManager layoutManager = ((LinearLayoutManager) cellViewHolder.mRecyclerView.getLayoutManager());
//            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
//            listPosition.put(position, firstVisiblePosition);
//        }
//        super.onViewRecycled(viewHolder);
    }


    @Override
    public int getItemCount() {

        return 1;
    }

    public void SetOnItemClickListener(final HomePlayingRecyclerAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void SetOnSeeMoreTopicListener(final OnSeeMoreTopicsListener onSeeMoreTopicsListener) {
        this.onSeeMoreTopicsListener = onSeeMoreTopicsListener;
    }

    @Override
    public int getItemViewType(int position) {
        return HISTORY_TOPIC;
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

}