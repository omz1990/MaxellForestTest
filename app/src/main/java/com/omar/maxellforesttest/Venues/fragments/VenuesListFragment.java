package com.omar.maxellforesttest.Venues.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.omar.maxellforesttest.Common.network.domain.models.SearchResponse;
import com.omar.maxellforesttest.Common.network.managers.SearchVenuesManager;
import com.omar.maxellforesttest.Common.ui.BaseFragment;
import com.omar.maxellforesttest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by omz on 27/5/18
 */
public class VenuesListFragment extends BaseFragment {

    @BindView(R.id.progressBar) protected ProgressBar progressBar;

    private SearchVenuesManager searchVenuesManager;
    private SearchResponse responseData;

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

        searchVenues();

        return view;
    }

    public void searchVenues() {
        // Make the API call (Retrofit and RxJava)
        searchVenuesManager.searchVenues("-33.914141,151.071808", "coffee", 10)
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
                Log.d("Search", "Venuse Response Size: "+response.getResponse().getVenues().size());
            }
        };
    }
}
