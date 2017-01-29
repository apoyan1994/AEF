package com.aef.edu.aef.handlers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.MainCategoryAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.ContentDataItem;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryChooser extends AppCompatActivity {

	private MainCategoryAdapter mainCategoryAdapter;

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
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_ABOUT_US, AefConstants.KEY_MAIN_ABOUT_US_TEXT));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_NEWS, AefConstants.KEY_MAIN_NEWS_TEXT));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_CONTACT_US, AefConstants.KEY_MAIN_CONTACT_US_TEXT));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_HOME, AefConstants.KEY_MAIN_HOME_TEXT));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_MORE_DETAILS, AefConstants.KEY_MAIN_MORE_DETAILS_TEXT));

		mainCategoryAdapter = new MainCategoryAdapter(this, contentDataItems);
		recyclerView.setAdapter(mainCategoryAdapter);
	}

	private ContentDataItem addItem(String mainNickName, String itemText) {
		return new ContentDataItem()
				.setMainNickName(mainNickName)
				.setItemText(itemText);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mainCategoryAdapter != null)
			mainCategoryAdapter.setSubCategoryOpened(false);
	}
}
