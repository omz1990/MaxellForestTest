
package com.omar.maxellforesttest.Common.network.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchResponse() {
    }

    /**
     * 
     * @param response
     * @param meta
     */
    public SearchResponse(Meta meta, Response response) {
        super();
        this.meta = meta;
        this.response = response;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
