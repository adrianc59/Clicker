package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TapActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    ImageView tapper;
    TextView somethingCountView;
    Button shopBtn;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        somethingCountView = findViewById(R.id.tapperView);
        tapper = findViewById(R.id.tapper);
        shopBtn = findViewById(R.id.shopBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        final Session session = new Session(getApplicationContext());
        final String username = session.getUsername();

        somethingCountView.setText(session.getCurrCount() + " Reps!");

        tapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.updateCount(session, dbManager.getTotalCount(username));
                somethingCountView.setText(session.getCurrCount() + " Reps!");
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TapActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin("");
                Intent intent = new Intent(TapActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
