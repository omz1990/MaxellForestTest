package com.omar.maxellforesttest.Venues.fragments;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.omar.maxellforesttest.Common.network.domain.models.Response;
import com.omar.maxellforesttest.Common.network.domain.models.SearchResponse;
import com.omar.maxellforesttest.Common.network.domain.models.Venue;
import com.omar.maxellforesttest.Common.network.managers.SearchVenuesManager;
import com.omar.maxellforesttest.Common.ui.BaseFragment;
import com.omar.maxellforesttest.Common.utils.PermissionHelper;
import com.omar.maxellforesttest.R;
import com.omar.maxellforesttest.Venues.adapters.VenuesListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by omz on 27/5/18
 */
public class VenuesListFragment extends BaseFragment implements VenuesListAdapter.VenuesListListener {

    @BindView(R.id.progressBar) protected ProgressBar progressBar;

    @BindView(R.id.venuesRecyclerView) protected RecyclerView venuesRecyclerView;
    private VenuesListAdapter venuesAdapter;
    private List<Venue> venuesList;

    private SearchVenuesManager searchVenuesManager;
    private SearchResponse responseData;
    private VenuesFragmentListener listener;

    @BindView(R.id.mMapView) MapView mMapView;
    private GoogleMap mGoogleMap;

    private LocationManager locationManager;
    private LocationListener locationChangeListener;

    private boolean zoomToCurrentLocation = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Keep data saved while screen orientation changes
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venues_list, container, false);
        ButterKnife.bind(this, view);

        searchVenuesManager = new SearchVenuesManager();

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); //required to make map display immediately

        venuesList = new ArrayList<>();
        venuesAdapter = new VenuesListAdapter(getActivity(), venuesList, this);
        RecyclerView.LayoutManager mLayoutManagerPopular = new GridLayoutManager(getActivity(), 1);
        venuesRecyclerView.setLayoutManager(mLayoutManagerPopular);
        venuesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        venuesRecyclerView.setAdapter(venuesAdapter);

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                try {
                    //Request location updates:
                    mGoogleMap.setMyLocationEnabled(true);
                } catch (SecurityException e) {
                    // Failed to check permissions - let it go, just dont set My Location Enabled
                }

                // Disable buttons that are covered by custom frameLayout
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);

                LatLng sydney = new LatLng(-34, 151);
                CameraPosition cameraposition = new CameraPosition.Builder()
                        .target(sydney)
                        .zoom(12)
                        .build();
            }
        });

        startLocationManager();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Initialize the listener
        if (context instanceof VenuesListFragment.VenuesFragmentListener) {
            listener = (VenuesListFragment.VenuesFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement VenuesFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        zoomToCurrentLocation = true;
        try {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 20*1000, 0, locationChangeListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void startLocationManager() {
        locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationChangeListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location l) {
                handleLocationChange(l);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) { }

            @Override
            public void onProviderEnabled(String s) { }

            @Override
            public void onProviderDisabled(String s) { }
        };
    }

    private void handleLocationChange(Location location) {

        if (zoomToCurrentLocation) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12);
            this.getMap().animateCamera(cameraUpdate);
            zoomToCurrentLocation = false;
        }
        searchVenues(location.getLatitude()+","+location.getLongitude(), "coffee", 5000);
    }

    protected GoogleMap getMap() {
        return mGoogleMap;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (locationManager != null) {
            locationManager.removeUpdates(locationChangeListener);
        }
    }

    public void searchVenues(String ll, String query, int radius) {
        // Make the API call (Retrofit and RxJava)
        searchVenuesManager.searchVenues(ll, query, radius)
                .subscribe(getSearchVenuesSubscriber());

        progressBar.setVisibility(View.VISIBLE);
    }

    private DisposableObserver<SearchResponse> getSearchVenuesSubscriber() {
        return new DisposableObserver<SearchResponse>() {
            @Override
            public void onComplete() {
                // noop
            }

            @Override
            public void onError(Throwable e) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onNext(SearchResponse response) {
                progressBar.setVisibility(View.GONE);
                responseData = response;
                updateScreenData();
            }
        };
    }

    private synchronized void updateScreenData() {
        // Update the markers on the map
        for (Venue venue : responseData.getResponse().getVenuesSortedByDistance()) {
            LatLng latLng = new LatLng(venue.getLocation().getLat(), venue.getLocation().getLng());
            this.getMap().addMarker(new MarkerOptions().position(latLng).title(venue.getName()));
        }

        // Update the data in the recycler view
        venuesList.clear();
        venuesList.addAll(responseData.getResponse().getVenuesSortedByDistance());
        venuesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onVenueClick(Venue venue) {
        listener.onVenueClick(venue);
    }

    // Listener that will notify the activity that an item was clicked in the RecyclerView
    public interface VenuesFragmentListener {
        void onVenueClick(Venue venue);
    }
}
