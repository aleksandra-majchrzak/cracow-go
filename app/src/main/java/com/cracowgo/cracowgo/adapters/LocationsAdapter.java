package com.cracowgo.cracowgo.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.server.entities.Location;
import com.cracowgo.cracowgo.server.entities.Tag;
import com.google.android.flexbox.FlexboxLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohru on 19.03.2017.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder> {

    private Location[] locations;
    Context context;

    public LocationsAdapter(Location[] locations, Context context) {
        this.locations = locations;
        this.context = context;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_row, parent, false);
        return new LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        holder.locationNameTextView.setText(locations[position].getName());

        for (Tag tag : locations[position].getTags()) {
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, 30);

            Button tagButton = new Button(context);
            tagButton.setText(tag.getName());
            tagButton.setBackgroundResource(R.drawable.tag_button_background);
            tagButton.setTextColor(context.getResources().getColor(R.color.white));
            tagButton.setTextSize(10);
            tagButton.setLayoutParams(lp);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tagButton.setStateListAnimator(null);
            }
            tagButton.setPadding(3, 3, 3, 3);

            holder.tagsFlexBox.addView(tagButton);
        }
    }

    @Override
    public int getItemCount() {
        return locations.length;
    }

    class LocationsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.location_name_textView)
        TextView locationNameTextView;

        @BindView(R.id.tags_flexBox)
        FlexboxLayout tagsFlexBox;

        public LocationsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
