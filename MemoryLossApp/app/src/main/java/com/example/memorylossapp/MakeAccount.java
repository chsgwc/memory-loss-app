package com.example.memorylossapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MakeAccount extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editName, editUsername, editPassword, editEmail;
    Button submitButton, addContactButton;
    ConstraintLayout constraintLayout; //could be a linear layout if easier!!! (use this link:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);

        constraintLayout = findViewById(R.id.ogConstraintLayout);

        //make instance of class: DatabaseHelper, calls constructor (create DB + table)!
        myDB = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.nameEditText);
        editUsername = (EditText) findViewById(R.id.usernameEditText);
        editPassword = (EditText) findViewById(R.id.passwordText);
        editEmail = (EditText) findViewById(R.id.emailEditText);
        submitButton = (Button) findViewById(R.id.submitBtn);
        addContactButton = (Button) findViewById(R.id.addContactBtn);

        //generate contacts on the screen
        final Context context = getApplicationContext();
        addContactButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //dynamically add buttons
                        TextView textView = new TextView(context);
                        textView.setText("made in JAVA CODE!!!!! BUT I CANT POSITION IT CORRECTLY");
                        ConstraintLayout.LayoutParams textViewLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        textView.setLayoutParams(textViewLayoutParams);
                        constraintLayout.addView(textView);
                    }
                }
        );

        AddData(); //method will be called whenever submit Button clicked!
    }

    //storing data in database
    public void AddData() {
        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertData(editName.getText().toString(), editUsername.getText().toString(), editPassword.getText().toString(), editEmail.getText().toString());
                        if (isInserted)
                            Toast.makeText(MakeAccount.this, "Data Inserted!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MakeAccount.this, "Data not Inserted :(", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


}
