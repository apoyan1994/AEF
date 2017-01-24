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
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.content_activities.AefContentActivity;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.utils.AefUtils;

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
	public SubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_chooser_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SubCategoryAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.itemText.setText(contentData.get(gridPos).getText());
		holder.itemImage.setImageBitmap(AefUtils.getScaledBitmap(activity, contentData.get(gridPos).getPhotoId(), 100, 100));

		holder.itemContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(activity, AefContentActivity.class);
				intent.putExtra(AefConstants.KEY_SUB_CATEGORY_NICK_NAME, contentData.get(gridPos).getNickName());
				intent.putExtra(AefConstants.KEY_SUB_CATEGORY_NICK_POS, contentData.get(gridPos).getNickPos());
				activity.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return contentData.size();
	}
}
