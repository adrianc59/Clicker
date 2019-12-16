package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class test12345 extends AppCompatActivity {


    com.github.clans.fab.FloatingActionButton shopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test12345);


        shopBtn = findViewById(R.id.floatingActionItem1);

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test12345.this, ShopActivity.class);
                startActivity(intent);
            }
        });
    }
}
