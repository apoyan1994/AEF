package com.aef.edu.aef.handlers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.SubCategoryAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.ContentDataItem;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryChooser extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_caegory_chooser);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_chooser_recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),
				getResources().getInteger(R.integer.grid_item_count)));

		int currentCategory = getIntent().getIntExtra(AefConstants.KEY_GRID_POS, 0);

		List<ContentDataItem> contentDataItems = null;

		switch (currentCategory) {
			case 0:
				contentDataItems = aboutUs();
				break;
			case 1:
				contentDataItems = bB();
				break;
			case 2:
				contentDataItems = cC();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				contentDataItems = aboutUs();
		}


		recyclerView.setAdapter(new SubCategoryAdapter(getApplicationContext(), contentDataItems));
	}

	private List<ContentDataItem> aboutUs() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem(R.drawable.children_lori_darpas_2014_img, AefConstants.CHILDREN_LORI_DARPAS_2014_DESCR,
				AefConstants.CHILDREN_LORI_DARPAS_2014_URI);

		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);

		return contentDataItems;
	}

	private List<ContentDataItem> bB() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem(R.drawable.children_lori_darpas_2014_img, AefConstants.CHILDREN_LORI_DARPAS_2014_DESCR,
				AefConstants.CHILDREN_LORI_DARPAS_2014_URI);

		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);

		return contentDataItems;
	}

	private List<ContentDataItem> cC() {
		List<ContentDataItem> contentDataItems = new ArrayList<>();

		ContentDataItem item = new ContentDataItem(R.drawable.children_lori_darpas_2014_img, AefConstants.CHILDREN_LORI_DARPAS_2014_DESCR,
				AefConstants.CHILDREN_LORI_DARPAS_2014_URI);

		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);
		contentDataItems.add(item);

		return contentDataItems;
	}
}
