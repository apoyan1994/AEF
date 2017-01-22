package com.aef.edu.aef.content_activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aef.edu.aef.adapters.ContentItemsAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.constants.ConBigText;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.R;
import com.aef.edu.aef.utils.TabOpenManager;

import java.util.ArrayList;
import java.util.List;

public class AefContentNews extends AppCompatActivity {

	private RecyclerView recycler;
	private RecyclerView.Adapter itemsAdapter;
	private RecyclerView.LayoutManager itemsLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_context);

		Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAqua));
		toolbar.setTitle("News");
		setSupportActionBar(toolbar);


		initData();
	}

	private void initData() {
		itemsLayoutManager = new LinearLayoutManager(this);

		recycler = (RecyclerView) findViewById(R.id.content_items_recycler_view);
		recycler.setLayoutManager(itemsLayoutManager);

		itemsAdapter = new ContentItemsAdapter(this, createContextDataItems());
		recycler.setAdapter(itemsAdapter);
	}

	private List<ContextDataItem> createContextDataItems() {
		List<ContextDataItem> contextDataItems = new ArrayList<>();

		ContextDataItem item = new ContextDataItem(R.drawable.news_first_graduating_artsakh, AefConstants.NEWS_FIRST_GRADUATING_ARTSAKH_DESCR,
				ConBigText.NEWS_FIRST_GRADUATING_ARTSAKH_TEXT);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_holds_reception, AefConstants.AEF_HOLDS_RECEPTION_DESCR,
				AefConstants.AEF_HOLDS_RECEPTION_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_launches_a_new_cholarship, AefConstants.AEF_LAUNCHES_NEW_SCHOLARSHIP_DESCR,
				AefConstants.AEF_LAUNCHES_NEW_SCHOLARSHIP_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_65th_aniversary, AefConstants.AEF_65TH_ANNIVERSARY_DESCR,
				AefConstants.AEF_65TH_ANNIVERSARY_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_65th_aniversary, AefConstants.AEF_65TH_PROGRAM_BOOKLET_DESCR,
				AefConstants.AEF_65TH_PROGRAM_BOOKLET_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.honor_ralph_tufenkian_and_hacop_baghdassarian, AefConstants.AEF_HONOR_RALPH_TUFENKIAN_AND_HACOP_BAGHDASSARIAN_DESCR,
				AefConstants.AEF_HONOR_RALPH_TUFENKIAN_AND_HACOP_BAGHDASSARIAN_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.aef_donation_to_sarf, AefConstants.AEF_SARF_PRESS_RELEASE_DESCR,
				ConBigText.AEF_SARF_PRESS_RELEASE_TEXT);
		contextDataItems.add(item);

		return contextDataItems;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		TabOpenManager.openNewTab(this, item.getItemId());
		return super.onOptionsItemSelected(item);
	}
}
