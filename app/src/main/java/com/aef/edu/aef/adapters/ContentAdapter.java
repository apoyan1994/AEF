package com.aef.edu.aef.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.R;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

	private List<ContentDataItem> contentData;
	private Context context;

	public ContentAdapter(Context context, List<ContentDataItem> contentData) {
		this.context = context;
		this.contentData = contentData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView mTextView;
		ImageView mImageView;

		LinearLayout mContent;

		ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.content_item_text);
			mImageView = (ImageView) v.findViewById(R.id.content_item_icon);
			mContent = (LinearLayout) v.findViewById(R.id.content_item_container);
		}
	}

	@Override
	public int getItemViewType(int position) {
		contentData.get(position).getType();
		return super.getItemViewType(position);
	}

	@Override
	public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		if (viewType == AefConstants.TYPE_IMAGE) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_item, parent, false);

		} else {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view_item, parent, false);
		}
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ContentAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();

		if (contentData.get(gridPos).getType() == AefConstants.TYPE_IMAGE) {
			holder.mImageView.setImageResource(contentData.get(gridPos).getPhotoId());

		} else {
			holder.mTextView.setText(contentData.get(gridPos).getText());
		}
	}

	@Override
	public int getItemCount() {
		return contentData.size();
	}
}
