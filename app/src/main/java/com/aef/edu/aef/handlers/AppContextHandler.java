package com.aef.edu.aef.handlers;

import com.aef.edu.aef.R;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.TextImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agvan on 6/4/17.
 */

public class AppContextHandler {
	public static List<TextImageItem> getCurrentSubCategoryData(String mainCategory, String SubCategory) {
		List<TextImageItem> contentList = new ArrayList<>();

		switch (mainCategory) {
			//projects
			case AefConstants.KEY_MAIN_PROJECTS:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_PROJECTS_STATIONERY:
						contentList.add(addItem(-1, R.drawable.stationery));

				}
				break;

			//about us
			case AefConstants.KEY_MAIN_ABOUT_US:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(-1, R.drawable.aef_sub_history));
						contentList.add(addItem(R.string.about_us_history, -1));
						break;

					case AefConstants.KEY_SUB_ABOUT_US_ACHIEVEMENTS:
						contentList.add(addItem(-1, R.drawable.achievements));
						contentList.add(addItem(R.string.about_us_achievement, -1));
						break;

				}
				break;

			//scholarships
			case AefConstants.KEY_MAIN_SCHOLARSHIPS:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_SCHOLARSHIPS_HAKOB_AND_HILDA:
						contentList.add(addItem(-1, R.drawable.hakob_and_hild));
						contentList.add(addItem(R.string.scholarship_hakob_hilda, -1));
						break;

					case AefConstants.KEY_SUB_SCHOLARSHIPS_GRECK_AND_JINA:
						contentList.add(addItem(-1, R.drawable.greck_and_jina));
						contentList.add(addItem(R.string.scholarship_greck_jina, -1));
						break;

					case AefConstants.KEY_SUB_SCHOLARSHIPS_JORJOYAN:
						contentList.add(addItem(-1, R.drawable.jorjoryan));
						contentList.add(addItem(R.string.scholarship_jorjoryan, -1));
						break;

					case AefConstants.KEY_SUB_SCHOLARSHIPS_SIRIAN_STUD:
						contentList.add(addItem(-1, R.drawable.sirian_stud));
						contentList.add(addItem(R.string.scholarship_sirian_stud, -1));
						break;

					case AefConstants.KEY_SUB_SCHOLARSHIPS_ART_HISTORY:
						contentList.add(addItem(R.string.scholarship_art_history, -1));
						break;

				}
				break;

			//application 2017
			case AefConstants.KEY_MAIN_APPLICATION_2017:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(R.string.item_home, -1));

				}
				break;

			//vol_work
			case AefConstants.KEY_MAIN_VOL_WORK:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(R.string.item_home, -1));

				}
				break;

			//news
			case AefConstants.KEY_MAIN_NEWS:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(R.string.item_home, -1));

				}
				break;

			//contact us
			case AefConstants.KEY_MAIN_CONTACT_US:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(R.string.item_home, -1));

				}
				break;

			//succeed_stories
			case AefConstants.KEY_MAIN_SUCCEED_STORIES:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(R.string.item_home, -1));

				}
				break;

			default:
				contentList = new ArrayList<>();
		}


		return contentList;
	}

	private static TextImageItem addItem(int itemTextId, int photoId) {
		return new TextImageItem()
				.setItemText(itemTextId)
				.setPhotoId(photoId);
	}
}
