package com.example.clicker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private EditText username;
    private EditText email;
    private EditText pass;
    private EditText confirmPass;
    private TextView loginLink;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        username = findViewById(R.id.regUsername);
        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPassword);
        confirmPass = findViewById(R.id.regConfirmPassword);
        loginLink = findViewById(R.id.loginLink);
        registerBtn = findViewById(R.id.regBtn);

        username.addTextChangedListener(inputWatcher);
        email.addTextChangedListener(inputWatcher);
        pass.addTextChangedListener(inputWatcher);
        confirmPass.addTextChangedListener(inputWatcher);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validUsername = checkUsername(username.getText().toString());
                boolean validEmail = checkEmail(email.getText().toString());
                boolean validPassword = checkPassword(pass.getText().toString(), confirmPass.getText().toString());

                if(validUsername && validEmail && validPassword) {
                    User user = new User(username.getText().toString(), email.getText().toString(),pass.getText().toString());
                    dbManager.addUser(user);

                    Session session = new Session(getApplicationContext());
                    session.setUsername(username.getText().toString());
                    session.setCurrCount(0);
                    session.setMultiplier(1);

                    Intent intent = new Intent(RegisterActivity.this, TapActivity.class);
                    startActivity(intent);
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private final TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (username.getText().toString().length() == 0 || email.getText().toString().length() == 0 ||
                    pass.getText().toString().length() == 0 || confirmPass.getText().toString().length() == 0) {
                registerBtn.setEnabled(false);
            } else {
                registerBtn.setEnabled(true);
            }
        }
    };

    private boolean checkUsername(String username) {

        //Check if username is taken
        if(dbManager.checkUsernameExist(username)){
            Toast toast = Toast.makeText(getApplicationContext(), "Username already taken!", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkEmail(String email) {
        if(dbManager.checkEmailExist(email)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Email already taken!", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkPassword(String pass, String confirmPass) {

        Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");

        if(!(passwordPattern.matcher(pass).matches())) {
            Toast toast = Toast.makeText(getApplicationContext(), "Password is too weak \n Requires: lowercase, uppercase, number and special char", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        else if(!pass.equals(confirmPass)) {
            System.out.println("Pass: " + pass);
            System.out.println("Confirm Pass: " + confirmPass);

            Toast toast = Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        else {
            return true;
        }
    }

    private void addValidUser(String username, String email, String password){
        User user = new User(username, email, password);

        dbManager.addUser(user);
    }
}
