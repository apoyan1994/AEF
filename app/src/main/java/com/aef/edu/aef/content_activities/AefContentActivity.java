package com.aef.edu.aef.content_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.ContentAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.handlers.AppContextHandler;
import com.aef.edu.aef.items.TextImageItem;
import com.aef.edu.aef.utils.AefUtils;

import java.util.List;

public class AefContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_content);

		RecyclerView recycler = (RecyclerView) findViewById(R.id.content_items_recycler_view);
		recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

		String mainNickName = getIntent().getStringExtra(AefConstants.KEY_MAIN_CATEGORY_NICK_NAME);
		String subNickName = getIntent().getStringExtra(AefConstants.KEY_SUB_CATEGORY_NICK_NAME);
		String toolbarTitle = getIntent().getStringExtra(AefConstants.KEY_SUB_CATEGORY_TITLE);

		if (mainNickName == null || subNickName == null) {
			Log.e("AefContentActivity", "mainNickName or subNickName is null " + mainNickName + "  " + subNickName);
			finish();
			return;
		}

		((TextView) findViewById(R.id.toolbar_title)).setText(toolbarTitle);

		List<TextImageItem> contentList = AppContextHandler.getCurrentSubCategoryData(mainNickName, subNickName);
		recycler.setAdapter(new ContentAdapter(getApplicationContext(), contentList, AefUtils.getMinSize(this, 1)));
	}
}
