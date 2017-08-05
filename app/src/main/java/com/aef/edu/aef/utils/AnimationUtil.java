package com.aef.edu.aef.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.aef.edu.aef.interfaces.OnAnimationEndListener;

/**
 * Created by agvan on 7/10/17.
 */

public class AnimationUtil {
	public static void translateAnimation(View animatedView, int startPos, int endPos, final OnAnimationEndListener onAnimationEndListener) {
		ObjectAnimator translateX = ObjectAnimator.ofFloat(animatedView, "translationX", startPos, endPos);
		translateX.setDuration(400);
		translateX.setInterpolator(new LinearInterpolator());
		translateX.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				onAnimationEndListener.onAnimationEnded();
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		translateX.start();
	}
}