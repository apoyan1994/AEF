package com.aef.edu.aef.handlers;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.content_activities.AefAboutUs;
import com.aef.edu.aef.content_activities.AefContactUs;
import com.aef.edu.aef.content_activities.AefContentHome;
import com.aef.edu.aef.content_activities.AefContentNews;
import com.aef.edu.aef.R;

public class AefContextHandler extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_context_handler);

		if (null == savedInstanceState) {
			openHome();
		}
	}

	private void openHome() {
		startActivityForResult(new Intent(getApplicationContext(), AefContentHome.class), 45);
	}

	private void openAboutUs() {
		startActivityForResult(new Intent(getApplicationContext(), AefAboutUs.class), 46);
	}

	private void openNews() {
		startActivityForResult(new Intent(getApplicationContext(), AefContentNews.class), 47);

	}

	private void openCalendarOfActivities() {
		startActivityForResult(new Intent(getApplicationContext(), AefContentNews.class), 48);
	}

	private void openProjects() {
		startActivityForResult(new Intent(getApplicationContext(), AefContentNews.class), 49);
	}

	private void openContactUs() {
		startActivityForResult(new Intent(getApplicationContext(), AefContactUs.class), 50);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			if (null != data) {
				int currentMenuItem = data.getIntExtra(AefConstants.KEY_MENU_ITEM, 0);

				switch (currentMenuItem) {
					case AefConstants.KEY_MENU_ITEM_HOME:
						openHome();
						break;

					case AefConstants.KEY_MENU_ITEM_ABOUT_US:
						openAboutUs();
						break;

					case AefConstants.KEY_MENU_ITEM_NEWS:
						openNews();
						break;

					case AefConstants.KEY_MENU_ITEM_CALENDAR_OF_ACTIVITIES:
						openCalendarOfActivities();
						break;

					case AefConstants.KEY_MENU_ITEM_PROJECTS:
						openProjects();
						break;

					case AefConstants.KEY_MENU_ITEM_CONTACT_US:
						openContactUs();
						break;
				}
			}

		} else {
			finish();

		}
	}
}
