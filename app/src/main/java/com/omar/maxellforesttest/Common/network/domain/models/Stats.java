
package com.omar.maxellforesttest.Common.network.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("tipCount")
    @Expose
    private int tipCount;
    @SerializedName("usersCount")
    @Expose
    private int usersCount;
    @SerializedName("checkinsCount")
    @Expose
    private int checkinsCount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stats() {
    }

    /**
     * 
     * @param checkinsCount
     * @param usersCount
     * @param tipCount
     */
    public Stats(int tipCount, int usersCount, int checkinsCount) {
        super();
        this.tipCount = tipCount;
        this.usersCount = usersCount;
        this.checkinsCount = checkinsCount;
    }

    public int getTipCount() {
        return tipCount;
    }

    public void setTipCount(int tipCount) {
        this.tipCount = tipCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(int checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

}
