package com.omar.maxellforesttest.Common.network.domain;


import com.omar.maxellforesttest.Common.network.ServerSettings;
import com.omar.maxellforesttest.Common.network.domain.models.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by omz on 27/5/18
 */
public interface SearchVenuesService {

    @GET("venues/search?"
            +"client_id="+ ServerSettings.Domain.BASE_URL_CLIENT_ID
            +"&client_secret="+ServerSettings.Domain.BASE_URL_CLIENT_SECRET
            +"&v="+ServerSettings.Domain.BASE_URL_API_VERSION)
    Observable<SearchResponse> searchVenues(@Query("ll") String ll, @Query("query") String query, @Query("limit") int limit);

}
