package com.aef.edu.aef;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<ContextDataItem> mData;

    public ItemsAdapter(List<ContextDataItem> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.content_item_text);
            mImageView = (ImageView) v.findViewById(R.id.content_item_icon);
        }
    }

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position).getText());
        holder.mImageView.setImageResource(mData.get(position).getPhotoId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
