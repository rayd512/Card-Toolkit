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

import com.example.cardtoolkit.Firebase.FirebaseAuthHandler;
import com.example.cardtoolkit.Firebase.FBCallbacks.RegisterUserCallback;
import com.example.cardtoolkit.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Registers a user
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuthHandler mAuthHandler;

    private TextView title;
    private EditText emailEditText, passwordEditText, confirmEditText;
    private Button registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuthHandler = new FirebaseAuthHandler(FirebaseAuth.getInstance());

        title = (TextView) findViewById(R.id.title_textView);
        title.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);

        emailEditText = (EditText) findViewById(R.id.reg_email_address_editText);
        passwordEditText = (EditText) findViewById(R.id.reg_password_editText);
        confirmEditText = (EditText) findViewById(R.id.reg_confirm_password_editText);

        progressBar = (ProgressBar) findViewById((R.id.reg_progressBar));



    }

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_textView:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.register_button:
                boolean valid = isValidForm();
                if(valid) {

                    Log.d("AUTH", "Valid registration form");

                    progressBar.setVisibility(View.VISIBLE);
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    mAuthHandler.registerUser(email, password, new RegisterUserCallback() {
                        @Override
                        public void onComplete(boolean success) {
                            progressBar.setVisibility(View.GONE);
                            if(success) {
                                Toast.makeText(getApplicationContext(),
                                        "Succesfully registered, please verify your email",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Failed to register user, please try again",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                break;
        }
    }

    /**
     * Checks form for validity
     * @return boolean form valid or not
     */
    private boolean isValidForm() {

        // Get string values from EditText
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPass = confirmEditText.getText().toString();

        // Check for empty fields
        if(email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        } else if(password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        } else if(confirmPass.isEmpty()) {
            confirmEditText.setError("Confirming Password is required");
            confirmEditText.requestFocus();
            return false;
        }


        // Check for validity of email

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid Email");
            emailEditText.requestFocus();
            return false;
        }

        // Check for min password length
        if(password.length() < 8) {
            passwordEditText.setError("Password must be at least 8 characters");
            passwordEditText.requestFocus();
            return false;
        }

        // Check if password matches
        if(!password.equals(confirmPass)) {
            passwordEditText.setError("Passwords do not match");
            passwordEditText.setText("");
            confirmEditText.setText("");
            passwordEditText.requestFocus();
            return false;
        }

        // Return true when form passes all checks
        return true;
    }
}