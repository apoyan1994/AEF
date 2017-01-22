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

import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.constants.ConBigText;
import com.aef.edu.aef.items.ContextDataItem;
import com.aef.edu.aef.adapters.ContentItemsAdapter;
import com.aef.edu.aef.R;
import com.aef.edu.aef.utils.TabOpenManager;

import java.util.ArrayList;
import java.util.List;

public class AefContentHome extends AppCompatActivity {

	private RecyclerView recycler;
	private RecyclerView.Adapter itemsAdapter;
	private RecyclerView.LayoutManager itemsLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_context);

		Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAqua));
		toolbar.setTitle("Home");
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

		ContextDataItem item = new ContextDataItem(R.drawable.aef_2015_erebuni_img, AefConstants.AEF_2015_EREBUNI_DESCR,
				AefConstants.AEF_2015_EREBUNI_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.children_lori_darpas_2014_img, AefConstants.CHILDREN_LORI_DARPAS_2014_DESCR,
				AefConstants.CHILDREN_LORI_DARPAS_2014_URI);
		contextDataItems.add(item);

		item = new ContextDataItem(R.drawable.header_img_2, AefConstants.ARMENIAN_EDUCATIONAL_FOUNDATION_DESCR, ConBigText.ARMENIAN_EDUCATIONAL_FOUNDATION_TEXT);
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
