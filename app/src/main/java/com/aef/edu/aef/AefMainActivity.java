package com.aef.edu.aef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.aef.edu.aef.handlers.AnimationHandler;
import com.aef.edu.aef.handlers.MainCategoryChooser;
import com.aef.edu.aef.interfaces.OnAnimationEndListener;

public class AefMainActivity extends AppCompatActivity implements OnAnimationEndListener {

	private final int AEF_REQUEST_CODE = 45;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_main);

		if (null == savedInstanceState) {
			new AnimationHandler(this, (ImageView) findViewById(R.id.aef_description), findViewById(R.id.letters_container));
		}
	}

	@Override
	public void onBackPressed() {
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
		//startActivityForResult(new Intent(getApplicationContext(), AefContextHandler.class), AEF_REQUEST_CODE);
		//startActivityForResult(new Intent(getApplicationContext(), SubCategoryChooser.class), AEF_REQUEST_CODE);
		startActivityForResult(new Intent(getApplicationContext(), MainCategoryChooser.class), AEF_REQUEST_CODE);
	}
}
