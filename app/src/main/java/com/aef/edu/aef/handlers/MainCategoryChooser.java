package com.aef.edu.aef.handlers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.MainCategoryAdapter;
import com.aef.edu.aef.itemss.ContextDataItem;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryChooser extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_category_chooser);

		init();
	}

	public class CustomLinearLayoutManager extends LinearLayoutManager {

		public CustomLinearLayoutManager(Context context) {
			super(context);
		}

		@Override
		public boolean canScrollVertically() {
			return true;
		}
	}

	private void init() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_chooser_recycler_view);
		recyclerView.setLayoutManager(new CustomLinearLayoutManager(getApplicationContext()));

		List<ContextDataItem> contextDataItems = new ArrayList<>();

		ContextDataItem item = new ContextDataItem("");
		contextDataItems.add(item);

		item = new ContextDataItem("");//About us");
		contextDataItems.add(item);

		item = new ContextDataItem("");//Contacts");
		contextDataItems.add(item);

		item = new ContextDataItem("");//Home");
		contextDataItems.add(item);

		item = new ContextDataItem("");//More details");
		contextDataItems.add(item);

		item = new ContextDataItem("");//news 11");
		contextDataItems.add(item);

		recyclerView.setAdapter(new MainCategoryAdapter(this, contextDataItems));
		recyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
	}
}
