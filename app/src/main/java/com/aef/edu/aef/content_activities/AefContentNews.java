package com.aef.edu.aef.content_activities;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aef.edu.aef.adapters.ItemsAdapter;
import com.aef.edu.aef.Constants;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.R;

import java.util.ArrayList;
import java.util.List;

public class AefContentNews extends AppCompatActivity {

	private RecyclerView recycler;
	private RecyclerView.Adapter itemsAdapter;
	private RecyclerView.LayoutManager itemsLayoutManager;
	private Toolbar toolbar;

	private List<ContextDataItem> contextDataItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_context);

		toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAqua));
		toolbar.setTitle("News");
		setSupportActionBar(toolbar);


		itemsLayoutManager = new LinearLayoutManager(this);
		contextDataItems = new ArrayList<>();

		recycler = (RecyclerView) findViewById(R.id.content_items_recycler_view);
		recycler.setLayoutManager(itemsLayoutManager);

		initData();
		itemsAdapter = new ItemsAdapter(this, contextDataItems);
		recycler.setAdapter(itemsAdapter);
	}

	private void initData() {
		ContextDataItem item = new ContextDataItem(R.drawable.news_first_graduating_artsakh, Constants.NEWS_FIRST_GRADUATING_ARTSAKH_DESCR,
				Constants.NEWS_FIRST_GRADUATING_ARTSAKH_TEXT);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_holds_reception, Constants.AEF_HOLDS_RECEPTION_DESCR,
				Constants.AEF_HOLDS_RECEPTION_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_launches_a_new_cholarship, Constants.AEF_LAUNCHES_NEW_SCHOLARSHIP_DESCR,
				Constants.AEF_LAUNCHES_NEW_SCHOLARSHIP_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_65th_aniversary, Constants.AEF_65TH_ANNIVERSARY_DESCR,
				Constants.AEF_65TH_ANNIVERSARY_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_65th_aniversary, Constants.AEF_65TH_PROGRAM_BOOKLET_DESCR,
				Constants.AEF_65TH_PROGRAM_BOOKLET_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.honor_ralph_tufenkian_and_hacop_baghdassarian, Constants.AEF_HONOR_RALPH_TUFENKIAN_AND_HACOP_BAGHDASSARIAN_DESCR,
				Constants.AEF_HONOR_RALPH_TUFENKIAN_AND_HACOP_BAGHDASSARIAN_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_donation_to_sarf, Constants.AEF_SARF_PRESS_RELEASE_DESCR,
				Constants.AEF_SARF_PRESS_RELEASE_TEXT);
		contextDataItems.add(item);
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
				//toolbar.setTitle(getResources().getString(R.string.item_about_us));
				openNewTab(Constants.KEY_MENU_ITEM_ABOUT_US);
				break;

			case R.id.news:
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
				openNewTab(Constants.KEY_MENU_ITEM_CONTACT_US);
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
