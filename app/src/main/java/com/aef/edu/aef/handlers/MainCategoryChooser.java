package com.aef.edu.aef.handlers;

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
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_ABOUT_US, "About us"));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_NEWS, "News"));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_CONTACT_US, "Contact us"));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_HOME, "Home"));
		contentDataItems.add(addItem(AefConstants.KEY_MAIN_MORE_DETAILS, "More details"));

		recyclerView.setAdapter(new MainCategoryAdapter(this, contentDataItems));
	}

	private ContentDataItem addItem(String nickName, String itemText) {
		return new ContentDataItem(nickName, itemText);
	}
}
