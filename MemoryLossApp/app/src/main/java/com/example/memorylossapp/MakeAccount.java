package com.example.memorylossapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MakeAccount extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editName, editUsername, editPassword, editEmail;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);

        //make instance of class: DatabaseHelper, calls constructor (create DB + table)!
        myDB = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.nameEditText);
        editUsername = (EditText) findViewById(R.id.usernameEditText);
        editPassword = (EditText) findViewById(R.id.passwordText);
        editEmail = (EditText) findViewById(R.id.emailEditText);
        submitButton = (Button) findViewById(R.id.submitBtn);

        AddData(); //method will be called whenever submit Button clicked!
    }

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
