package com.cracowgo.cracowgo.activities;

import android.content.Intent;
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
import com.cracowgo.cracowgo.server.listeners.GetTagsSubscriberListener;
import com.cracowgo.cracowgo.utils.HeadersGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagsActivity extends AppCompatActivity implements GetTagsSubscriberListener {

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

                //todo pobieranie lokalizacji przypisanych do danego taga
                Intent intent = new Intent(TagsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetTagsError() {
        Snackbar.make(findViewById(R.id.activity_tags), "Tags loading error", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionError() {
        Snackbar.make(findViewById(R.id.activity_tags), R.string.connection_offline, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUnknownError() {
        Snackbar.make(findViewById(R.id.activity_tags), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }
}
