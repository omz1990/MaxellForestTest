package com.omar.maxellforesttest.Venues;

import android.os.Bundle;

import com.omar.maxellforesttest.Common.ui.BaseActivity;
import com.omar.maxellforesttest.R;

/**
 * Created by omz on 27/5/18
 */

public class SearchVenuesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewReference() {
        return R.layout.activity_venues_search;
    }
}
