package com.aef.edu.aef.handlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.SubCategoryAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.content_activities.AefAboutUs;
import com.aef.edu.aef.items.ContentDataItem;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryChooser extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_caegory_chooser);

		int currentCategory = getIntent().getIntExtra(AefConstants.KEY_GRIDS_SELECTED_ITEM_POS, 0);

		List<ContentDataItem> contentDataItems = null;

		switch (currentCategory) {
			case AefConstants.KEY_ABOUT_US:
				openAboutUs();
				break;
			case AefConstants.KEY_NEWS:
				contentDataItems = news();
				break;
			case AefConstants.KEY_CONTACT_US:
				contentDataItems = News();
				break;
			case AefConstants.KEY_HOME:
				contentDataItems = home();
				break;
			case AefConstants.KEY_MORE_DETAILS:
				contentDataItems = home();
				break;
			default:
				contentDataItems = news();
		}

		if (contentDataItems != null) {
			RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_chooser_recycler_view);
			recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),
					getResources().getInteger(R.integer.grid_item_count)));

			recyclerView.setAdapter(new SubCategoryAdapter(this, contentDataItems));
		}
	}

	private List<ContentDataItem> news() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem(R.drawable.aef_donation_to_sarf, AefConstants.AEF_SARF_PRESS_RELEASE_DESCR,
				R.string.aef_sarf_press_release_text);
		contentDataItems.add(item);

		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);

		return contentDataItems;
	}

	private List<ContentDataItem> News() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem(R.drawable.news_first_graduating_artsakh, AefConstants.NEWS_FIRST_GRADUATING_ARTSAKH_DESCR,
				R.string.news_first_graduating_artsakh_text);
		contentDataItems.add(item);

		//item = new ContentDataItem(R.drawable.aef_holds_reception, AefConstants.AEF_HOLDS_RECEPTION_DESCR,
		//		AefConstants.AEF_HOLDS_RECEPTION_URI);
		//contentDataItems.add(item);

		//item = new ContentDataItem(R.drawable.aef_launches_a_new_scholarship, AefConstants.AEF_LAUNCHES_NEW_SCHOLARSHIP_DESCR,
		//		AefConstants.AEF_LAUNCHES_NEW_SCHOLARSHIP_URI);
		//contentDataItems.add(item);

		//item = new ContentDataItem(R.drawable.aef_65th_aniversary, AefConstants.AEF_65TH_ANNIVERSARY_DESCR,
		//		AefConstants.AEF_65TH_ANNIVERSARY_URI);
		//contentDataItems.add(item);

		//item = new ContentDataItem(R.drawable.aef_65th_aniversary, AefConstants.AEF_65TH_PROGRAM_BOOKLET_DESCR,
		//		AefConstants.AEF_65TH_PROGRAM_BOOKLET_URI);
		//contentDataItems.add(item);

		//item = new ContentDataItem(R.drawable.honor_ralph_tufenkian_and_hacop_baghdassarian, AefConstants.AEF_HONOR_RALPH_TUFENKIAN_AND_HACOP_BAGHDASSARIAN_DESCR,
		//		AefConstants.AEF_HONOR_RALPH_TUFENKIAN_AND_HACOP_BAGHDASSARIAN_URI);
		//contentDataItems.add(item);

		item = new ContentDataItem(R.drawable.aef_donation_to_sarf, AefConstants.AEF_SARF_PRESS_RELEASE_DESCR,
				R.string.aef_sarf_press_release_text);
		contentDataItems.add(item);

		return contentDataItems;
	}

	private List<ContentDataItem> home() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem(R.drawable.aef_donation_to_sarf, AefConstants.AEF_SARF_PRESS_RELEASE_DESCR,
				R.string.aef_sarf_press_release_text);
		contentDataItems.add(item);

		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);

		return contentDataItems;
	}

	private void openAboutUs() {
		startActivityForResult(new Intent(getApplicationContext(), AefAboutUs.class), 44);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		finish();
	}
}


/*maybe can helpful

private void openHome() {
		startActivityForResult(new Intent(getApplicationContext(), AefHome.class), 45);
	}



	private void openNews() {
		startActivityForResult(new Intent(getApplicationContext(), AefNews.class), 47);

	}

	private void openContactUs() {
		startActivityForResult(new Intent(getApplicationContext(), AefContactUs.class), 50);
	}

	private void openCalendarOfActivities() {
	}

	private void openProjects() {
	}

	//*****************************

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
 */