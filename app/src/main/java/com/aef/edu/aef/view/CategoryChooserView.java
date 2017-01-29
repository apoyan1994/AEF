package com.aef.edu.aef.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.aef.edu.aef.R;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;

import java.util.Calendar;

/**
 * Created by agvan on 12/10/16.
 */

public class CategoryChooserView extends View {

	private static final int MAX_CLICK_DURATION = 200;
	private static final int MAX_MOVE_SIZE = 10;

	private long startClickTime;

	private int KEY_ITEM_WIDTH;
	private int KEY_ITEM_HEIGHT;

	private int itemTextHeight;

	private Context context;

	private Paint textPaint;
	private Bitmap bitmap;

	private OnCategorySelectedListener onCategorySelectedListener;

	private String itemText;

	private int parentWidth;
	private int parentHeight;
	private int position;
	private float x1Cord = 0;
	private float x2Cord = 0;
	private float accessPointDiff;
	private float oldX = -1;
	private float oldY = -1;

	private boolean isCategorySelected = false;
	private boolean isStartedInsideObject = false;

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
		setWillNotDraw(false);
		KEY_ITEM_WIDTH = getResources().getInteger(R.integer.chooser_item_width);
		KEY_ITEM_HEIGHT = getResources().getInteger(R.integer.chooser_item_height);
		textPaint = new Paint();
		textPaint.setColor(ContextCompat.getColor(context, R.color.aef_white));
		textPaint.setTextSize(getResources().getInteger(R.integer.chooser_item_text_size));
		setTextHeight();
		//paint.setColorFilter((new PorterDuffColorFilter(Color.parseColor("#5ed1ed"), PorterDuff.Mode.MULTIPLY)));
	}

	private void setTextHeight() {
		Rect bounds = new Rect();
		textPaint.getTextBounds("A", 0, 1, bounds);
		itemTextHeight = bounds.height();
	}

	private void initMeasuredItems() {
		x1Cord = parentWidth / 2 - KEY_ITEM_WIDTH / 2;
		x2Cord = x1Cord + KEY_ITEM_WIDTH;

		this.setMeasuredDimension(parentWidth, KEY_ITEM_HEIGHT);
		this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, KEY_ITEM_HEIGHT));
	}

	private void changePosition(float newPos) {
		x1Cord = newPos;
		x2Cord = x1Cord + KEY_ITEM_WIDTH;
	}

	public void setOnCategorySelectedListener(OnCategorySelectedListener onCategorySelectedListener, int position) {
		this.onCategorySelectedListener = onCategorySelectedListener;
		this.position = position;
	}

	public void setArrowBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		initMeasuredItems();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = MotionEventCompat.getActionMasked(event);
		float xCoordinate = event.getX();

		switch (action) {
			case (MotionEvent.ACTION_DOWN):
				startClickTime = Calendar.getInstance().getTimeInMillis();
				oldX = xCoordinate;
				oldY = event.getY();

				if (xCoordinate > x1Cord && xCoordinate < x2Cord) {
					isStartedInsideObject = true;
					accessPointDiff = xCoordinate - x1Cord;
				}
				break;
			case (MotionEvent.ACTION_MOVE):
				if (!isStartedInsideObject) {
					break;
				}

				if ((x1Cord <= 20 || x2Cord >= parentWidth - 20) && !isCategorySelected) {
					isCategorySelected = true;
					onCategorySelectedListener.onCategorySelected(position);
				}

				if (xCoordinate > x1Cord && xCoordinate < x2Cord && x1Cord > 0 && x2Cord < parentWidth) {
					ViewParent parent = getParent();
					if (parent != null && Math.abs(event.getX() - oldX) > Math.abs(event.getY() - oldY)) {
						parent.requestDisallowInterceptTouchEvent(true);
					}

					changePosition(xCoordinate - accessPointDiff);
					invalidate();
				} else if (x1Cord < 0) {
					changePosition(0);
					invalidate();
				} else if (x2Cord > parentWidth) {
					changePosition(parentWidth - KEY_ITEM_WIDTH);
					invalidate();
				}

				break;
			case (MotionEvent.ACTION_UP):
				long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
				if (clickDuration < MAX_CLICK_DURATION && isStartedInsideObject && oldX - event.getX() < MAX_MOVE_SIZE) {
					isCategorySelected = true;
					onCategorySelectedListener.onCategorySelected(position);
				}
				isStartedInsideObject = false;
				isCategorySelected = false;

				backStateAnimation();
				break;
			case (MotionEvent.ACTION_CANCEL):
				Log.e("Aef_log", "ACTION_CANCEL");
				backStateAnimation();
				break;
			case (MotionEvent.ACTION_OUTSIDE):
				Log.e("Aef_log", "ACTION_OUTSIDE");
				break;
			default:
				return super.onTouchEvent(event);
		}

		return true;
	}



	private void backStateAnimation() {
		ValueAnimator animation = ValueAnimator.ofFloat(x1Cord, parentWidth / 2 - KEY_ITEM_WIDTH / 2);
		animation.setDuration(300);
		animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				changePosition((Float) animation.getAnimatedValue());
				invalidate();
			}
		});
		animation.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (bitmap != null) {
			canvas.drawBitmap(bitmap, x1Cord, 0, null);
		}

		if (!itemText.isEmpty()) {
			canvas.drawText(itemText,
					x1Cord + KEY_ITEM_WIDTH / 2 - textPaint.measureText(itemText) / 2,
					KEY_ITEM_HEIGHT / 2 + itemTextHeight / 2,
					textPaint);
		}

		super.onDraw(canvas);
	}
}
