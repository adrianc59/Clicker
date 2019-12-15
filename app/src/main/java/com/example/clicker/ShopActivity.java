package com.example.clicker;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    Button item1;
    Button item2;
    Button item3;
    Button item4;
    Button item5;

    TextView currCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Intent intent = getIntent();
        final int currCount = intent.getIntExtra("currCount", 0);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        final Session session = new Session(getApplicationContext());

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        currCountView = findViewById(R.id.currCountShopView);

        currCountView.setText("Balance = " + String.valueOf(currCount));

        final int[] items = dbManager.getItems(session);

        if(items[0] == 1) {
            item1.setBackgroundColor(Color.parseColor("#ff1111"));
        }
        if(items[1] == 1) {
            item2.setBackgroundColor(Color.parseColor("#ff1111"));
        }
        if(items[2] == 1) {
            item3.setBackgroundColor(Color.parseColor("#ff1111"));
        }
        if(items[3] == 1) {
            item4.setBackgroundColor(Color.parseColor("#ff1111"));
        }
        if(items[4] == 1) {
            item5.setBackgroundColor(Color.parseColor("#ff1111"));
        }

        item1.setOnClickListener(new View.OnClickListener() {
            int price = 100;
            int newMultiplier = 2;
            @Override
            public void onClick(View v) {
                if(items[0] == 0) {
                    if (currCount > price) {
                        dbManager.buyItem(session, newMultiplier, price, 1);

                        Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Can't Afford", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Already purchased", Toast.LENGTH_LONG).show();
                }
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            int price = 500;
            int newMultiplier = 10;
            @Override
            public void onClick(View v) {
                if(items[1] == 0) {
                    if(currCount > price) {
                        dbManager.buyItem(session, newMultiplier, price, 2);

                        Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Can't Afford",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Already purchased", Toast.LENGTH_LONG).show();
                }
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            int price = 1000;
            int newMultiplier = 100;
            @Override
            public void onClick(View v) {
                if(items[2] == 0) {
                    if(currCount > price) {
                        dbManager.buyItem(session, newMultiplier, price, 3);

                        Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Can't Afford",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Already purchased", Toast.LENGTH_LONG).show();
                }
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            int price = 10000;
            int newMultiplier = 500;
            @Override
            public void onClick(View v) {
                if(items[3] == 0) {
                    if(currCount > price) {
                        dbManager.buyItem(session, newMultiplier, price, 4);

                        Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Can't Afford",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Already purchased", Toast.LENGTH_LONG).show();
                }
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            int price = 150000;
            int newMultiplier = 1000;
            @Override
            public void onClick(View v) {
                if(items[4] == 0) {
                    if(currCount > price) {
                        dbManager.buyItem(session, newMultiplier, price, 5);

                        Intent intent = new Intent(ShopActivity.this, TapActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Can't Afford",Toast.LENGTH_LONG).show();
                    }
                } else {
                Toast.makeText(getApplicationContext(), "Already purchased", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
