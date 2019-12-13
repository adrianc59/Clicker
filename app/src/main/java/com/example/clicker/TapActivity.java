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
    private Session session;

    int perTap = 1;

    ImageView tapper;
    TextView somethingCountView;
    Button shopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        session = new Session(getApplicationContext());
        String username = session.getUsername();
        String email = session.getEmail();
        int currCount = session.getCurrCount();
        int totalCount = session.getTotalCount();

        somethingCountView = findViewById(R.id.tapperView);
        tapper = findViewById(R.id.tapper);
        shopBtn = findViewById(R.id.shopBtn);

        somethingCountView.setText(String.valueOf(currCount) + " Reps!");

        tapper.setOnClickListener(new View.OnClickListener() {
            final Session session = new Session(getApplicationContext());
            @Override
            public void onClick(View view) {
                session.setCurrCount(session.getCurrCount() + 1);
                session.setTotalCount(session.getTotalCount() + 1);

                dbManager.updateCount(session);
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
    }
}
