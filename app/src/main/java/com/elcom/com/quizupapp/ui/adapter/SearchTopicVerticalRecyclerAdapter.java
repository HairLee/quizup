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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.CaterogySearch;
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreTopicsListener;

import java.util.List;


/**
 * Created Hailpt on 3/21/17.
 */
public class SearchTopicVerticalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements Filterable {

    private List<CaterogySearch> mList;
    private SparseIntArray listPosition = new SparseIntArray();
    private SearchHorizontalRecyclerAdapter.OnItemClickListener mItemClickListener;
    private Context mContext;
    private OnSeeMoreTopicsListener onSeeMoreTopicsListener;
    private static final int FAVOURITE_TOPIC = 1;
    public SearchTopicVerticalRecyclerAdapter(List<CaterogySearch> list, SearchHorizontalRecyclerAdapter.OnItemClickListener pOnSeeMoreTopicsListener) {
        this.mList = list;
        mItemClickListener = pOnSeeMoreTopicsListener;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mTitle;
        private RelativeLayout rlTitle;
        private CellViewHolder(View itemView) {
            super(itemView);

            mRecyclerView = itemView.findViewById(R.id.recyclerView);
            mTitle = itemView.findViewById(R.id.txt_topic_title);
            rlTitle = itemView.findViewById(R.id.rlTitle);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();

        switch (viewType) {

            case FAVOURITE_TOPIC:
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_topic_item, viewGroup, false);
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
                if (mList.get(position).getName().equals("")){
                    cellViewHolder.rlTitle.setVisibility(View.GONE);
                } else {
                    cellViewHolder.mTitle.setText(mList.get(position).getName());
                }
                cellViewHolder.mRecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                cellViewHolder.mRecyclerView.setLayoutManager(layoutManager);
                SearchHorizontalRecyclerAdapter adapter = new SearchHorizontalRecyclerAdapter(mList.get(position).getList());
                cellViewHolder.mRecyclerView.setAdapter(adapter);
                adapter.SetOnItemClickListener(mItemClickListener);
                break;
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
//        final int position = viewHolder.getAdapterPosition();
//        if (position == 1 || position == 2){
//            CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
//            LinearLayoutManager layoutManager = ((LinearLayoutManager) cellViewHolder.mRecyclerView.getLayoutManager());
//            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
//            listPosition.put(position, firstVisiblePosition);
//        }
        super.onViewRecycled(viewHolder);
    }


    @Override
    public int getItemCount() {
        if (mList == null){
            return 0;
        }
        return mList.size();
    }

    public void SetOnItemClickListener(final SearchHorizontalRecyclerAdapter.OnItemClickListener mItemClickListener) {
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


}