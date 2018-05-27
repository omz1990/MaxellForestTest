package com.omar.maxellforesttest.Common.utils;

/**
 * Created by omz on 27/5/18
 */


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/** Util class to ask permissions */
public class PermissionHelper {

    // Manifest.permission.ACCESS_FINE_LOCATION
    private Activity activity;
    private String REQUESTED_PERMISSION;
    private int PERMISSION_CODE;

    public PermissionHelper(Activity activity, String REQUESTED_PERMISSION, int PERMISSION_CODE) {
        this.activity = activity;
        this.REQUESTED_PERMISSION = REQUESTED_PERMISSION;
        this.PERMISSION_CODE = PERMISSION_CODE;
    }

    /** Check to see we have the requested permissions for this app. */
    public boolean hasPermission() {
        return ContextCompat.checkSelfPermission(activity, REQUESTED_PERMISSION)
                == PackageManager.PERMISSION_GRANTED;
    }

    /** Check to see we have the necessary permissions for this app, and ask for them if we don't. */
    public void requestPermission() {
        ActivityCompat.requestPermissions(
                activity, new String[] { REQUESTED_PERMISSION }, PERMISSION_CODE);
    }

    /** Check to see if we need to show the rationale for this permission. */
    public boolean shouldShowRequestPermissionRationale() {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, REQUESTED_PERMISSION);
    }

    /** Launch Application Setting to grant permission. */
    public void launchPermissionSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }
}
