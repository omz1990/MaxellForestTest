package com.omar.maxellforesttest.Venues;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.omar.maxellforesttest.Common.ui.BaseActivity;
import com.omar.maxellforesttest.R;
import com.omar.maxellforesttest.Venues.fragments.VenuesListFragment;

/**
 * Created by omz on 27/5/18
 */

public class SearchVenuesActivity extends BaseActivity {

    FragmentManager fragmentManager;

    // Fragments and their tags variable which we will use to check whether a previous instance of the fragment has been created
    private String TAG_VENUES_LIST = "vList";
    private VenuesListFragment venuesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = this.getSupportFragmentManager();

        loadFragment();
    }

    @Override
    public int getContentViewReference() {
        return R.layout.activity_venues_search;
    }

    private void loadFragment() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        venuesListFragment = (VenuesListFragment) fragmentManager.findFragmentByTag(TAG_VENUES_LIST);

        if (venuesListFragment == null) {
            venuesListFragment = new VenuesListFragment();
            fragmentTransaction.add(R.id.fragmentContainer, venuesListFragment, TAG_VENUES_LIST);
        } else {
            fragmentTransaction.replace(R.id.fragmentContainer, venuesListFragment, TAG_VENUES_LIST);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }
}
