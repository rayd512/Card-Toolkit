package com.example.cardtoolkit.UserAuth.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardtoolkit.CardDisplay.Views.CardActivity;
import com.example.cardtoolkit.Firebase.FBCallbacks.LoginUserCallback;
import com.example.cardtoolkit.Firebase.FirebaseAuthHandler;
import com.example.cardtoolkit.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Login page
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    // UI Elements
    private TextView registerTextView, forgotTextView;
    private EditText emailEditText, passEditText;
    private Button loginButton;
    private ProgressBar progressBar;

    // Firebase handler
    private FirebaseAuthHandler mAuthHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up UI elements
        registerTextView = (TextView) findViewById(R.id.register_textView);
        registerTextView.setOnClickListener(this);
        forgotTextView = (TextView)findViewById(R.id.forgot_textView);
        forgotTextView.setOnClickListener(this);

        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        emailEditText = (EditText) findViewById(R.id.login_email_address_editText);
        passEditText = (EditText) findViewById((R.id.login_password_editText));

        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);

        mAuthHandler = new FirebaseAuthHandler(FirebaseAuth.getInstance());

        if(mAuthHandler.isSignedIn()) {
            startActivity(new Intent(this, CardActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_textView:
                startRegisterActivity();
                break;
            case R.id.forgot_textView:
                startForgotPasswordActivity();
                break;
            case R.id.login_button:
                handleLogin();


        }
    }

    /**
     * Starts the forgot password acitivity with no animation
     */
    private void startForgotPasswordActivity() {
        Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    /**
     * Starts the register acitivity with no animation  
     */
    private void startRegisterActivity() {
        Log.e("Debug", "Clicked Register");
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    private void handleLogin() {
        boolean valid = isValidForm();
        if(valid) {
            String email = emailEditText.getText().toString();
            String password = passEditText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            mAuthHandler.loginUser(email, password, new LoginUserCallback() {
                @Override
                public void onComplete(boolean success) {
                    progressBar.setVisibility(View.GONE);
                    if(!success) {
                        Toast.makeText(LoginActivity.this,
                                "Failed to Login, please check your credentials",
                                Toast.LENGTH_LONG);
                    } else {
                        if(mAuthHandler.isUserVerified()) {
                            // Start new activity here
                            startActivity(new Intent(getApplicationContext(), CardActivity.class));
                        } else {
                            mAuthHandler.sendEmailVerification();
                            Toast.makeText(LoginActivity.this,
                                    "Please verify your email",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please check your login credentials",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Checks if form is not blank and email is valid
     * @return
     */
    private boolean isValidForm() {
        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();

        if(email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        } else if(password.isEmpty()) {
            passEditText.setError("Password is required");
            passEditText.requestFocus();
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