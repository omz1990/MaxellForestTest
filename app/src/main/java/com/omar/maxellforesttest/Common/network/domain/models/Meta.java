
package com.omar.maxellforesttest.Common.network.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("requestId")
    @Expose
    private String requestId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Meta() {
    }

    /**
     * 
     * @param requestId
     * @param code
     */
    public Meta(int code, String requestId) {
        super();
        this.code = code;
        this.requestId = requestId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
