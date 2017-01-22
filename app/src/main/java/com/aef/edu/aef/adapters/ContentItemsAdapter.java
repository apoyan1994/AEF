package com.aef.edu.aef.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aef.edu.aef.content_activities.AefContextMoreDetails;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.R;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ContentItemsAdapter extends RecyclerView.Adapter<ContentItemsAdapter.ViewHolder> {

	private List<ContentDataItem> mData;
	private Context context;

	public ContentItemsAdapter(Context context, List<ContentDataItem> mData) {
		this.context = context;
		this.mData = mData;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView mTextView;
		public ImageView mImageView;
		;
		public LinearLayout mContent;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.content_item_text);
			mImageView = (ImageView) v.findViewById(R.id.content_item_icon);
			mContent = (LinearLayout) v.findViewById(R.id.content_item_container);
		}
	}

	@Override
	public ContentItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ContentItemsAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.mTextView.setText(mData.get(gridPos).getText());
		holder.mImageView.setImageResource(mData.get(gridPos).getPhotoId());

		holder.mContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(context, AefContextMoreDetails.class);
				intent.putExtra(AefContextMoreDetails.HOME_TITLE, mData.get(gridPos).getText());
				intent.putExtra(AefContextMoreDetails.HOME_CONTENT, mData.get(gridPos).getUri());
				intent.putExtra(AefContextMoreDetails.HOME_IMAGE, mData.get(gridPos).getPhotoId());
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}
}
