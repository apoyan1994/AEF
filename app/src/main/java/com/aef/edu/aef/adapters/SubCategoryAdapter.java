package com.aef.edu.aef.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
		holder.mImageView.setImageBitmap(getScaledBitmap(mData.get(gridPos).getPhotoId()));

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

	private Bitmap getScaledBitmap(int id) {
		return lessResolution(id, 100, 100);
	}

	public Bitmap lessResolution(int id, int width, int height) {
		int reqHeight = height;
		int reqWidth = width;
		BitmapFactory.Options options = new BitmapFactory.Options();

		// First decode with inJustDecodeBounds=true to check dimensions
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), id, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeResource(context.getResources(), id, options);
	}

	private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
}
