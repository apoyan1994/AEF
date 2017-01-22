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
		((TextView) findViewById(R.id.home_contetn)).setText(getIntent().getStringExtra(HOME_CONTENT));
		((ImageView) findViewById(R.id.contest_image)).setImageResource(getIntent().getIntExtra(HOME_IMAGE, 0));
	}
}
