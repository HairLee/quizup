package com.elcom.com.quizupapp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.custom.AdvertisingView;

import java.util.ArrayList;


/**
 * Created by Jeffrey Liu on 3/21/16.
 */
public class VerticalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ArrayList<Integer>> mList;
    private SparseIntArray listPosition = new SparseIntArray();
    private HorizontalRecyclerAdapter.OnItemClickListener mItemClickListener;
    private Context mContext;

    public static final int ADVERTISING_AND_NEWS = 0;
    public static final int FAVOURITE_TOPIC = 1;
    public static final int PLAYED_GAME = 2;

    public VerticalRecyclerAdapter(ArrayList<ArrayList<Integer>> list) {
        this.mList = list;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mTitle;
        private CellViewHolder(View itemView) {
            super(itemView);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            mTitle = (TextView)itemView.findViewById(R.id.txt_topic_title);
        }

    }

    private class MidleViewHolder extends RecyclerView.ViewHolder {

        private AdvertisingView mAdverView;

        private MidleViewHolder(View itemView) {
            super(itemView);

            mAdverView = (AdvertisingView) itemView.findViewById(R.id.rl_adver);
        }

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();

        switch (viewType) {
            case ADVERTISING_AND_NEWS:
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_adver_item_layout, viewGroup, false);
                return new MidleViewHolder(v1);
            case FAVOURITE_TOPIC:
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_vertical, viewGroup, false);
                return new CellViewHolder(v2);
            case PLAYED_GAME:
                View v3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_vertical, viewGroup, false);
                return new CellViewHolder(v3);
            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {


        switch (getItemViewType(position)) {
            case ADVERTISING_AND_NEWS:
                MidleViewHolder textViewHolder = (MidleViewHolder) viewHolder;
               textViewHolder.mAdverView.updateData();
                break;
            case FAVOURITE_TOPIC:
                CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
                cellViewHolder.mTitle.setText("Ambition -- "+position);
                cellViewHolder.mRecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                cellViewHolder.mRecyclerView.setLayoutManager(layoutManager);
                HorizontalRecyclerAdapter adapter = new HorizontalRecyclerAdapter(mList.get(position));
                cellViewHolder.mRecyclerView.setAdapter(adapter);
                adapter.SetOnItemClickListener(mItemClickListener);


                int lastSeenFirstPosition = listPosition.get(position, 0);
//                if (lastSeenFirstPosition >= 0) {
//                    cellViewHolder.mRecyclerView.scrollToPosition(lastSeenFirstPosition);
//                }
//                setAnimation(cellViewHolder.itemView,position,lastSeenFirstPosition);

                break;
            case PLAYED_GAME:


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
        if (mList == null)
            return 0;
        return mList.size();
    }


    // for both short and long click
    public void SetOnItemClickListener(final HorizontalRecyclerAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return ADVERTISING_AND_NEWS;
            case 1:
                return FAVOURITE_TOPIC;
            case 2:
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