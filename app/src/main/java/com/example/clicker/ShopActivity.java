package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private int currPoints;

    Button item1;
    Button item2;
    Button item3;
    Button item4;
    Button item5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        final Session session = new Session(getApplicationContext());

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.updateMultiplier(session, 2);

                Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                startActivity(intent);
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.updateMultiplier(session, 10);

                Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                startActivity(intent);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.updateMultiplier(session, 100);

                Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                startActivity(intent);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.updateMultiplier(session, 500);

                Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                startActivity(intent);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.updateMultiplier(session, 1000);

                Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                startActivity(intent);
            }
        });
        //Create a listening event

    }
}
