package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private EditText email;
    private EditText password;
    private TextView registerLink;
    private TextView errorMsg;
    private TextView attempts;
    private Button loginBtn;
    private int count = 4;

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
        //errorMsg = findViewById(R.id.loginErrorMsg);
        //attempts = findViewById(R.id.attempts);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginDetailsMatch = validateMatch(email.getText().toString(), password.getText().toString());
                if(loginDetailsMatch){
                    //Validate and findAccount is double calling findAccount
                    User user = dbManager.findAccount(email.getText().toString(), password.getText().toString());

                    Session session = new Session(getApplicationContext());
                    session.setLogin("Login");
                    session.setUsername(user.getUsername());
                    session.setEmail(user.getEmail());
                    session.setCurrCount(user.getCurrCount());
                    session.setTotalCount(user.getTotalCount());

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

    private boolean validateMatch(String email, String userPassword) {
        try {
            if (!dbManager.findAccount(email, userPassword).equals(null)) {
                return true;
            } else {
                failedAttempt();
                return false;
            }
        }catch(NullPointerException e){
            failedAttempt();
            return false;
        }
    }

    private void failedAttempt() {
        count--;

        errorMsg.setText("Wrong email or password!");
        attempts.setText("No of attempts remaining: " + count);

        if(count == 0)
        {
            loginBtn.setEnabled(false);
        }
    }

    private void checkLogin(String login) {

        System.out.println("cxz" + login);
        if(login.equals("Login")){
            System.out.println("vcx");
            Intent i = new Intent(LoginActivity.this, TapActivity.class);
            startActivity(i);
        }
    }
}
