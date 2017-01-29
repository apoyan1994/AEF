package com.aef.edu.aef.content_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.aef.edu.aef.R;
import com.aef.edu.aef.adapters.ContentAdapter;
import com.aef.edu.aef.constants.AefConstants;
import com.aef.edu.aef.items.TextImageItem;

import java.util.ArrayList;
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
			finish();
			return;
		}

		((TextView) findViewById(R.id.toolbar_title)).setText(toolbarTitle);

		List<TextImageItem> contentList = new ArrayList<>();

		switch (mainNickName) {
			//news
			case AefConstants.KEY_MAIN_ABOUT_US:
				switch (subNickName) {
					case AefConstants.KEY_SUB_ABOUT_US_ABOUT_US:
						contentList.add(addItem(R.string.about_us_text_about_us, -1));
						contentList.add(addItem(R.string.about_us_text_our_mission, -1));
						contentList.add(addItem(R.string.about_us_text_our_history, -1));
						contentList.add(addItem(R.string.about_us_text_board_of_directors, -1));
						break;
					case AefConstants.KEY_SUB_ABOUT_US_ACHIEVEMENTS:
						contentList.add(addItem(R.string.about_us_text_achievements_part_1, -1));
						contentList.add(addItem(R.string.about_us_text_achievements_part_2, -1));
						break;
					case AefConstants.KEY_SUB_ABOUT_US_MEMBERSHIP:
						contentList.add(addItem(R.string.about_us_text_membership, -1));
						break;
					case AefConstants.KEY_SUB_ABOUT_US_ENDOWMENTS:
						contentList.add(addItem(R.string.about_us_text_membership_endowments_and_donations, -1));
						contentList.add(addItem(R.string.about_us_text_membership_endowments_funds, -1));
						break;
					case AefConstants.KEY_SUB_ABOUT_US_HISTORY:
						contentList.add(addItem(R.string.about_us_text_our_history, -1));
						break;
					case AefConstants.KEY_SUB_ABOUT_US_NON_PROFIT:
						contentList.add(addItem(R.string.about_us_text_501_non_profit, -1));
						break;
					case AefConstants.KEY_SUB_ABOUT_US_ANNUAL_ALLOCATION:
						break;
				}
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

	private TextImageItem addItem(int itemTextId, int photoId) {
		return new TextImageItem()
				.setItemText(itemTextId)
				.setPhotoId(photoId);
	}
}
