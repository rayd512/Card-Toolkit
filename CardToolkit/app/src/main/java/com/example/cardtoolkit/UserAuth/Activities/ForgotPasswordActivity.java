package com.example.cardtoolkit.UserAuth.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cardtoolkit.Firebase.FBCallbacks.ResetPassCallback;
import com.example.cardtoolkit.Firebase.FirebaseAuthHandler;
import com.example.cardtoolkit.R;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEditText;
    private Button resetButton;
    private ProgressBar progressBar;

    private FirebaseAuthHandler mAuthHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = (EditText) findViewById(R.id.forgot_email_address_editText);
        resetButton = (Button) findViewById(R.id.forgot_pass_button);
        resetButton.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.forgot_progressBar);

        mAuthHandler = new FirebaseAuthHandler(FirebaseAuth.getInstance());

    }

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgot_pass_button:
                handleReset();
                break;
        }
    }

    private void handleReset() {
        boolean valid = isValidForm();
        String email = emailEditText.getText().toString();
        if(valid) {
            progressBar.setVisibility(View.VISIBLE);
            mAuthHandler.resetPassword(email, new ResetPassCallback() {
                @Override
                public void onComplete(boolean success) {
                    if(success) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Please check your email to rest password",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "Something went wrong, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                    finish();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please check your email",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Checks if form is not blank and email is valid
     * @return
     */
    private boolean isValidForm() {
        String email = emailEditText.getText().toString();

        if(email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        }

        // Check for validity of email

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid Email");
            emailEditText.requestFocus();
            return false;
        }

        return true;
    }

}