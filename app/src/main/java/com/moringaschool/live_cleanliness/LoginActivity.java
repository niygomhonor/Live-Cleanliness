package com.moringaschool.live_cleanliness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG =LoginActivity.class.getSimpleName();
    private EditText mAdressEmail;
    private EditText mPassw;
    private Button mLogin;
    private TextView mCreateAccount;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAdressEmail = (EditText) findViewById(R.id.adress);
        mPassw = (EditText)findViewById(R.id.pass);
        mLogin = (Button) findViewById(R.id.logIn);
        mCreateAccount =(TextView) findViewById(R.id.create);
        mAuth = FirebaseAuth.getInstance();

        mLogin.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                }
            }
        };
        createAuthProgressDialog();
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccount) {
            Intent create = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(create);
        }
        if (v == mLogin){
            logIn();
        }

    }

    private void createAuthProgressDialog(){
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void logIn() {
        String email = mAdressEmail.getText().toString().trim();
        String password = mPassw.getText().toString().trim();
        if (email.equals("")){
            mAdressEmail.setError("please enter your email");
            return;
        }
        if(password.equals("")){
            mPassw.setError("Password cannot be blank");
            return;
        }
        mAuthProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "Welcome ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
