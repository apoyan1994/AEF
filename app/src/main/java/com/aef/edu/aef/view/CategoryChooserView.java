package com.aef.edu.aef.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.aef.edu.aef.interfaces.OnCategorySelectedListener;

/**
 * Created by agvan on 12/10/16.
 */

public class CategoryChooserView extends View {
	private Paint paint;
	private RectF rectf;

	private OnCategorySelectedListener onCategorySelectedListener;

	private int parentWidth;
	private int parentHeight;
	private int position;

	float x1Cord = 0;
	float x2Cord = 0;

	public CategoryChooserView(Context context) {
		super(context);
		init();
	}

	public CategoryChooserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CategoryChooserView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		paint = new Paint();
	}

	private void initMeasuredItems() {
		x1Cord = parentWidth / 2 - 250;
		x2Cord = parentWidth / 2 + 250;

		rectf = new RectF(x1Cord, 0, x2Cord, 200);
		this.setMeasuredDimension(parentWidth / 2, 200);
		this.setLayoutParams(new FrameLayout.LayoutParams(parentWidth, 200));
	}

	private void changePosition(float newPos) {
		x1Cord = newPos - 250;
		x2Cord = newPos + 250;

		rectf = new RectF(x1Cord, 0, x2Cord, 200);
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
		invalidate();

		int action = MotionEventCompat.getActionMasked(event);

		switch (action) {
			case (MotionEvent.ACTION_DOWN):

				return true;
			case (MotionEvent.ACTION_MOVE):
				float getX = event.getX();
				if (getX >= parentWidth || getX <= 0) {
					onCategorySelectedListener.onCategorySelected(position);
				}

				if (getX > x1Cord && getX < x2Cord) {
					changePosition(event.getX());
				}
				return true;
			case (MotionEvent.ACTION_UP):

				return true;
			case (MotionEvent.ACTION_CANCEL):

				return true;
			case (MotionEvent.ACTION_OUTSIDE):

				return true;
			default:
				return super.onTouchEvent(event);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColor(Color.parseColor("#5ed1ed"));
		canvas.drawOval(rectf, paint);
		super.onDraw(canvas);
	}
}
