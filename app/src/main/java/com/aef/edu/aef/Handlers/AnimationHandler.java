package com.aef.edu.aef.handlers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.interfaces.OnAnimationEndListener;

/**
 * Created by Hovo on 12/10/2016.
 */

public class AnimationHandler {

	private AnimatorSet animatorSet;
	private AnimatorSet animatorSet1;

	private View lettersContainer;
	private TextView latterA;
	private TextView latterE;
	private TextView latterF;

	private ImageView imageDescr;

	private OnAnimationEndListener callBack;

	public AnimationHandler(OnAnimationEndListener callBack, ImageView imageDescr, View lettersContainer,
					 TextView latterA, TextView latterE, TextView latterF) {
		this.callBack = callBack;
		this.imageDescr = imageDescr;
		this.lettersContainer = lettersContainer;
		this.latterA = latterA;
		this.latterE = latterE;
		this.latterF = latterF;

		init();
	}

	private void init() {
		animatorSet = new AnimatorSet();
		animatorSet.playTogether(
				ObjectAnimator.ofPropertyValuesHolder(
						latterA,
						PropertyValuesHolder.ofFloat("rotation", 0, 360))
						.setDuration(1500),

				ObjectAnimator.ofPropertyValuesHolder(
						latterE,
						PropertyValuesHolder.ofFloat("rotation", 0, 360))
						.setDuration(1500),

				ObjectAnimator.ofPropertyValuesHolder(
						latterF,
						PropertyValuesHolder.ofFloat("rotation", 0, 360))
						.setDuration(1500),

				ObjectAnimator.ofPropertyValuesHolder(
						lettersContainer,
						PropertyValuesHolder.ofFloat("alpha", 0f, 1f))
						.setDuration(1000)
		);

		ObjectAnimator alpha = ObjectAnimator.ofPropertyValuesHolder(
				imageDescr,
				PropertyValuesHolder.ofFloat("alpha", 0f, 1f))
				.setDuration(2000);

		animatorSet1 = new AnimatorSet();

		animatorSet1.play(animatorSet).after(alpha);

		animatorSet1.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				latterA.postDelayed(new Runnable() {
					@Override
					public void run() {
						callBack.onAnimationEnded();
					}
				}, 1000);
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
	}

	public void startAnimation() {
		animatorSet1.start();
	}
}
