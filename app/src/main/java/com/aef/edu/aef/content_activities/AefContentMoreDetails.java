package com.aef.edu.aef.content_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aef.edu.aef.R;

public class AefContentMoreDetails extends AppCompatActivity {

	public static final String HOME_TITLE = "home_title";
	public static final String HOME_CONTENT = "home_content";
	public static final String HOME_IMAGE = "home_image";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_text);

		((TextView) findViewById(R.id.home_title)).setText(getIntent().getStringExtra(HOME_TITLE));
		int resId = getIntent().getIntExtra(HOME_CONTENT, -1);
		if (resId != -1) {
			((TextView) findViewById(R.id.home_contetn)).setText(getText(resId));
		}
		resId = getIntent().getIntExtra(HOME_IMAGE, -1);
		if (resId != -1) {
			((ImageView) findViewById(R.id.contest_image)).setImageResource(resId);
		}
	}
}
