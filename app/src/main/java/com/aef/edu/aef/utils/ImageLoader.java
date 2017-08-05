package com.aef.edu.aef.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.aef.edu.aef.interfaces.OnBitmapLoadListener;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by agvan on 6/4/17.
 */

public class ImageLoader {

	public static void loadImage(Context context, int resource, ImageView imageView, int width, int height) {
		Glide.with(context)
				.load(resource)
				.apply(RequestOptions.centerCropTransform().override(width, height))
				.into(imageView);
	}

	public static void loadImage(Context context, int resource, final ImageView imageView, int width, int height, final OnBitmapLoadListener callback) {
		Glide.with(context)
				.asBitmap()
				.load(resource)
				.apply(RequestOptions.centerCropTransform().override(width, height))
				.into(new BitmapImageViewTarget(imageView) {
					@Override
					protected void setResource(Bitmap resource) {
						if (resource != null) {
							callback.onBitmapLoaded(resource, imageView);
						}
					}
				});
	}
}
