package com.omar.maxellforesttest.Common.network.managers;

import com.omar.maxellforesttest.Common.network.ServerSettings;
import com.omar.maxellforesttest.Common.network.ServiceFactory;
import com.omar.maxellforesttest.Common.network.domain.SearchVenuesService;
import com.omar.maxellforesttest.Common.network.domain.models.SearchResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by omz on 27/5/18
 */
public class SearchVenuesManager {

    protected SearchVenuesService searchVenuesService;

    public SearchVenuesManager() {
        searchVenuesService = ServiceFactory.createService(SearchVenuesService.class, ServerSettings.Domain.BASE_URL);
    }

    public Observable<SearchResponse> searchVenues(String ll, String query, int limit) {
        return searchVenuesService.searchVenues(ll, query, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
