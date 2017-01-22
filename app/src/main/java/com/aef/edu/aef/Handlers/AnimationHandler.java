package com.aef.edu.aef.handlers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.interfaces.OnAnimationEndListener;

/**
 * Created by Hovo on 12/10/2016.
 */

public class AnimationHandler {

	private View lettersContainer;
	private ImageView aefImgDescription;

	private int letterAnimationDuration = 1;//1000;
	private final int appStartDuration = 1;//800;

	private OnAnimationEndListener callBack;

	public AnimationHandler(OnAnimationEndListener callBack, ImageView aefImgDescription, View lettersContainer) {
		this.callBack = callBack;
		this.aefImgDescription = aefImgDescription;
		this.lettersContainer = lettersContainer;

		createAnimator();
	}

	private void createAnimator() {
		final AnimatorSet lettersAnimatorSet = new AnimatorSet();
		lettersAnimatorSet.setInterpolator(new LinearInterpolator());
		lettersAnimatorSet.playTogether(

				ObjectAnimator.ofPropertyValuesHolder(
						lettersContainer,
						PropertyValuesHolder.ofFloat(AefConstants.ROTATION_X, -270, -360))
						.setDuration(letterAnimationDuration)

		);

		lettersAnimatorSet.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				callBack.onAnimationEnded();
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});

		aefImgDescription.postDelayed(new Runnable() {
			@Override
			public void run() {
				lettersAnimatorSet.start();
			}
		}, appStartDuration);
	}
}
