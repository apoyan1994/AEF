package com.aef.edu.aef.handlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.MenuItemsAdapter;
import com.aef.edu.aef.constants.Constants;
import com.aef.edu.aef.items.ContextDataItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemChooser extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_item_chooser);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_chooser_recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),
				getResources().getInteger(R.integer.grid_item_count)));

		List<ContextDataItem> contextDataItems = new ArrayList<>();
		ContextDataItem item = new ContextDataItem(R.drawable.children_lori_darpas_2014_img, Constants.CHILDREN_LORI_DARPAS_2014_DESCR,
				Constants.CHILDREN_LORI_DARPAS_2014_URI);

		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);
		contextDataItems.add(item);

		recyclerView.setAdapter(new MenuItemsAdapter(getApplicationContext(), contextDataItems));
	}
}
