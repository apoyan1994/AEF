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
import com.aef.edu.aef.utils.AefUtils;
import com.aef.edu.aef.utils.ImageLoader;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

	private final int ITEM_TYPE_TEXT = -1;

	private List<TextImageItem> contentData;
	private Context context;
	private int displMinSize;
	private int screenWidth;
	private int screenHeight;

	public ContentAdapter(Context context, List<TextImageItem> contentData, int displMinSize) {
		this.context = context;
		this.contentData = contentData;
		this.displMinSize = displMinSize;
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
		if (viewType == ITEM_TYPE_TEXT) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_text_view_item, parent, false);

		} else {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ccontent_image_view_item, parent, false);
		}

		screenWidth = parent.getWidth();
		screenHeight = parent.getHeight();
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ContentAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();

		if (contentData.get(gridPos).getPhotoId() == ITEM_TYPE_TEXT) {
			holder.mTextView.setText(context.getResources().getText(contentData.get(gridPos).getItemTextId()));

		} else {
//			ImageLoader.loadImage(context, contentData.get(gridPos).getPhotoId(), holder.mImageView, displMinSize,
//					AefUtils.calculateImageHeight(context, displMinSize, contentData.get(gridPos).getPhotoId()));

			ImageLoader.loadImage(context, contentData.get(gridPos).getPhotoId(), holder.mImageView, screenWidth,
					AefUtils.calculateImageHeight(context, screenWidth, contentData.get(gridPos).getPhotoId()));


		}
	}

	@Override
	public int getItemCount() {
		return contentData.size();
	}
}
