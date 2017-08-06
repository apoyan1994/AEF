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
import com.aef.edu.aef.view.CategoryChooserArrowView;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryArrowAdapter extends RecyclerView.Adapter<MainCategoryArrowAdapter.ViewHolder> implements OnCategorySelectedListener {

	private List<ContentDataItem> contentData;
	private Activity activity;

	private boolean subCategoryOpened = false;

	private Bitmap arrowBitmap;

	public MainCategoryArrowAdapter(Activity activity, List<ContentDataItem> contentData) {
		this.activity = activity;
		this.contentData = contentData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView mTextView;
		CategoryChooserArrowView categoryChooserView;

		ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.category_chooser_item_text);
			categoryChooserView = (CategoryChooserArrowView) v.findViewById(R.id.category_chooser_view);
		}
	}

	@Override
	public MainCategoryArrowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_chooser_item, parent, false);
		//ImageLoader.loadImage(activity, R.drawable.large_arrow_left_right, );
		arrowBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.large_arrow_left_right), activity.getResources().getInteger(R.integer.chooser_item_width),
				activity.getResources().getInteger(R.integer.chooser_item_height), false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MainCategoryArrowAdapter.ViewHolder holder, final int position) {
		final int itemPos = holder.getAdapterPosition();
		//holder.itemText.setText();
		holder.categoryChooserView.setOnCategorySelectedListener(this, itemPos);
		holder.categoryChooserView.setArrowBitmap(arrowBitmap);
		holder.categoryChooserView.setItemText(contentData.get(itemPos).getItemText());
	}

	@Override
	public int getItemCount() {
		return contentData.size();
	}

	@Override
	public void onCategorySelected(int position) {
		if (!subCategoryOpened) {
			subCategoryOpened = true;
			openSubCategory(position);
		}
	}

	private void openSubCategory(int gridPos) {
		Intent sendIntent = new Intent(activity, SubCategoryChooser.class);
		sendIntent.putExtra(AefConstants.KEY_MAIN_CATEGORY_NICK_NAME, contentData.get(gridPos).getMainNickName());
		activity.startActivityForResult(sendIntent, 1);
	}

	public void setSubCategoryOpened(boolean subCategoryOpened) {
		this.subCategoryOpened = subCategoryOpened;
	}
}
