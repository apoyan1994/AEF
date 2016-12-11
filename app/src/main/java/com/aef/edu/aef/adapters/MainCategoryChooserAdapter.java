package com.aef.edu.aef.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aef.edu.aef.R;
import com.aef.edu.aef.content_activities.AefContextMoreDetails;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.utils.NetworkUtils;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryChooserAdapter extends RecyclerView.Adapter<MainCategoryChooserAdapter.ViewHolder> {

	private List<ContextDataItem> mData;
	private Context context;

	public MainCategoryChooserAdapter(Context context, List<ContextDataItem> mData) {
		this.context = context;
		this.mData = mData;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView mTextView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.category_chooser_item_text);
		}
	}


	@Override
	public int getItemViewType(int position) {
		return position % 2;
	}

	@Override
	public MainCategoryChooserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_chooser_item_view, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MainCategoryChooserAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.mTextView.setText(mData.get(gridPos).getText());
		if (position % 2 == 0) {
			holder.mTextView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.category_chooser_item_background_selector));
			FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.mTextView.getLayoutParams();
			holder.mTextView.setGravity(Gravity.START);
			layoutParams.gravity = Gravity.START;
			layoutParams.leftMargin = 50;

		} else {
			holder.mTextView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.category_chooser_item_background_selector_red));
			FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.mTextView.getLayoutParams();
			holder.mTextView.setGravity(Gravity.END);
			layoutParams.gravity = Gravity.END;
			layoutParams.rightMargin = 50;

		}

		holder.mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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
		});
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

}
