package com.aef.edu.aef.content_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aef.edu.aef.R;
import com.aef.edu.aef.handlers.MapHandlers;
import com.aef.edu.aef.utils.NetworkUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class AefContentActivityWithMap extends AppCompatActivity {

	private GoogleMap map;
	private SupportMapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aef_content_with_map);


		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		initMap();

		findViewById(R.id.gotoHkh).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (map != null) {
					new MapHandlers(map).showHkhLocation(AefContentActivityWithMap.this);
				} else {
					if (!NetworkUtils.isConnected(getApplicationContext())) {
						Toast.makeText(getApplicationContext(), "Network connection is disabled", Toast.LENGTH_SHORT).show();
					}
					// TODO: 7/2/17 check amd show warning internet or gps absence
					initMap();
				}
			}
		});
	}

	private void initMap() {
		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap googleMap) {
				map = googleMap;
				if (map == null) {
					Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
