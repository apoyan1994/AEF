package com.aef.edu.aef.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.content_activities.AefContentActivity;
import com.aef.edu.aef.content_activities.AefContentActivityWithMap;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.utils.ImageLoader;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

	private List<ContentDataItem> contentData;
	private Activity activity;

	public SubCategoryAdapter(Activity activity, List<ContentDataItem> contentData) {
		this.activity = activity;
		this.contentData = contentData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView itemText;
		ImageView itemImage;
		FrameLayout itemContainer;

		ViewHolder(View v) {
			super(v);
			itemText = (TextView) v.findViewById(R.id.grid_item_text);
			itemImage = (ImageView) v.findViewById(R.id.grid_item_icon);
			itemContainer = (FrameLayout) v.findViewById(R.id.grid_item_container);
		}
	}

	@Override
	public int getItemViewType(int position) {
		return contentData.get(position).getPhotoId();
	}

	@Override
	public SubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_chooser_item, parent, false);
		if (viewType == -1) {
			FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.findViewById(R.id.grid_item_text).getLayoutParams();
			layoutParams.gravity = Gravity.CENTER;
			view.findViewById(R.id.grid_item_text).setLayoutParams(layoutParams);

			((TextView) view.findViewById(R.id.grid_item_text)).setMaxLines(7);
		}
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SubCategoryAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.itemText.setText(contentData.get(gridPos).getItemText());

		if(contentData.get(position).getPhotoId() != -1) {
			ImageLoader.loadImage(activity, contentData.get(gridPos).getPhotoId(), holder.itemImage, 400, 400);
		}

		holder.itemContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent;
				if (AefConstants.KEY_SUB_CONTACT_WITH_US_MAP.equals(contentData.get(gridPos).getSubNickName())) {
					intent = new Intent(activity, AefContentActivityWithMap.class);
				} else {
					intent = new Intent(activity, AefContentActivity.class);
				}
				intent.putExtra(AefConstants.KEY_MAIN_CATEGORY_NICK_NAME, contentData.get(gridPos).getMainNickName());
				intent.putExtra(AefConstants.KEY_SUB_CATEGORY_NICK_NAME, contentData.get(gridPos).getSubNickName());
				intent.putExtra(AefConstants.KEY_SUB_CATEGORY_TITLE, contentData.get(gridPos).getItemText());
				activity.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return contentData.size();
	}
}
