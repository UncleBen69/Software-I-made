package com.uncleben.cxnotifier.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class randomutils {

    public static boolean onmobiledata(final Context context) {
        //starts the program as not in
        boolean onmobiledata = false;

        String networkStatus;

        // Get connect manager
        final ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // check for wifi
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // check for mobile data
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( wifi.isAvailable() ) {
            networkStatus = "wifi";
            onmobiledata = false;
        } else if( mobile.isAvailable() ) {
            networkStatus = "mobileData";
            onmobiledata = true;
        } else {
            networkStatus = "noNetwork";
        }

        Toast.makeText(context,networkStatus, Toast.LENGTH_LONG).show();

        return onmobiledata;
    }
}
