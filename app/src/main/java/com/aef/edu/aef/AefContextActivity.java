package com.aef.edu.aef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AefContextActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter itemsAdapter;
    private RecyclerView.LayoutManager itemsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aef_context);

        /* Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAqua));
        setSupportActionBar(myToolbar);*/

        recycler = (RecyclerView) findViewById(R.id.content_items_recycler_view);

        itemsLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(itemsLayoutManager);

        List<ContextDataItem> contextDataItems = new ArrayList<>();

        ContextDataItem item = new ContextDataItem();
        item.setPhotoId(R.drawable.img_1);
        item.setText("img 111");
        contextDataItems.add(item);

        item = new ContextDataItem();
        item.setPhotoId(R.drawable.img_2);
        item.setText("img 222");
        contextDataItems.add(item);

        item = new ContextDataItem();
        item.setPhotoId(R.drawable.img_3);
        item.setText("img 333");
        contextDataItems.add(item);

        itemsAdapter = new ItemsAdapter(contextDataItems);
        recycler.setAdapter(itemsAdapter);
    }
}
