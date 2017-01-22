package com.aef.edu.aef.content_activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aef.edu.aef.adapters.ContentItemsAdapter;
import com.aef.edu.aef.items.ContentDataItem;
import com.aef.edu.aef.R;

import java.util.ArrayList;
import java.util.List;

public class AefNews extends AppCompatActivity {

	private RecyclerView recycler;
	private RecyclerView.Adapter itemsAdapter;
	private RecyclerView.LayoutManager itemsLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_content);

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

	private List<ContentDataItem> createContextDataItems() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

//		ContentDataItem

		return contentDataItems;
	}
}
