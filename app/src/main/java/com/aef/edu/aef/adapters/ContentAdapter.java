package com.aef.edu.aef.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.items.TextImageItem;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

	private final int TYPE_TEXT = -1;

	private List<TextImageItem> contentData;
	private Context context;

	public ContentAdapter(Context context, List<TextImageItem> contentData) {
		this.context = context;
		this.contentData = contentData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView mTextView;
		ImageView mImageView;

		ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.content_item_text);
			mImageView = (ImageView) v.findViewById(R.id.content_item_icon);
		}
	}

	@Override
	public int getItemViewType(int position) {
		return contentData.get(position).getPhotoId();
	}

	@Override
	public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		if (viewType == TYPE_TEXT) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_text_view_item, parent, false);

		} else {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ccontent_image_view_item, parent, false);
		}
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ContentAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();

		if (contentData.get(gridPos).getPhotoId() == TYPE_TEXT) {
			holder.mTextView.setText(context.getResources().getText(contentData.get(gridPos).getItemTextId()));

		} else {
			holder.mImageView.setImageResource(contentData.get(gridPos).getPhotoId());
		}
	}

	@Override
	public int getItemCount() {
		return contentData.size();
	}
}
