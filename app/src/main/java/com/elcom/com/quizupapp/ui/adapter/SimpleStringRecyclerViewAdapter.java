package com.elcom.com.quizupapp.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.response.Statistical;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yogesh on 1/7/16.
 */
public class SimpleStringRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Statistical> mValues;
    private Activity mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final CircleImageView mImageView;
        TextView tvNumber,tvName,tvEp;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.avatar);
            tvNumber = (TextView)view.findViewById(R.id.tvNumber);
            tvName = (TextView)view.findViewById(R.id.tvName);
            tvEp = (TextView)view.findViewById(R.id.tvEp);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public SimpleStringRecyclerViewAdapter(Activity activity,List<Statistical> items) {
        activity.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mValues = items;
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aaaaa_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if(mValues.get(position) != null){
            holder.tvNumber.setText(position+1+"");
            holder.tvEp.setText(mValues.get(position).getPoints());
            holder.tvName.setText(mValues.get(position).getName());

            Picasso.get()
                    .load(mValues.get(position).getUrl())
                    .resize(350, 350)
                    .centerCrop()
                    .into(holder.mImageView);

        }
        }


    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

