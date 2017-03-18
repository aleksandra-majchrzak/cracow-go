package com.cracowgo.cracowgo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.server.CracowGoService;
import com.cracowgo.cracowgo.server.entities.User;
import com.cracowgo.cracowgo.server.listeners.UpdateUserSubscriberListener;
import com.cracowgo.cracowgo.utils.Constants;
import com.cracowgo.cracowgo.utils.HeadersGenerator;

import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreferencesActivity extends AppCompatActivity implements UpdateUserSubscriberListener {

    @BindView(R.id.preferences_birth_year_editText)
    EditText birthEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.preferences_go_button)
    public void onGoButtonClick() {
        String birthYear = birthEditText.getText().toString();

        if (birthYear.length() != 4) {
            Snackbar.make(findViewById(R.id.activity_preferences), R.string.wrong_year_format, Snackbar.LENGTH_SHORT).show();
            return;
        }

        User user = new User();

        SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREFERENCES, 0);
        Map<String, String> headers = HeadersGenerator.getHeaders(this);

        user.setId(prefs.getInt(Constants.userId, 0));
        user.setBirthDate(birthYear);
        user.setNationality(Locale.getDefault().getDisplayCountry());

        CracowGoService.getInstance().updateUser(user, this, headers);
    }

    @Override
    public void onUpdateUserCompleted() {
        Intent intent = new Intent(PreferencesActivity.this, TagsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onUpdateUserError() {
        Snackbar.make(findViewById(R.id.activity_preferences), "Update error", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionError() {
        Snackbar.make(findViewById(R.id.activity_preferences), R.string.connection_offline, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUnknownError() {
        Snackbar.make(findViewById(R.id.activity_preferences), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }
}
