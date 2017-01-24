package com.aef.edu.aef.content_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.ContentAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.ContentDataItem;

import java.util.ArrayList;
import java.util.List;

public class AefContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_content);

		RecyclerView recycler = (RecyclerView) findViewById(R.id.content_items_recycler_view);
		recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

		List<ContentDataItem> contentList;

		int nickPos = getIntent().getIntExtra(AefConstants.KEY_SUB_CATEGORY_NICK_POS, 0);
		String nickName = getIntent().getStringExtra(AefConstants.KEY_SUB_CATEGORY_NICK_NAME);
		if(nickName == null){
			finish();
			return;
		}
		switch (nickName) {
			//news
			case "a":
				switch (nickPos){
					case 0:
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
				}
				contentList = new ArrayList<>();
				break;
			//home

				/*item = new ContentDataItem(R.drawable.aef_2015_erebuni_img, AefConstants.AEF_2015_EREBUNI_DESCR,
				AefConstants.AEF_2015_EREBUNI_URI);
		contentDataItems.add(item);

		item = new ContentDataItem(R.drawable.children_lori_darpas_2014_img, AefConstants.CHILDREN_LORI_DARPAS_2014_DESCR,
				AefConstants.CHILDREN_LORI_DARPAS_2014_URI);
		contentDataItems.add(item);

		item = new ContentDataItem(R.drawable.header_img_2, AefConstants.ARMENIAN_EDUCATIONAL_FOUNDATION_DESCR,
				R.string.armenian_educational_foundation_text, AefConstants.TYPE_IMAGE);
		contentDataItems.add(item);
*/
			case "v":
				contentList = new ArrayList<>();
				break;
			default:
				contentList = new ArrayList<>();
		}

		recycler.setAdapter(new ContentAdapter(getApplicationContext(), contentList));
	}
}
