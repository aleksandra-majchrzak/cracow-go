package com.cracowgo.cracowgo.activities;

import android.content.Intent;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.server.entities.Location;
import com.cracowgo.cracowgo.utils.Constants;
import com.cracowgo.cracowgo.utils.LocationHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback,
        LocationListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this);

        TextView userEmailTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email_textView);
        userEmailTextView.setText(getSharedPreferences(Constants.SHARED_PREFERENCES, 0).getString(Constants.userEmail, ""));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.drawer_button)
    public void onDrawerButtonClick() {
        drawer.openDrawer(Gravity.LEFT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_locations) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, LocationsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tags) {

        } else if (id == R.id.nav_profile) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        android.location.Location myLocation = LocationHelper.getLocation(this, this);
        // Add a marker in Sydney and move the camera
        if (myLocation != null) {
            LatLng currentMyLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentMyLocation).title("My current location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentMyLocation));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }

        Bundle locationsBundle = getIntent().getBundleExtra(Constants.locations);
        if (locationsBundle != null) {
            Parcelable[] locationsP = locationsBundle.getParcelableArray(Constants.locations);
            Location[] locations = new Location[locationsP.length];
            System.arraycopy(locationsP, 0, locations, 0, locationsP.length);


            for (Location location : locations) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(loc).title(location.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        }
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
}
