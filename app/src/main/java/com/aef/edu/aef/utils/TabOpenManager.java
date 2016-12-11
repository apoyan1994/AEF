package com.aef.edu.aef.utils;

import android.app.Activity;

import com.aef.edu.aef.R;
import com.aef.edu.aef.constants.AefConstants;

/**
 * Created by agvan on 12/6/16.
 */

public class TabOpenManager {

	public static void openNewTab(Activity activity, int tab) {
		int key;

		switch (tab) {
			case R.id.home:
				key = AefConstants.KEY_MENU_ITEM_HOME;
				break;

			case R.id.about_us:
				key = AefConstants.KEY_MENU_ITEM_ABOUT_US;
				break;

			case R.id.news:
				key = AefConstants.KEY_MENU_ITEM_NEWS;
				break;

			//case R.id.calendar_of_activities:
			//	openNewTab(AefConstants.KEY_MENU_ITEM_CALENDAR_OF_ACTIVITIES);
			//	break;

			//case R.id.projects:
			//	openNewTab(AefConstants.KEY_MENU_ITEM_PROJECTS);
			//	break;

			case R.id.contact_us:
				key = AefConstants.KEY_MENU_ITEM_CONTACT_US;
				break;
			default:
				key = AefConstants.KEY_MENU_ITEM_HOME;

		}

		activity.getIntent().putExtra(AefConstants.KEY_MENU_ITEM, key);
		activity.setResult(Activity.RESULT_OK, activity.getIntent());
		activity.finish();
	}
}
