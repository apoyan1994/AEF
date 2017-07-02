package com.aef.edu.aef.utils.book_animation_util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Simple Activity for curl testing.
 *
 * @author harism
 */
public class CurlActivity {

	private CurlView mCurlView;
	private Context context;
	private int[] mBitmapIds;

	public void init(Context mContext, CurlView curlView, int[] bitmapIds) {


		int index = 0;
		this.context = mContext;
		this.mCurlView = curlView;
		this.mBitmapIds = bitmapIds;

		mCurlView.setPageProvider(new PageProvider());
		mCurlView.setSizeChangedObserver(new SizeChangedObserver());
		mCurlView.setCurrentIndex(index);
		mCurlView.setBackgroundColor(0x00000000);

		// This is something somewhat experimental. Before uncommenting next
		// line, please see method comments in CurlView.
		// mCurlView.setEnableTouchPressure(true);
	}

	public void onPause() {
		mCurlView.onPause();
	}

	public void onResume() {
		mCurlView.onResume();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {
		@Override
		public int getPageCount() {
			return mBitmapIds.length;
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			System.out.println("Apoyan index " + index);
			Drawable d = ContextCompat.getDrawable(context, mBitmapIds[index]);

			int margin = 7;
			int border = 3;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return b;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {
			switch (index) {
				// First case is image on front side, solid colored back.
				case 0: {
					System.out.println("Apoyan obama");
					Bitmap front = loadBitmap(width, height, 0);
					//Bitmap back = loadBitmap(width, height, 1);
					page.setTexture(front, CurlPage.SIDE_FRONT);
					//page.setTexture(back, CurlPage.SIDE_BACK);
					//page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
					break;
				}
				// Second case is image on back side, solid colored front.
				case 1: {
					System.out.println("Apoyan taipei_101");
					Bitmap front = loadBitmap(width, height, 2);
					//Bitmap back = loadBitmap(width, height, 3);
					page.setTexture(front, CurlPage.SIDE_FRONT);
					//page.setTexture(back, CurlPage.SIDE_BACK);
					//page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
					break;
				}
				// Third case is images on both sides.
				case 2: {
					System.out.println("Apoyan road_rage world");
					Bitmap front = loadBitmap(width, height, 4);
					//Bitmap back = loadBitmap(width, height, 0);
					page.setTexture(front, CurlPage.SIDE_FRONT);
					//page.setTexture(back, CurlPage.SIDE_BACK);
					break;
				}
				// Fourth case is images on both sides - plus they are blend against
				// separate colors.
				case 3: {
					System.out.println("Apoyan taipei_101 road_rage");
					Bitmap front = loadBitmap(width, height, 1);
					//Bitmap back = loadBitmap(width, height, 2);
					page.setTexture(front, CurlPage.SIDE_FRONT);
					//page.setTexture(back, CurlPage.SIDE_BACK);
					//page.setColor(Color.argb(127, 170, 130, 255),
					//		CurlPage.SIDE_FRONT);
					//page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
					break;
				}
				// Fifth case is same image is assigned to front and back. In this
				// scenario only one texture is used and shared for both sides.
				case 4:
					Bitmap front = loadBitmap(width, height, 3);
					//Bitmap back = loadBitmap(width, height, 4);
					page.setTexture(front, CurlPage.SIDE_FRONT);
					//page.setTexture(back, CurlPage.SIDE_BACK);
					//page.setColor(Color.argb(127, 255, 255, 255), CurlPage.SIDE_BACK);
					break;
			}
		}

	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				mCurlView.setMargins(.1f, .05f, .1f, .05f);
			} else {
				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				mCurlView.setMargins(.1f, .1f, .1f, .1f);
			}
		}
	}

}