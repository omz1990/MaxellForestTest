
package com.omar.maxellforesttest.Common.network.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeenHere {

    @SerializedName("lastCheckinExpiredAt")
    @Expose
    private int lastCheckinExpiredAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BeenHere() {
    }

    /**
     * 
     * @param lastCheckinExpiredAt
     */
    public BeenHere(int lastCheckinExpiredAt) {
        super();
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

    public int getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(int lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

}
