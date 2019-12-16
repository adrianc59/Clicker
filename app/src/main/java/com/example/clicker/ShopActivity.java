package com.example.clicker;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.clicker.BaseApplication.CHANNEL_1_ID;
import static com.example.clicker.BaseApplication.CHANNEL_2_ID;

public class ShopActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private NotificationManagerCompat notificationManager;

    Button item1;
    Button item2;
    Button item3;
    Button item4;
    Button item5;

    Button testBtn;

    TextView currCountView;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;
    ProgressBar progressBar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Intent intent = getIntent();
        final int currCount = intent.getIntExtra("currCount", 0);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        notificationManager = NotificationManagerCompat.from(this);

        final Session session = new Session(getApplicationContext());

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        currCountView = findViewById(R.id.currCountShopView);

        testBtn = findViewById(R.id.testBtn);

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);
        progressBar5 = findViewById(R.id.progressBar5);

        progressBar1.setProgress(currCount);
        progressBar2.setProgress(currCount);
        progressBar3.setProgress(currCount);
        progressBar4.setProgress(currCount);
        progressBar5.setProgress(currCount);

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

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hi");
                sendOnChannel1("Purchased X2 Multiplier!");
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            int price = 100;
            int newMultiplier = 2;
            @Override
            public void onClick(View v) {
                sendOnChannel1("Purchased X2 Multiplier!");

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

    public void sendOnChannel1(String message) {
        String title = "Keep Going!";

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(String message) {
        String title = "Keep Going!";

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}