package com.aef.edu.aef.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.content_activities.AefContentMoreDetails;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.utils.AefUtils;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

	private List<ContentDataItem> mData;
	private Activity activity;

	public SubCategoryAdapter(Activity activity, List<ContentDataItem> mData) {
		this.activity = activity;
		this.mData = mData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView mTextView;
		ImageView mImageView;
		FrameLayout mContent;

		ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.menu_item_text);
			mImageView = (ImageView) v.findViewById(R.id.menu_item_icon);
			mContent = (FrameLayout) v.findViewById(R.id.menu_item_container);
		}
	}

	@Override
	public SubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_chooser_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SubCategoryAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.mTextView.setText(mData.get(gridPos).getText());
		holder.mImageView.setImageBitmap(AefUtils.getScaledBitmap(activity, mData.get(gridPos).getPhotoId(), 100, 100));

		holder.mContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(activity, AefContentMoreDetails.class);
				intent.putExtra(AefContentMoreDetails.HOME_TITLE, mData.get(gridPos).getText());
				intent.putExtra(AefContentMoreDetails.HOME_CONTENT, mData.get(gridPos).getStringResId());
				intent.putExtra(AefContentMoreDetails.HOME_IMAGE, mData.get(gridPos).getPhotoId());
				activity.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}
}
