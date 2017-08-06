package com.aef.edu.aef.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.interfaces.OnAnimationStartListener;
import com.aef.edu.aef.interfaces.OnCategorySelectedListener;
import com.aef.edu.aef.utils.ImageLoader;

import java.util.Calendar;
import java.util.List;

/**
 * Created by agvan on 12/10/16.
 */

public class CategoryChooserViewSlide extends View {

	private static final int MAX_CLICK_DURATION = 200;
	private static final int MIN_MOVE_SIZE = 10;
	private static final int MAX_MOVE_SIZE = 30;

	private long startClickTime;

	private int KEY_ITEM_WIDTH;
	private int KEY_ITEM_HEIGHT;

	private int itemTextHeight;

	private Context context;

	private Paint textPaint;

	private OnCategorySelectedListener onCategorySelectedListener;
	private OnAnimationStartListener onAnimationStartListener;

	private ImageView imageViewFront;
	private ImageView imageViewBack;

	private List<Integer> categoryBitmap;

	private String itemText;

	private int parentWidth;
	private int parentHeight;
	private int position;
	private int currentPos = 0;
	private float x1Cord = 0;
	private float x2Cord = 0;
	private float accessPointDiff;
	private float oldX = -1;
	private float oldY = -1;

	private boolean isCategorySelected = false;
	private boolean isStartedInsideObject = false;
	private boolean animationStarted = false;
	private boolean isFrontView = true;


	public CategoryChooserViewSlide(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public CategoryChooserViewSlide(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public CategoryChooserViewSlide(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}

	private void init() {
		setWillNotDraw(false);
		KEY_ITEM_WIDTH = 300;//getResources().getInteger(R.integer.chooser_item_width);
		KEY_ITEM_HEIGHT = 300;//getResources().getInteger(R.integer.chooser_item_height);
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
		this.setLayoutParams(new FrameLayout.LayoutParams(parentWidth, KEY_ITEM_HEIGHT));
	}

	private void changePosition(float newPos) {
		x1Cord = newPos;
		x2Cord = x1Cord + KEY_ITEM_WIDTH;
	}

	public void setOnCategorySelectedListener(OnCategorySelectedListener onCategorySelectedListener, OnAnimationStartListener onAnimationStartListener, int position, List<Integer> categoryBitmaps) {
		this.onCategorySelectedListener = onCategorySelectedListener;
		this.onAnimationStartListener = onAnimationStartListener;
		this.position = position;
		this.categoryBitmap = categoryBitmaps;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public void setAnimatedViews(ImageView imageViewFront, ImageView imageViewBack) {
		this.imageViewFront = imageViewFront;
		this.imageViewBack = imageViewBack;
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

				if (xCoordinate > x1Cord && xCoordinate < x2Cord && x1Cord > 0 && x2Cord < parentWidth) {
					ViewParent parent = getParent();
					if (parent != null && Math.abs(event.getX() - oldX) > Math.abs(event.getY() - oldY)) {
						parent.requestDisallowInterceptTouchEvent(true);
					}
				}
				break;
			case (MotionEvent.ACTION_UP):
				if (animationStarted) {
					break;
				}

				long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
				if (clickDuration < MAX_CLICK_DURATION && isStartedInsideObject && Math.abs(oldX - event.getX()) < MIN_MOVE_SIZE) {
					isCategorySelected = true;

					onCategorySelectedListener.onCategorySelected(position);
				} else if (clickDuration > MAX_CLICK_DURATION && isStartedInsideObject && Math.abs(oldX - event.getX()) > MAX_MOVE_SIZE) {
					boolean isRightToLeft = oldX - event.getX() > 0;
					if (isRightToLeft) {
						if (currentPos < categoryBitmap.size() - 1) {
							currentPos++;
							ImageLoader.loadImage(context, categoryBitmap.get(currentPos), imageViewFront, 300, 300);
							ImageLoader.loadImage(context, categoryBitmap.get(currentPos - 1), imageViewBack, 300, 300);
							onAnimationStartListener.onAnimationStartListener(imageViewFront, getWidth(), 0);
						}
					} else if (currentPos > 0) {
						ImageLoader.loadImage(context, categoryBitmap.get(currentPos), imageViewFront, 300, 300);
						ImageLoader.loadImage(context, categoryBitmap.get(currentPos - 1), imageViewBack, 300, 300);
						onAnimationStartListener.onAnimationStartListener(imageViewFront, 0, getWidth());
						currentPos--;
					}
				}
				isStartedInsideObject = false;
				isCategorySelected = false;

				break;
			default:
				return super.onTouchEvent(event);
		}

		return true;
	}
}
