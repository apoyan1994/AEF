package com.aef.edu.aef.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
}
