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

import com.aef.edu.aef.Constants;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.adapters.ItemsAdapter;
import com.aef.edu.aef.R;

import java.util.ArrayList;
import java.util.List;

public class AefContentHome extends AppCompatActivity {

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
		toolbar.setTitle("Home");
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
		ContextDataItem item = new ContextDataItem(R.drawable.aef_2015_erebuni_img, Constants.AEF_2015_EREBUNI_DESCR,
				Constants.AEF_2015_EREBUNI_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.children_lori_darpas_2014_img, Constants.CHILDREN_LORI_DARPAS_2014_DESCR,
				Constants.CHILDREN_LORI_DARPAS_2014_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.header_img_2, Constants.ARMENIAN_EDUCATIONAL_FOUNDATION_DESCR, Constants.ARMENIAN_EDUCATIONAL_FOUNDATION_TEXT);
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
				break;

			case R.id.about_us:
				openNewTab(Constants.KEY_MENU_ITEM_ABOUT_US);
				break;

			case R.id.news:
				openNewTab(Constants.KEY_MENU_ITEM_NEWS);
				break;

			//case R.id.calendar_of_activities:
			//	openNewTab(Constants.KEY_MENU_ITEM_CALENDAR_OF_ACTIVITIES);
			//	break;

			//case R.id.projects:
			//	openNewTab(Constants.KEY_MENU_ITEM_PROJECTS);
			//	break;

			case R.id.contact_us:
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
