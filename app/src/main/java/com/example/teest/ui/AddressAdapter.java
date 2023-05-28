package com.example.teest.ui;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> implements Filterable {
    private List<Address> resultList;

    public AddressAdapter(Context context) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        resultList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Address getItem(int index) {
        return resultList.get(index);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        Address address = getItem(position);

        if (address != null) {
            String simplifiedAddress = address.getAddressLine(0); // Get the first address line
            textView.setText(simplifiedAddress);
        }

        return textView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<Address> addresses = searchAddresses(constraint.toString());
                    filterResults.values = addresses;
                    filterResults.count = addresses.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    resultList = (List<Address>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    private List<Address> searchAddresses(String query) {
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addresses = geocoder.getFromLocationName(query, 3); // Maximum 10 results
            return addresses;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
