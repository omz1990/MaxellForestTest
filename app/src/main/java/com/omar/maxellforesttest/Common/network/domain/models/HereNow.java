
package com.omar.maxellforesttest.Common.network.domain.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HereNow {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HereNow() {
    }

    /**
     * 
     * @param summary
     * @param count
     * @param groups
     */
    public HereNow(int count, String summary, List<Object> groups) {
        super();
        this.count = count;
        this.summary = summary;
        this.groups = groups;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

}
