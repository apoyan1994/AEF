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
						break;
					case AefConstants.KEY_SUB_PROJECTS_COMPUTER_SCHOOLS:
						contentList.add(addItem(-1, R.drawable.computer_schools));
						break;
					case AefConstants.KEY_SUB_PROJECTS_SCHOOL_CONSTRUCTION:
						contentList.add(addItem(-1, R.drawable.school_construction));
						break;
					case AefConstants.KEY_SUB_PROJECTS_SCHOLARSHIP_PROG:
						contentList.add(addItem(-1, R.drawable.scholarship_prog));
						break;
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
					case AefConstants.KEY_SUB_APPLICATION_OFTEN_GIVEN_QUESTION:
						contentList.add(addItem(-1, R.drawable.achievements));
						contentList.add(addItem(R.string.application_often_questions, -1));
						break;
					case AefConstants.KEY_SUB_APPLICATION_APPLICATION_FORM:
						contentList.add(addItem(-1, R.drawable.application_form));
						contentList.add(addItem(R.string.application_form, -1));
						break;
					case AefConstants.KEY_SUB_APPLICATION_SPONSORED_BY_HKH:
						contentList.add(addItem(-1, R.drawable.application_form));
						contentList.add(addItem(R.string.sponsored_by_hkh, -1));
						break;
					case AefConstants.KEY_SUB_APPLICATION_NECESSARY_DOCUMENTS:
						contentList.add(addItem(-1, R.drawable.necessary_documents));
						contentList.add(addItem(R.string.necessary_documents, -1));
						break;
				}
				break;

			//vol_work
			case AefConstants.KEY_MAIN_VOL_WORK:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_VOL_WORK_POMEGRANATE_GRAINS:
						contentList.add(addItem(R.string.pomegranate_grains, -1));
						break;
				}
				break;

			//news
			case AefConstants.KEY_MAIN_NEWS:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_HKH_NEWS_SCHOLARSHIP_COMPETITION:
						contentList.add(addItem(-1, R.drawable.scholarship_prog));
						contentList.add(addItem(R.string.hkh_news, -1));
						break;
				}
				break;

			//contact us
			case AefConstants.KEY_MAIN_CONTACT_US:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_CONTACT_WITH_US_OUR_ADDRESS:
						contentList.add(addItem(R.string.contact_with_us, -1));
						break;
				}
				break;

			//succeed_stories
			case AefConstants.KEY_MAIN_SUCCEED_STORIES:
				switch (SubCategory) {
					case AefConstants.KEY_SUB_SUCCEED_STORIES_ADDRESS:
						contentList.add(addItem(R.string.succeed_stories, -1));
						break;
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
