package com.aef.edu.aef.aef;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.handlers.AnimationHandler;
import com.aef.edu.aef.interfaces.OnAnimationEndListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AefMainActivity extends AppCompatActivity implements OnAnimationEndListener {

	final String KEY_APP_STARTED = "app_started";

	private final int AEF_REQUEST_CODE = 45;
	private boolean animationStarted;
	private boolean appStarted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return;
		}
		setContentView(R.layout.activity_aef_main);

		if (savedInstanceState == null || !savedInstanceState.getBoolean(KEY_APP_STARTED)) {
			animationStarted = true;
			new AnimationHandler(this, (ImageView) findViewById(R.id.aef_description), findViewById(R.id.letters_container));
		}

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		width = height = (int)(Math.min(width, height) / 2.6);

		Glide.with(this)
				.load(R.drawable.aef_icon_inside_png)
				.apply(RequestOptions.centerCropTransform().override(width, height))
				.into((ImageView) findViewById(R.id.aef_description));
	}

	@Override
	public void onBackPressed() {
		//disable back press during animation
		if (!animationStarted) {
			super.onBackPressed();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(KEY_APP_STARTED, appStarted);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == AEF_REQUEST_CODE) {
			finish();
		}
	}

	@Override
	public void onAnimationEnded() {
		//startActivityForResult(new Intent(getApplicationContext(), MainCategoryChooser.class), AEF_REQUEST_CODE);
		appStarted = true;
		animationStarted = false;
	}
}
