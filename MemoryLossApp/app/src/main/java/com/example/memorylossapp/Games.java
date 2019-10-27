package com.example.memorylossapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Games extends AppCompatActivity {

    int size;
    int counter;
    List keys;
    TextView temppictureTextView;
    EditText answerEditText;
    Button checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        final Map<String, String> mapQuestions = new LinkedHashMap<String, String>(); //no idea which Map to use!!
        mapQuestions.put("Picture: toothbrush", "Toothbrush");
        mapQuestions.put("Picture: Apple", "Apple");
        mapQuestions.put("Picture: Chair", "Chair");
        mapQuestions.put("Picture: Table", "Table");
        size = mapQuestions.size();
        counter = 0;
        keys = new ArrayList(mapQuestions.keySet());
        Collections.shuffle(keys);

        Button nextBtn = (Button) findViewById(R.id.nextButton);
        temppictureTextView = (TextView) findViewById(R.id.tempPicTextView);
        answerEditText = (EditText) findViewById(R.id.editText);
        checkBtn = (Button) findViewById(R.id.CheckButton);
        nextBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (counter == size-1){
                            showMessage("Game Finished!", "Your score was ____");
                            return;
                        }
                        temppictureTextView.setText((String) keys.get(counter));
                    }
                }
        );
        checkBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answerEditText.getText().toString().equals(mapQuestions.get(keys.get(counter)).toString())){
                            showMessage("Correct!","Click Next");
                        } else {
                            showMessage("Wrong :(", "Try Again");
                        }
                    }
                }
        );



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

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
    }
}
