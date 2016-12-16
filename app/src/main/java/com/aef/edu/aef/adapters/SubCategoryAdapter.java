package com.aef.edu.aef.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.content_activities.AefContextMoreDetails;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.utils.AefUtils;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

	private List<ContextDataItem> mData;
	private Context context;

	public SubCategoryAdapter(Context context, List<ContextDataItem> mData) {
		this.context = context;
		this.mData = mData;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView mTextView;
		public ImageView mImageView;
		public FrameLayout mContent;

		public ViewHolder(View v) {
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
		holder.mImageView.setImageBitmap(AefUtils.getScaledBitmap(context, mData.get(gridPos).getPhotoId(), 100, 100));

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
