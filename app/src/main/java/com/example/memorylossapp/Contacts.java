package com.example.memorylossapp;
//I THINK I NEED TO MAKE "MakeAccount" and "Contacts" PAGE THE SAME :(
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.example.memorylossapp.MakeAccount.myDB;

public class Contacts extends AppCompatActivity {
    Button viewAllBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        viewAllBtn = (Button) findViewById(R.id.extractBtn);
        viewAll();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void viewAll(){
        viewAllBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = myDB.getAllData();
                        if (res.getCount() == 0){ //no data, no rows in the database
                            showMessage("Error", "Nothing found in database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :" + res.getString(0)+"\n");
                            buffer.append("NAME :" + res.getString(1)+"\n");
                            buffer.append("Username :" + res.getString(2)+"\n");
                            buffer.append("Password :" + res.getString(3)+"\n");
                            buffer.append("Email :" + res.getString(4)+"\n");
                            buffer.append("ContactName :" + res.getString(5)+"\n");
                            buffer.append("ContactPhone :" + res.getString(6)+"\n");
                            buffer.append("ContactRelationship :" + res.getString(7)+"\n\n");
                        }

                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
    }
}
