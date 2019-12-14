package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private EditText email;
    private EditText password;
    private TextView registerLink;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Session session = new Session(getApplicationContext());
        checkLogin(session.getLogin());

        dbManager = new DatabaseManager(this);
        dbManager.open();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerLink = findViewById(R.id.regLink);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginDetailsMatch = validateMatch(email.getText().toString(), password.getText().toString());
                if (loginDetailsMatch) {
                    User user = dbManager.findAccount(email.getText().toString(), password.getText().toString());

                    Session session = new Session(getApplicationContext());
                    session.setLogin("Login");
                    session.setUsername(user.getUsername());
                    session.setCurrCount(user.getCurrCount());
                    session.setMultiplier(user.getMultiplier());

                    Intent intent = new Intent(LoginActivity.this, TapActivity.class);
                    startActivity(intent);
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateMatch(String username, String userPassword) {
        User x = dbManager.findAccount(username,userPassword);
        if(x.getUsername() == null) {
            Toast.makeText(getApplicationContext(),"Details don't match.",Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void checkLogin(String login) {
        if(login.equals("Login")) {
            Intent i = new Intent(LoginActivity.this, TapActivity.class);
            startActivity(i);
        }
    }
}
