package com.aef.edu.aef.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.aef.edu.aef.R;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;

/**
 * Created by agvan on 12/10/16.
 */

public class CategoryChooserView extends View {

	private Context context;

	private final int KEY_ITEM_HEIGHT = 80;
	private final int KEY_ITEM_WIDTH = 240;
	private final int KEY_ITEM_HALF_WIDTH = KEY_ITEM_WIDTH / 2;

	private Paint paint;
	private RectF rectf;

	private OnCategorySelectedListener onCategorySelectedListener;

	private int parentWidth;
	private int parentHeight;
	private int position;
	private float accessPointDifX1 = -1;
	private float accessPointDifX2 = -1;
	private float x1Cord = 0;
	private float x2Cord = 0;

	private boolean isCategorySelected = false;

	public CategoryChooserView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public CategoryChooserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public CategoryChooserView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}

	private void init() {
		paint = new Paint();
	}

	private void initMeasuredItems() {
		x1Cord = parentWidth / 2 - KEY_ITEM_HALF_WIDTH;
		x2Cord = parentWidth / 2 + KEY_ITEM_HALF_WIDTH;

		rectf = new RectF(x1Cord, 0, x2Cord, KEY_ITEM_HEIGHT);
		this.setMeasuredDimension(parentWidth, KEY_ITEM_HEIGHT);
		this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, KEY_ITEM_HEIGHT));
	}

	private void changePosition(float newPos, float diff1, float diff2) {
		x1Cord = newPos - diff1;
		x2Cord = newPos + diff2;

		rectf = new RectF(x1Cord, 0, x2Cord, KEY_ITEM_HEIGHT);
	}

	public void setOnCategorySelectedListener(OnCategorySelectedListener onCategorySelectedListener, int position) {
		this.onCategorySelectedListener = onCategorySelectedListener;
		this.position = position;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		initMeasuredItems();

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = MotionEventCompat.getActionMasked(event);
		float getX = event.getX();

		switch (action) {
			case (MotionEvent.ACTION_DOWN):
				if (getX > x1Cord && getX < x2Cord) {
					accessPointDifX1 = getX - x1Cord;
					accessPointDifX2 = x2Cord - getX;
				}
				break;
			case (MotionEvent.ACTION_MOVE):
				if (accessPointDifX1 == -1) {
					break;
				}

				if ((x1Cord <= 20 || x2Cord >= parentWidth - 20) && !isCategorySelected) {
					isCategorySelected = true;
					onCategorySelectedListener.onCategorySelected(position);
				}

				if (getX > x1Cord && getX < x2Cord && getX - accessPointDifX1 > 0 && getX + accessPointDifX2 < parentWidth) {
					ViewParent parent = getParent();
					if (parent != null) {
						parent.requestDisallowInterceptTouchEvent(true);
					}

					changePosition(event.getX(), accessPointDifX1, accessPointDifX2);
					invalidate();
				}
				break;
			case (MotionEvent.ACTION_UP):
				accessPointDifX1 = -1;
				accessPointDifX2 = -1;
				isCategorySelected = false;

				backStateAnimation();
				break;
			case (MotionEvent.ACTION_CANCEL):
				Log.e("Apoyan", "ACTION_CANCEL");
				backStateAnimation();
				break;
			case (MotionEvent.ACTION_OUTSIDE):
				Log.e("Apoyan", "ACTION_OUTSIDE");
				break;
			default:
				return super.onTouchEvent(event);
		}
		return true;
	}

	private void backStateAnimation() {
		ValueAnimator animation = ValueAnimator.ofFloat(x1Cord, parentWidth / 2 - KEY_ITEM_HALF_WIDTH);
		animation.setDuration(300);
		animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();
				changePosition(value, 0, 2 * KEY_ITEM_HALF_WIDTH);
				invalidate();
			}
		});
		animation.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColorFilter((new PorterDuffColorFilter(Color.parseColor("#5ed1ed"), PorterDuff.Mode.MULTIPLY)));
		//canvas.drawOval(rectf, paint);
		Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.large_arrow_left_right), KEY_ITEM_WIDTH, KEY_ITEM_HEIGHT, false);
		bitmap.getHeight();
		canvas.drawBitmap(bitmap, x1Cord, 0, paint);
		super.onDraw(canvas);
	}
}
