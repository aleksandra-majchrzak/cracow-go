package com.cracowgo.cracowgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.cracowgo.cracowgo.R;
import com.cracowgo.cracowgo.server.CracowGoService;
import com.cracowgo.cracowgo.server.entities.RegisterResponse;
import com.cracowgo.cracowgo.server.entities.User;
import com.cracowgo.cracowgo.server.listeners.RegisterSubscriberListener;
import com.cracowgo.cracowgo.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterSubscriberListener {

    @BindView(R.id.email_editText)
    EditText emailEditText;

    @BindView(R.id.password_editText)
    EditText passwordEditText;

    @BindView(R.id.confirm_password_editText)
    EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.register_button)
    public void onRegisterButtonClick() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(findViewById(R.id.activity_register), R.string.email_cannot_be_empty, Snackbar.LENGTH_SHORT).show();
            return;
        } else if (!email.contains("@")) {
            Snackbar.make(findViewById(R.id.activity_register), R.string.incorrect_mail_format, Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Snackbar.make(findViewById(R.id.activity_register), R.string.password_cannot_be_empty, Snackbar.LENGTH_SHORT).show();
            return;
        } else if (password.length() < 6) {
            Snackbar.make(findViewById(R.id.activity_register), R.string.password_must_be_longer, Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (!confirmPassword.equals(password)) {
            Snackbar.make(findViewById(R.id.activity_register), R.string.password_confirmation_must_be_identical, Snackbar.LENGTH_SHORT).show();
            return;
        }

        User user = new User(email, password);

        CracowGoService.getInstance().register(user, this);
    }

    @Override
    public void onRegistrationCompleted(RegisterResponse registerResponse) {
        getSharedPreferences(Constants.SHARED_PREFERENCES, 0)
                .edit()
                .putString(Constants.userEmail, registerResponse.getUser().getEmail())
                .putString(Constants.accessToken, registerResponse.getHeaders().getAccessToken())
                .putString(Constants.tokenType, registerResponse.getHeaders().getTokenType())
                .putString(Constants.client, registerResponse.getHeaders().getClient())
                .putString(Constants.expiry, registerResponse.getHeaders().getExpiry())
                .putString(Constants.uid, registerResponse.getHeaders().getUid())
                .commit();


        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onRegistrationError() {
        Snackbar.make(findViewById(R.id.activity_register), "Registration error", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionError() {
        Snackbar.make(findViewById(R.id.activity_register), R.string.connection_offline, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUnknownError() {
        Snackbar.make(findViewById(R.id.activity_register), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }
}
