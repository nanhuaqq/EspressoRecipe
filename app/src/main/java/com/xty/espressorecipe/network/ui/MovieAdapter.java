package com.xty.espressorecipe.network.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xty.espressorecipe.R;
import com.xty.espressorecipe.network.model.Movie;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28 0028.
 */
public class MovieAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Movie.SubjectsBean> mData;

    private OnItemClickListener onItemClickListener;

    public MovieAdapter(Context mContext, List<Movie.SubjectsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public MovieAdapter(Context mContext, List<Movie.SubjectsBean> mData, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener
                            .onItemClick(parent, ((RecyclerView)parent).getChildAdapterPosition(v));
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mData.get(position).getImages().getMedium())
                .into(((ViewHolder)holder).movieAvatarView);
        ((ViewHolder)holder).movieTitleView.setText(mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieAvatarView;
        TextView movieTitleView;
        public ViewHolder(View itemView) {
            super(itemView);
            movieAvatarView = itemView.findViewById(R.id.imageView);
            movieTitleView = itemView.findViewById(R.id.textView);
        }
    }

    interface OnItemClickListener{
        void onItemClick(ViewGroup parentView, int position);
    }
}
