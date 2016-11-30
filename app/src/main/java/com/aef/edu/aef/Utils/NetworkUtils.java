package com.aef.edu.aef.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by agvan on 10/19/16.
 */

public class NetworkUtils {

	public static boolean isConnected(Context context) {
		if (context != null) {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm != null) {
				NetworkInfo nInfo = cm.getActiveNetworkInfo();
				return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
			}
		}

		return false;
	}

	public static boolean isInternetConnected (Context ctx) {
		ConnectivityManager connectivityMgr = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobile = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		// Check if wifi or mobile network is available or not. If any of them is
		// available or connected then it will return true, otherwise false;
		if (wifi != null) {
			if (wifi.isConnected()) {
				return true;
			}
		}
		if (mobile != null) {
			if (mobile.isConnected()) {
				return true;
			}
		}
		return false;
	}
}
