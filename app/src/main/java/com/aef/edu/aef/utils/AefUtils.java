package com.aef.edu.aef.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by agvan on 12/16/16.
 */

public class AefUtils {
	public static Bitmap getScaledBitmap(Context context, int resourceId, int width, int height) {
		return lessResolution(context, resourceId, width, height);
	}

	private static Bitmap lessResolution(Context context, int resourceId, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();

		// First decode with inJustDecodeBounds=true to check dimensions
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), resourceId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeResource(context.getResources(), resourceId, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

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

	public static int getMinSize(Activity activity, double scaleSize) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		return (int) (Math.min(width, height) / scaleSize);
	}

	public static int calculateImageHeight(Context context, int screenWidth, int imgWidth, int photoId) {
		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), photoId, options);

		int height = options.outHeight;
		int width = options.outWidth;

		options.inJustDecodeBounds = false;


		double scaleSize = width / ((double) imgWidth);
		return (int) (height / scaleSize);

	}

	public static int calculateImageWidth(Context context, int screenWidth, int photoId) {
		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), photoId, options);

		int width = options.outWidth;

		options.inJustDecodeBounds = false;
		int imageSize = Math.min(width, screenWidth);
		return imageSize < screenWidth / 2 ? (int) (imageSize * 1.5) : imageSize;
	}
}
