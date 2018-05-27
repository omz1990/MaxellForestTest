package com.omar.maxellforesttest.Venues;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.omar.maxellforesttest.Common.network.domain.models.Venue;
import com.omar.maxellforesttest.Common.ui.BaseActivity;
import com.omar.maxellforesttest.Common.ui.WebViewFragment;
import com.omar.maxellforesttest.Common.utils.PermissionHelper;
import com.omar.maxellforesttest.R;
import com.omar.maxellforesttest.Venues.fragments.VenuesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by omz on 27/5/18
 */

public class SearchVenuesActivity extends BaseActivity implements VenuesListFragment.VenuesFragmentListener {

    @BindView(R.id.errorTextContainer) protected RelativeLayout errorTextContainer;

    FragmentManager fragmentManager;

    // Fragments and their tags variable which we will use to check whether a previous instance of the fragment has been created
    private String TAG_VENUES_LIST = "vList";
    private VenuesListFragment venuesListFragment;

    private WebViewFragment webViewFragment;
    private String TAG_VENUES_WEB = "vWeb";

    private PermissionHelper locationPermissionHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        fragmentManager = this.getSupportFragmentManager();
        locationPermissionHelper = new PermissionHelper(this, Manifest.permission.ACCESS_FINE_LOCATION, 1);
        if (!locationPermissionHelper.hasPermission()) {
            locationPermissionHelper.requestPermission();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationPermissionHelper.hasPermission()) {
            errorTextContainer.setVisibility(View.GONE);
            loadFragment();
        } else {
            errorTextContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getContentViewReference() {
        return R.layout.activity_venues_search;
    }

    private void loadFragment() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        venuesListFragment = (VenuesListFragment) fragmentManager.findFragmentByTag(TAG_VENUES_LIST);
        webViewFragment = (WebViewFragment) fragmentManager.findFragmentByTag(TAG_VENUES_WEB);

        if (webViewFragment != null) {
            fragmentTransaction.replace(R.id.fragmentContainer, webViewFragment, TAG_VENUES_WEB);
        } else {
            if (venuesListFragment == null) {
                venuesListFragment = new VenuesListFragment();
                fragmentTransaction.add(R.id.fragmentContainer, venuesListFragment, TAG_VENUES_LIST);
            } else {
                fragmentTransaction.replace(R.id.fragmentContainer, venuesListFragment, TAG_VENUES_LIST);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @OnClick(R.id.grantPermission)
    protected void requestPermissionClicked() {
        if (locationPermissionHelper.shouldShowRequestPermissionRationale()) {
            locationPermissionHelper.requestPermission();
        } else {
            locationPermissionHelper.launchPermissionSettings();
        }
    }

    @Override
    public void onVenueClick(Venue venue) {
        if (webViewFragment == null) {
            webViewFragment = new WebViewFragment();
        }
        if (venue.getUrl() != null && !venue.getUrl().isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString("url", venue.getUrl());
            webViewFragment.setArguments(bundle);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            // Sliding top and bottom animation
            ft.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_top);
            ft.replace(R.id.fragmentContainer, webViewFragment, TAG_VENUES_WEB);
            ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
        } else {
            Toast.makeText(this, "This venue does not have a website", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        onResume();
    }
}
