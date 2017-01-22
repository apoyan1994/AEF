package com.aef.edu.aef.handlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.MainCategoryAdapter;
import com.aef.edu.aef.items.ContentDataItem;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryChooser extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_category_chooser);

		init();
	}

	private void init() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_chooser_recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem("About us");
		contentDataItems.add(item);

		item = new ContentDataItem("Contacts");
		contentDataItems.add(item);

		item = new ContentDataItem("Home");
		contentDataItems.add(item);

		item = new ContentDataItem("More details");
		contentDataItems.add(item);

		recyclerView.setAdapter(new MainCategoryAdapter(this, contentDataItems));
	}
}
