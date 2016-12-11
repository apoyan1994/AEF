package com.aef.edu.aef.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aef.edu.aef.R;
import com.aef.edu.aef.content_activities.AefContextMoreDetails;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.utils.NetworkUtils;
import com.aef.edu.aef.view.CategoryChooserView;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> implements OnCategorySelectedListener {

	private List<ContextDataItem> mData;
	private Context context;

	public MainCategoryAdapter(Context context, List<ContextDataItem> mData) {
		this.context = context;
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
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MainCategoryAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.mTextView.setText(mData.get(gridPos).getText());
		holder.categoryChooserView.setOnCategorySelectedListener(this, gridPos);

		holder.mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openSubCategory(gridPos);
			}
		});
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
		String url = mData.get(gridPos).getUri();
		if (null != url) {
			if (url.contains("http") || url.contains("https")) {
				if (NetworkUtils.isConnected(context)) {
					final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
					context.startActivity(intent);

				} else {
					Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
				}

			} else {
				final Intent intent = new Intent(context, AefContextMoreDetails.class);
				intent.putExtra(AefContextMoreDetails.HOME_TITLE, mData.get(gridPos).getText());
				intent.putExtra(AefContextMoreDetails.HOME_CONTENT, mData.get(gridPos).getUri());
				intent.putExtra(AefContextMoreDetails.HOME_IMAGE, mData.get(gridPos).getPhotoId());
				context.startActivity(intent);
			}
		}
	}

}
