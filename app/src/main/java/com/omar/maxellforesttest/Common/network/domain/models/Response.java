
package com.omar.maxellforesttest.Common.network.domain.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("venues")
    @Expose
    private List<Venue> venues = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param venues
     */
    public Response(List<Venue> venues) {
        super();
        this.venues = venues;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public List<Venue> getVenuesSortedByDistance() {
        List<Venue> list = venues;
        Collections.sort(list, new Comparator<Venue>() {
            public int compare(Venue o1, Venue o2) {
                if (o1.getLocation().getDistance() < o2.getLocation().getDistance()) return -1;
                if (o1.getLocation().getDistance() > o2.getLocation().getDistance()) return 1;
                return 0;
            }
        });
        return list;
    }

}
