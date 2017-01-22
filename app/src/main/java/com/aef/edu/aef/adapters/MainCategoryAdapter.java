package com.aef.edu.aef.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.handlers.SubCategoryChooser;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.view.CategoryChooserView;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> implements OnCategorySelectedListener {

	private List<ContentDataItem> mData;
	private Activity activity;

	private Bitmap arrowBitmap;

	public MainCategoryAdapter(Activity activity, List<ContentDataItem> mData) {
		this.activity = activity;
		this.mData = mData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView mTextView;
		CategoryChooserView categoryChooserView;

		ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.category_chooser_item_text);
			categoryChooserView = (CategoryChooserView) v.findViewById(R.id.category_chooser_view);
		}
	}

	@Override
	public MainCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_chooser_item, parent, false);
		arrowBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.large_arrow_left_right), activity.getResources().getInteger(R.integer.chooser_item_width),
				activity.getResources().getInteger(R.integer.chooser_item_height), false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MainCategoryAdapter.ViewHolder holder, final int position) {
		final int itemPos = holder.getAdapterPosition();
		//holder.mTextView.setText();
		holder.categoryChooserView.setOnCategorySelectedListener(this, itemPos);
		holder.categoryChooserView.setArrowBitmap(arrowBitmap);
		holder.categoryChooserView.setItemText(mData.get(itemPos).getText());
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	@Override
	public void onCategorySelected(int position) {
		openSubCategory(position);
	}

	private void openSubCategory(int gridPos) {
		Intent sendIntent = new Intent(activity, SubCategoryChooser.class);
		sendIntent.putExtra(AefConstants.KEY_GRIDS_SELECTED_ITEM_POS, gridPos);
		activity.startActivity(sendIntent);
//		final Intent intent = new Intent(activity, AefNews.class);
//		intent.putExtra(AefMoreDetails.HOME_TITLE, mData.get(gridPos).getText());
//		activity.startActivity(intent);
	}
}
