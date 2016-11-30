package com.aef.edu.aef.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aef.edu.aef.content_activities.AefContextMoreDetails;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.R;
import com.aef.edu.aef.utils.NetworkUtils;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

	private List<ContextDataItem> mData;
	private Context context;

	public ItemsAdapter(Context context, List<ContextDataItem> mData) {
		this.context = context;
		this.mData = mData;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView mTextView;
		public ImageView mImageView;
		public ImageView fromWebImageView;
		public LinearLayout mContent;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.content_item_text);
			mImageView = (ImageView) v.findViewById(R.id.content_item_icon);
			fromWebImageView = (ImageView) v.findViewById(R.id.content_item_from_web);
			mContent = (LinearLayout) v.findViewById(R.id.content_item_container);
		}
	}

	@Override
	public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item_view, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ItemsAdapter.ViewHolder holder, final int position) {
		final int gridPos = holder.getAdapterPosition();
		holder.mTextView.setText(mData.get(gridPos).getText());
		holder.mImageView.setImageResource(mData.get(gridPos).getPhotoId());

		String uri = mData.get(gridPos).getUri();
		if (uri.contains("http") || uri.contains("https")) {
			holder.fromWebImageView.setVisibility(View.VISIBLE);
		}

		holder.mContent.setOnClickListener(new View.OnClickListener() {
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
