package com.elcom.com.quizupapp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy;
import com.elcom.com.quizupapp.ui.custom.AdvertisingView;
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreTopicsListener;
import com.elcom.com.quizupapp.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class VerticalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements Filterable {

    private List<Caterogy> mList;
    private SparseIntArray listPosition = new SparseIntArray();
    private HorizontalRecyclerAdapter.OnItemClickListener mItemClickListener;
    private Context mContext;
    private OnSeeMoreTopicsListener onSeeMoreTopicsListener;
    private static final int FAVOURITE_TOPIC = 1;
    public VerticalRecyclerAdapter(List<Caterogy> list, OnSeeMoreTopicsListener pOnSeeMoreTopicsListener) {
        this.mList = list;
        onSeeMoreTopicsListener = pOnSeeMoreTopicsListener;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mTitle,tvMore;
        private CellViewHolder(View itemView) {
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

            case FAVOURITE_TOPIC:
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_vertical, viewGroup, false);
                return new CellViewHolder(v2);

            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {


        switch (getItemViewType(position)) {

            case FAVOURITE_TOPIC:
                CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
                cellViewHolder.mTitle.setText(mList.get(position).getName());
                cellViewHolder.mRecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                cellViewHolder.mRecyclerView.setLayoutManager(layoutManager);
                HorizontalRecyclerAdapter adapter = new HorizontalRecyclerAdapter(mList.get(position).getTopics());
                cellViewHolder.mRecyclerView.setAdapter(adapter);
                adapter.SetOnItemClickListener(mItemClickListener);
                cellViewHolder.tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onSeeMoreTopicsListener.onSeeMoreTopics(1);
                    }
                });

                int lastSeenFirstPosition = listPosition.get(position, 0);
//                if (lastSeenFirstPosition >= 0) {
//                    cellViewHolder.mRecyclerView.scrollToPosition(lastSeenFirstPosition);
//                }
                setAnimation(cellViewHolder.itemView,position,lastSeenFirstPosition);
                break;
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        final int position = viewHolder.getAdapterPosition();
        if (position == 1 || position == 2){
            CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
            LinearLayoutManager layoutManager = ((LinearLayoutManager) cellViewHolder.mRecyclerView.getLayoutManager());
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            listPosition.put(position, firstVisiblePosition);
        }
        super.onViewRecycled(viewHolder);
    }


    @Override
    public int getItemCount() {
        if (mList == null){
            return 0;
        }
        return mList.size();
    }

    public void SetOnItemClickListener(final HorizontalRecyclerAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return FAVOURITE_TOPIC;
            default:
                return FAVOURITE_TOPIC;

        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    private void setAnimation(View viewToAnimate, int position, int lastPosition)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


}