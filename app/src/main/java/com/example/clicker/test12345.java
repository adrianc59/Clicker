package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class test12345 extends AppCompatActivity {


    com.github.clans.fab.FloatingActionButton shopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test12345);

        ImageView imageView = findViewById(R.id.imageViewTest);
        String url = "https://cdn4.iconfinder.com/data/icons/sports-fitness-line-color-vol-5/52/weight__dumbbell__fitness__gym__lifter__avatar__body-512.png";

        Picasso.with(this).load(url).into(imageView);

        shopBtn = findViewById(R.id.floatingActionItem1);

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test12345.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
