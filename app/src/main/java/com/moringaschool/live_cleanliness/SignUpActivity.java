package com.moringaschool.live_cleanliness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText mFirstName;
    private EditText mEmailAdress;
    private EditText mTelephoneNumber;
    private EditText mPassword;
    private Button mSignUp;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String mName;
    private ProgressDialog mAuthProgressDialog;
    private ImageButton mLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirstName = (EditText) findViewById(R.id.fullName);
        mEmailAdress = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mTelephoneNumber = (EditText) findViewById(R.id.mobileNumber);
        mSignUp = (Button) findViewById(R.id.signUp);
        mLog = (ImageButton) findViewById(R.id.move);


        mSignUp.setOnClickListener(this);
        mLog.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        authenticationListener();
        createAuthProgressDialog();
    }

    @Override
    public void onClick(View v) {
        if (v == mSignUp) {
            signUp();
        }
        if (v == mLog){
            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    private void signUp() {
        final String firstName = mFirstName.getText().toString().trim();
//        final String secondName = mSecondName.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String telephone = mTelephoneNumber.getText().toString().trim();
        final String email = mEmailAdress.getText().toString().trim();
        mName = mFirstName.getText().toString().trim();

        boolean validFirstName = isValidFirstName(firstName);
//        boolean validSecondName = isValidSecondName(secondName);
        boolean validEmail = isValidEmail(email);
        boolean validPassword = isValidPassword(password);
        boolean validMobileN = isValidNumber(telephone);

        if (!validFirstName || !validEmail || !validPassword || !validMobileN)
            return;

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Auhentication succesful");
                    createFirebaseUserProfile(task.getResult().getUser());
                } else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void authenticationListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, user.getDisplayName());
                            Toast.makeText(SignUpActivity.this, "The display name has ben set", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean isValidFirstName(String name) {
        if (name.equals("")) {
            mFirstName.setError("Please enter your name");
            return false;
        }
        return true;
    }

//    private boolean isValidSecondName(String name) {
//        if (name.equals("")) {
//            mSecondName.setError("please enter name");
//            return false;
//        }
//        return true;
//    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailAdress.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidNumber(String telephone) {
        if (telephone.length()< 11){
            mTelephoneNumber.setError("Please enter telephone number");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        }
        return true;
    }
}
