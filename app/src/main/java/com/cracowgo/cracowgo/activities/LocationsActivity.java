package com.cracowgo.cracowgo.activities;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.adapters.LocationsAdapter;
import com.cracowgo.cracowgo.server.CracowGoService;
import com.cracowgo.cracowgo.server.listeners.GetLocationsForTagSubscriberListener;
import com.cracowgo.cracowgo.utils.HeadersGenerator;
import com.cracowgo.cracowgo.utils.LocationHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationsActivity extends AppCompatActivity implements LocationListener, GetLocationsForTagSubscriberListener {

    @BindView(R.id.locations_recyclerView)
    RecyclerView locationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        ButterKnife.bind(this);

        Location location = LocationHelper.getLocation(this, this);

        if (location != null) {
            CracowGoService.getInstance().getAllNearbyLocations(
                    HeadersGenerator.getHeaders(this),
                    50.065475,//location.getLatitude(),
                    19.939467,//location.getLongitude(),
                    this);
        }
    }

    @OnClick(R.id.back_imageView)
    public void onBackImageViewClick() {
        onBackPressed();
    }

    @Override
    public void onLocationChanged(android.location.Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onGetLocationsForTagCompleted(com.cracowgo.cracowgo.server.entities.Location[] locations) {
        LocationsAdapter adapter = new LocationsAdapter(locations, this);
        locationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onGetLocationsError() {

    }

    @Override
    public void onConnectionError() {
        Snackbar.make(findViewById(R.id.activity_locations), R.string.connection_offline, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUnknownError() {
        Snackbar.make(findViewById(R.id.activity_locations), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }
}
