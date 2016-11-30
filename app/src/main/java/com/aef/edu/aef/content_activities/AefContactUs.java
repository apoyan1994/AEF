package com.aef.edu.aef.content_activities;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aef.edu.aef.Constants;
import com.aef.edu.aef.R;

public class AefContactUs extends AppCompatActivity {

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_contact_us);

		toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAqua));
		toolbar.setTitle("Contact us");
		setSupportActionBar(toolbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.home:
				openNewTab(Constants.KEY_MENU_ITEM_HOME);
				break;

			case R.id.about_us:
				openNewTab(Constants.KEY_MENU_ITEM_ABOUT_US);
				break;

			case R.id.news:
				openNewTab(Constants.KEY_MENU_ITEM_NEWS);
				break;

			//case R.id.calendar_of_activities:
				//toolbar.setTitle(getResources().getString(R.string.item_calendar_of_activities));
			//	openNewTab(Constants.KEY_MENU_ITEM_CALENDAR_OF_ACTIVITIES);
			//	break;

			//case R.id.projects:
				//toolbar.setTitle(getResources().getString(R.string.item_projects));
			//	openNewTab(Constants.KEY_MENU_ITEM_PROJECTS);
			//	break;

			case R.id.contact_us:
				//toolbar.setTitle(getResources().getString(R.string.item_contact_us));
				//openNewTab(Constants.KEY_MENU_ITEM_CONTACT_US);
				break;

		}
		return super.onOptionsItemSelected(item);
	}

	private void openNewTab(int tab) {
		getIntent().putExtra(Constants.KEY_MENU_ITEM, tab);
		setResult(Activity.RESULT_OK, getIntent());
		finish();
	}
}
