package com.omar.maxellforesttest.Venues.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar.maxellforesttest.Common.ui.BaseFragment;
import com.omar.maxellforesttest.R;

/**
 * Created by omz on 27/5/18
 */
public class VenuesListFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Keep data saved while screen orientation changes
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venues_list, container, false);

        return view;
    }
}
