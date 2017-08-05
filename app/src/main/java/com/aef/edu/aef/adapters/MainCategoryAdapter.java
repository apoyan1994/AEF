package com.aef.edu.aef.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.handlers.AppContextHandler;
import com.aef.edu.aef.handlers.SubCategoryChooser;
import com.aef.edu.aef.interfaces.OnAnimationEndListener;
import com.aef.edu.aef.interfaces.OnAnimationStartListener;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.utils.AnimationUtil;
import com.aef.edu.aef.utils.ImageLoader;
import com.aef.edu.aef.view.CategoryChooserViewSlide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hovo on 13/10/2016.
 */

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> implements OnCategorySelectedListener, OnAnimationStartListener {

	private List<ContentDataItem> contentData;
	private Activity activity;

	private boolean subCategoryOpened = false;
	private boolean animationStarted = false;

	public MainCategoryAdapter(Activity activity, List<ContentDataItem> contentData) {
		this.activity = activity;
		this.contentData = contentData;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		TextView mTextView;
		ImageView imageViewFront;
		ImageView imageViewBack;
		CategoryChooserViewSlide categoryChooserViewSlide;
		List<Integer> bitmapIds;

		ViewHolder(View v) {
			super(v);
			imageViewFront = (ImageView) v.findViewById(R.id.animated_view_front);
			imageViewBack = (ImageView) v.findViewById(R.id.animated_view_back);
			categoryChooserViewSlide = (CategoryChooserViewSlide) v.findViewById(R.id.category_chooser_view);
			bitmapIds = new ArrayList<>();
		}
	}

	@Override
	public MainCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_chooser_slider_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MainCategoryAdapter.ViewHolder holder, final int position) {
		final int itemPos = holder.getAdapterPosition();
		//holder.itemText.setText();
		//ImageLoader.loadImage(activity, R.drawable.large_arrow_left_right, );

		/**
		 *commented for height and width
		 *then will manage height and width for support other screen sizes
		 */
//		Bitmap firstLoadedBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(activity.getResources(),
//				AppContextHandler.getCurrentCategorisBitmaps(AppContextHandler.getCurrentCategoryNameByPos(itemPos)).get(0)), activity.getResources().getInteger(R.integer.chooser_item_width),
//				activity.getResources().getInteger(R.integer.chooser_item_height), false);

		ImageLoader.loadImage(activity, AppContextHandler.getCurrentCategorisBitmaps(AppContextHandler.getCurrentCategoryNameByPos(itemPos)).get(0), holder.imageViewFront, 300, 300);
//		ImageLoader.loadImage(activity, AppContextHandler.getCurrentCategorisBitmaps(AppContextHandler.getCurrentCategoryNameByPos(itemPos)).get(0), holder.imageViewFront, 300, 300, new OnBitmapLoadListener() {
//			@Override
//			public void onBitmapLoaded(Bitmap bitmap, ImageView imageView) {
//				imageView.setImageBitmap(bitmap);
//			}
//		});


		//holder.imageViewFront.setImageBitmap(firstLoadedBitmap);
		holder.categoryChooserViewSlide.setItemText(contentData.get(itemPos).getItemText());
		holder.categoryChooserViewSlide.setAnimatedViews(holder.imageViewFront, holder.imageViewBack);
		holder.bitmapIds = AppContextHandler.getCurrentCategorisBitmaps(AppContextHandler.getCurrentCategoryNameByPos(itemPos));
		holder.categoryChooserViewSlide.setOnCategorySelectedListener(this, this, itemPos, holder.bitmapIds);
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

	@Override
	public void onAnimationStartListener(View animatedView, int startPos, int endPos) {
		System.out.println("Apoyanner animation started");
		startSlideImageAnimation(animatedView, startPos, endPos);
	}

	private void openSubCategory(int gridPos) {
		Intent sendIntent = new Intent(activity, SubCategoryChooser.class);
		sendIntent.putExtra(AefConstants.KEY_MAIN_CATEGORY_NICK_NAME, contentData.get(gridPos).getMainNickName());
		activity.startActivityForResult(sendIntent, 1);
	}

	public void setSubCategoryOpened(boolean subCategoryOpened) {
		this.subCategoryOpened = subCategoryOpened;
	}

	private void startSlideImageAnimation(View animatedView, int startPos, int endPos) {
		animationStarted = true;
		AnimationUtil.translateAnimation(animatedView, startPos, endPos, new OnAnimationEndListener() {
			@Override
			public void onAnimationEnded() {
				animationStarted = false;
			}
		});
	}
}
