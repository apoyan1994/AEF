package com.aef.edu.aef.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aef.edu.aef.R;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.handlers.SubCategoryChooser;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.utils.book_animation_util.CurlActivity;
import com.aef.edu.aef.utils.book_animation_util.CurlView;

import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> implements OnCategorySelectedListener {

	private List<ContentDataItem> contentData;
	private Activity activity;

	private boolean subCategoryOpened = false;

	public MainCategoryAdapter(Activity activity, List<ContentDataItem> contentData) {
		this.activity = activity;
		this.contentData = contentData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		CurlView categoryChooserCurlView;
		private CurlActivity curlActivity;

		ViewHolder(View v, Context context) {
			super(v);

			// Bitmap resources.
			int[] mBitmapIds =
					{
							R.drawable.achievements,
							R.drawable.aef_holds_reception,
							R.drawable.app_icon_cool,
							R.drawable.large_arrow_left_right,
							R.drawable.necessary_documents
					};


			categoryChooserCurlView = (CurlView) v.findViewById(R.id.category_chooser_curl_view);
			curlActivity = new CurlActivity();
			curlActivity.init(context, categoryChooserCurlView, mBitmapIds);
		}
	}

	@Override
	public MainCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_chooser_curel_item, parent, false);
		return new ViewHolder(view, activity);
	}

	@Override
	public void onBindViewHolder(MainCategoryAdapter.ViewHolder holder, final int position) {
		final int itemPos = holder.getAdapterPosition();
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
