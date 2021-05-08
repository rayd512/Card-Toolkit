package com.example.cardtoolkit.Firebase;

import androidx.annotation.NonNull;

import com.example.cardtoolkit.Firebase.FBCallbacks.LoginUserCallback;
import com.example.cardtoolkit.Firebase.FBCallbacks.RegisterUserCallback;
import com.example.cardtoolkit.Firebase.FBCallbacks.ResetPassCallback;
import com.example.cardtoolkit.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAuthHandler {

    private FirebaseAuth mAuth;

    public FirebaseAuthHandler(FirebaseAuth auth) {
        mAuth = auth;
    }

    public void registerUser(String email, String password, RegisterUserCallback cb) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        user.sendEmailVerification();
                                        cb.onComplete(true);
                                    } else {
                                        cb.onComplete(false);
                                    }
                                }
                            });
                        } else {
                            cb.onComplete(false);
                        }
                    }
                });
    }

    public void loginUser(String email, String password, LoginUserCallback cb) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            cb.onComplete(true);
                        } else {
                            cb.onComplete(false);
                        }
                    }
                });
    }

    public boolean isUserVerified() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user.isEmailVerified();
    }

    public void sendEmailVerification() {
        FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification();
    }

    public void resetPassword(String email, ResetPassCallback cb) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    cb.onComplete(true);
                } else {
                    cb.onComplete(false);
                }
            }
        });
    }
}
