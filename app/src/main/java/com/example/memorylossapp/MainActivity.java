package com.example.memorylossapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signupButton = (Button) findViewById(R.id.signupBtn);
        Button gamesButton = (Button) findViewById(R.id.GamesButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Make an Account page
                Intent startIntent = new Intent(getApplicationContext(), MakeAccount.class);
                startActivity(startIntent);
            }
        });

        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Games page
                Intent startIntent = new Intent(getApplicationContext(), RecognitionGame.class);
                startActivity(startIntent);
            }
        });

        //notifications
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mBuilder.setContentTitle("hi");
        mBuilder.setContentText("hey");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }


}
