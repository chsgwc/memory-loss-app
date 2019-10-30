package com.example.memorylossapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
//!!! imageView.setImageResource(R.drawable.thumbs_up) or setBackgroundResource, (use first if you put image in your res/drawable folder)
public class RecognitionGame extends AppCompatActivity {

    int size;
    int counter;
    List keys;
    TextView temppictureTextView;
    EditText answerEditText;
    Button checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition_game);

        final ImageView theImageV = (ImageView) findViewById(R.id.imageView);
        theImageV.setImageResource(R.drawable.chair);

        final Map<String, Integer> mapQuestions = new LinkedHashMap<String, Integer>(); //no idea which Map to use!!
        mapQuestions.put("grandson", R.drawable.grandson);
        mapQuestions.put("Apple", R.drawable.apple);
        mapQuestions.put("Chair", R.drawable.chair);
        mapQuestions.put("Daughter", R.drawable.daughter);
        mapQuestions.put("Piano", R.drawable.piano);
        mapQuestions.put("Son", R.drawable.son);
        size = mapQuestions.size();
        counter = 0;
        keys = new ArrayList(mapQuestions.keySet()); //this might be wrong...
        Collections.shuffle(keys);

        Button nextBtn = (Button) findViewById(R.id.nextButton);
        //temppictureTextView = (TextView) findViewById(R.id.tempPicTextView);
        answerEditText = (EditText) findViewById(R.id.editText);
        checkBtn = (Button) findViewById(R.id.CheckButton);

        nextBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (counter == size-1){
                            showMessage("Game Finished!", "Congratulations, you remembered all the items!");
                            return;
                        }
                        theImageV.setImageResource(mapQuestions.get(keys.get(counter)));
                    }
                }
        );
        checkBtn.setOnClickListener( //hint option
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answerEditText.getText().toString().equals(keys.get(counter))){
                            showMessage("Correct!","Click Next");
                            counter++;
                        } else {
                            showMessage("Wrong :(", "Try Again");
                        }
                    }
                }
        );



    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
