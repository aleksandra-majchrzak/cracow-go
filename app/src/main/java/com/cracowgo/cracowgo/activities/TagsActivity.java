package com.cracowgo.cracowgo.activities;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.server.CracowGoService;
import com.cracowgo.cracowgo.server.entities.Tag;
import com.cracowgo.cracowgo.server.listeners.GetLocationsForTagSubscriberListener;
import com.cracowgo.cracowgo.server.listeners.GetTagsSubscriberListener;
import com.cracowgo.cracowgo.utils.Constants;
import com.cracowgo.cracowgo.utils.HeadersGenerator;
import com.cracowgo.cracowgo.utils.LocationHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagsActivity extends AppCompatActivity implements GetTagsSubscriberListener, LocationListener, GetLocationsForTagSubscriberListener {


    @BindView(R.id.tags_listView)
    ListView tagsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        ButterKnife.bind(this);

        CracowGoService.getInstance().getTags(HeadersGenerator.getHeaders(this), this);
    }

    @Override
    public void onGetTagsCompleted(Tag[] tags) {

        final ArrayAdapter<Tag> adapter = new ArrayAdapter<Tag>(this, R.layout.tag_row, R.id.tag_name_textView, tags);
        tagsListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tagsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag tag = adapter.getItem(position);

                Location myLocation = LocationHelper.getLocation(TagsActivity.this, TagsActivity.this);

                if (myLocation == null) {
                    Snackbar.make(findViewById(R.id.activity_tags), "Cannot access location. Turn Localization on.", Snackbar.LENGTH_SHORT).show();
                } else {
                    CracowGoService.getInstance().getLocationsForTag(
                            HeadersGenerator.getHeaders(TagsActivity.this),
                            tag.getId(),
                            myLocation.getLatitude(),//,50.061800
                            myLocation.getLongitude(),//,19.936000
                            TagsActivity.this);
                }
            }
        });
    }

    @Override
    public void onGetTagsError() {
        Snackbar.make(findViewById(R.id.activity_tags), "Tags loading error", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onGetLocationsForTagCompleted(com.cracowgo.cracowgo.server.entities.Location[]
                                                      locations) {

        Bundle locationsBundle = new Bundle();
        locationsBundle.putParcelableArray(Constants.locations, locations);

        Intent intent = new Intent(TagsActivity.this, MainActivity.class);
        intent.putExtra(Constants.locations, locationsBundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onGetLocationsError() {

    }

    @Override
    public void onConnectionError() {
        Snackbar.make(findViewById(R.id.activity_tags), R.string.connection_offline, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUnknownError() {
        Snackbar.make(findViewById(R.id.activity_tags), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

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
