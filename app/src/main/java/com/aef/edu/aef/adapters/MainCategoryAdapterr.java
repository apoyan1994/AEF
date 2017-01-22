package com.aef.edu.aef.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.content_activities.AefContentNews;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.view.CategoryChooserView;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryAdapterr extends RecyclerView.Adapter<MainCategoryAdapterr.ViewHolder> implements OnCategorySelectedListener {

	private List<ContextDataItem> mData;
	private Activity activity;

	public MainCategoryAdapterr(Activity activity, List<ContextDataItem> mData) {
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
	public MainCategoryAdapterr.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_chooser_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MainCategoryAdapterr.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.mTextView.setText(mData.get(gridPos).getText());
		holder.categoryChooserView.setOnCategorySelectedListener(this, gridPos);
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
		final Intent intent = new Intent(activity, AefContentNews.class);
		//intent.putExtra(AefContextMoreDetails.HOME_TITLE, mData.get(gridPos).getText());
		//activity.startActivity(intent);
	}
}
