
package com.omar.maxellforesttest.Common.network.domain.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specials {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("items")
    @Expose
    private List<Object> items = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Specials() {
    }

    /**
     * 
     * @param count
     * @param items
     */
    public Specials(int count, List<Object> items) {
        super();
        this.count = count;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

}
