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
    Button leaderBtn;
    Button logoutBtn;
    TextView multiplierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        somethingCountView = findViewById(R.id.tapperView);
        tapper = findViewById(R.id.tapper);
        shopBtn = findViewById(R.id.shopBtn);
        leaderBtn = findViewById(R.id.leaderBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        multiplierView = findViewById(R.id.multiplierView);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        final Session session = new Session(getApplicationContext());
        final String username = session.getUsername();

        multiplierView.setText("Multiplier = X" + session.getMultiplier());
        somethingCountView.setText(session.getCurrCount() + " Reps!");

        tapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.updateCount(session, dbManager.getTotalCount(username));
                somethingCountView.setText(session.getCurrCount() + " Reps!");
            }
        });

        leaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TapActivity.this, MainActivity.class);
                intent.putExtra("username", session.getUsername());
                intent.putExtra("totalCount", dbManager.getTotalCount(session.getUsername()));
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TapActivity.this, ShopActivity.class);
                intent.putExtra("currCount", session.getCurrCount());
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
