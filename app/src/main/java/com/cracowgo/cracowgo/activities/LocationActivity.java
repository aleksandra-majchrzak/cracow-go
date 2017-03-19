package com.cracowgo.cracowgo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.server.entities.Location;
import com.cracowgo.cracowgo.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.name_textView)
    TextView nameTextView;

    @BindView(R.id.description_textView)
    TextView descriptionTextView;

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        location = getIntent().getParcelableExtra(Constants.location);

        nameTextView.setText(location.getName());
        descriptionTextView.setText(location.getDescription());
    }

    @OnClick(R.id.back_imageView)
    public void onBackImageViewClick() {
        onBackPressed();
    }
}
