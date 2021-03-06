package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEt;
    private EditText passwordEt;
    private Button btnLogin;
    private Button signButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }


        usernameEt = findViewById(R.id.username);
        passwordEt = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        signButton = findViewById(R.id.signButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                loginUser(username, password);

            }


        });
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                signUp(username, password);

            }


        });
    }
        private void loginUser(String username, String password){
            Log.e("trying to log in with", username + " " + password);
            //Allows user to continue using the app since log in happens in the background
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e != null) {
                        //TODO: better error handling
                        Log.e("Login Activity", "problem with login :/" + e);
                        return;
                    }
                    goMainActivity();
                    Log.e("this is ", "working!!");
                    Toast.makeText(LoginActivity.this, "hi queen", Toast.LENGTH_SHORT).show();
                }
            });

    }

    private void signUp(String username, String password){

        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.e("Sign up" , " was successful");
                    // Hooray! Let them use the app now.
                } else {
                    Log.e("Sign up" , " was not successful " + e );
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });


    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();


    }
}