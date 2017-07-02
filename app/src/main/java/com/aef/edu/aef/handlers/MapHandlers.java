package com.aef.edu.aef.handlers;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


/**
 * Created by agvan on 7/1/17.
 */

public class MapHandlers {

	private MarkerOptions markerOptions;
	private GoogleMap map;

	public MapHandlers(GoogleMap map) {
		this.map = map;
	}

	public void showHkhLocation(final AppCompatActivity activity) {
		//map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

		if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		map.setMyLocationEnabled(true);
		LatLng latLng = new LatLng(40.1834, 44.5197);
		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(latLng)
				.zoom(15)
				.bearing(45)
				.tilt(20)
				.build();

		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		map.animateCamera(cameraUpdate);

		if (markerOptions == null || !markerOptions.isVisible()) {
			if (markerOptions == null) {
				markerOptions = new MarkerOptions().position(latLng).title("Հայ Կրթական հիմնարկություն");
			}

			map.addMarker(markerOptions).showInfoWindow();
		}


		// TODO: 7/2/17 handle runtime permission access
//				if (ContextCompat.checkSelfPermission(AefMainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//					map.setMyLocationEnabled(true);
//					Location location = map.getMyLocation();
//					System.out.println("Apoyan " + location.getLongitude());
//					if (ActivityCompat.shouldShowRequestPermissionRationale(AefMainActivity.this,
//							android.Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//						System.out.println("Apoyan 1");
//					} else {
//
//
//						ActivityCompat.requestPermissions(AefMainActivity.this,
//								new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//								1);
//						System.out.println("Apoyan 2");
//
//					}
//
//				}


		PolylineOptions rectOptions = new PolylineOptions()
				.add(new LatLng(37.35, -122.0))
				.add(new LatLng(137.45, -132.0))  // North of the previous point, but at the same longitude
				.add(new LatLng(167.45, -142.2))  // Same latitude, and 30km to the west
				.add(new LatLng(197.35, -152.2))  // Same longitude, and 16km to the south
				.add(new LatLng(200.35, -122.0)); // Closes the polyline.

		rectOptions.color(Color.RED);
		Polyline polyline = map.addPolyline(rectOptions);

	}

}
