package com.aef.edu.aef.handlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.MainCategoryAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.ContextDataItem;

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

		List<ContextDataItem> contextDataItems = new ArrayList<>();
		ContextDataItem item = new ContextDataItem(R.drawable.children_lori_darpas_2014_img, AefConstants.CHILDREN_LORI_DARPAS_2014_DESCR,
				AefConstants.CHILDREN_LORI_DARPAS_2014_URI, R.color.colorAccent);

		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);

		recyclerView.setAdapter(new MainCategoryAdapter(getApplicationContext(), contextDataItems));
	}
}
