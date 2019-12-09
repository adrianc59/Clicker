package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private Session session;

    //Store Tap multiplier in db

    Button level1;
    Button level2;
    Button level3;
    Button level4;
    Button level5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);

        //Create a listening event

    }
}
