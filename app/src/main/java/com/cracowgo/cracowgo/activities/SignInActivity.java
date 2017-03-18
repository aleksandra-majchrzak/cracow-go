package com.cracowgo.cracowgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.cracowgo.cracowgo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.email_editText)
    EditText emailEditText;

    @BindView(R.id.password_editText)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.signin_button)
    public void onSignInClick() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(findViewById(R.id.activity_sign_in), R.string.email_cannot_be_empty, Snackbar.LENGTH_SHORT).show();
            return;
        } else if (!email.contains("@")) {
            Snackbar.make(findViewById(R.id.activity_sign_in), R.string.incorrect_mail_format, Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Snackbar.make(findViewById(R.id.activity_sign_in), R.string.password_cannot_be_empty, Snackbar.LENGTH_SHORT).show();
            return;
        } else if (password.length() < 6) {
            Snackbar.make(findViewById(R.id.activity_sign_in), R.string.password_must_be_longer, Snackbar.LENGTH_SHORT).show();
            return;
        }

        finish();

        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
