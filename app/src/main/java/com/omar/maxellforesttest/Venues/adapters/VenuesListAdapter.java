package com.omar.maxellforesttest.Venues.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.omar.maxellforesttest.Common.network.domain.models.Venue;
import com.omar.maxellforesttest.R;

import java.util.List;

/**
 * Created by omz on 28/5/18
 */
public class VenuesListAdapter extends RecyclerView.Adapter<VenuesListAdapter.VenueViewHolder> {

    // The Recycler Adapter class for the recipes list recycler view
    private Context mContext;
    private List<Venue> venuesList;
    private VenuesListListener listener;

    public VenuesListAdapter(Context mContext, List<Venue> venuesList, VenuesListListener listener) {
        this.mContext = mContext;
        this.venuesList = venuesList;
        this.listener = listener;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_venue, parent, false);

        return new VenueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, final int position) {
        // Update data of all views
        holder.venueHeading.setText(venuesList.get(position).getName().trim());
        float distanceInKm = (float)venuesList.get(position).getLocation().getDistance()/1000;
        holder.venueDistance.setText(distanceInKm+" km");

        holder.venueAddress.setText(getAddressString(position));

        try {
            // Load images from received URLs
            Glide.with(mContext)
                    .load(venuesList.get(position).getCategories().get(0).getIcon().getPrefix() + "88" + venuesList.get(position).getCategories().get(0).getIcon().getSuffix())
                    .into(holder.thumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Listeners to notify the Fragment that an item has been clicked, and then send the details to the fragment
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVenueClick(venuesList.get(position));
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVenueClick(venuesList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return venuesList.size();
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView venueHeading, venueDistance, venueAddress;
        public ImageView thumbnail;

        public VenueViewHolder(View view) {
            super(view);
            // Bind all the views to variables in the class
            cardView = (CardView) view.findViewById(R.id.cardView);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            venueHeading = (TextView) view.findViewById(R.id.venueHeading);
            venueDistance = (TextView) view.findViewById(R.id.venueDistance);
            venueAddress = (TextView) view.findViewById(R.id.venueAddress);
        }
    }

    private String getAddressString(int position) {
        String addressLine1 = "";
        if (venuesList.get(position).getLocation().getAddress() != null && !venuesList.get(position).getLocation().getAddress().isEmpty()) {
            addressLine1 = addressLine1 + venuesList.get(position).getLocation().getAddress();
        }
        if (venuesList.get(position).getLocation().getCrossStreet() != null && !venuesList.get(position).getLocation().getCrossStreet().isEmpty()) {
            addressLine1 = addressLine1 + " (" + venuesList.get(position).getLocation().getCrossStreet() + ")";
        }
        if (addressLine1.length() > 0) {
            addressLine1 = addressLine1 + ",\n";
        }

        String addressLine2 = "";
        if (venuesList.get(position).getLocation().getCity() != null && !venuesList.get(position).getLocation().getCity().isEmpty()) {
            addressLine2 = addressLine2 + venuesList.get(position).getLocation().getCity() + ", ";
        }
        if (venuesList.get(position).getLocation().getState() != null && !venuesList.get(position).getLocation().getState().isEmpty()) {
            addressLine2 = addressLine2 + venuesList.get(position).getLocation().getState() + ", ";
        }
        if (venuesList.get(position).getLocation().getPostalCode() != null && !venuesList.get(position).getLocation().getPostalCode().isEmpty()) {
            addressLine2 = addressLine2 + venuesList.get(position).getLocation().getPostalCode() + ",";
        }
        if (addressLine2.length() > 0) {
            addressLine2 = addressLine2 + "\n";
        }

        String addressLine3 = "";
        if (venuesList.get(position).getLocation().getCountry() != null && !venuesList.get(position).getLocation().getCountry().isEmpty()) {
            addressLine3 = addressLine3 + venuesList.get(position).getLocation().getCountry();
        }

        return addressLine1 + addressLine2 + addressLine3;
    }

    public interface VenuesListListener {
        void onVenueClick(Venue venue);
    }
}