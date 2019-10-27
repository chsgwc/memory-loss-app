package com.example.memorylossapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeAccount extends AppCompatActivity {
    public static DatabaseHelper myDB;
    EditText editName, editUsername, editPassword, editEmail;
    Button submitButton, addContactButton;
    ConstraintLayout constraintLayout; //could be a linear layout if easier!!! (use this link:
    int ID;
    ArrayList<EditText> cNames = new ArrayList<EditText>();
    ArrayList<EditText> cPhones = new ArrayList<EditText>();
    ArrayList<EditText> cRels = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);
        Button tempBtn = (Button) findViewById(R.id.tempBtnNS);

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Make an Account page
                Intent startIntent = new Intent(getApplicationContext(), Contacts.class);
                startActivity(startIntent);
            }
        });

        constraintLayout = findViewById(R.id.ogConstraintLayout);

        //make instance of class: DatabaseHelper, calls constructor (create DB + table)!
        myDB = new DatabaseHelper(this);

        //Initializing variables for general information
        TextView title = (TextView) findViewById(R.id.titleTextView);
        editName = (EditText) findViewById(R.id.nameEditText);
        editUsername = (EditText) findViewById(R.id.usernameEditText);
        editPassword = (EditText) findViewById(R.id.passwordText);
        editEmail = (EditText) findViewById(R.id.emailEditText);


        //Initializing variables for contacts - most likely all useless.... lmao (can delete many of them)
        TextView contactTitle = (TextView) findViewById(R.id.ContactTitleTextView);
        TextView contact1Header = (TextView) findViewById(R.id.Contact1TextView);
        EditText contactName1 = (EditText) findViewById(R.id.ContactName1EditText);
        EditText contactPhone1 = (EditText) findViewById(R.id.ContactPhoneEditText);
        final EditText contactRel = (EditText) findViewById(R.id.ContactRelEditText);

        //Setting IDs to store in database easier
        contactName1.setId(1);
        contactPhone1.setId(2);
        contactRel.setId(3);
        cNames.add(contactName1);
        cPhones.add(contactPhone1);
        cRels.add(contactRel);


        //Initializing buttons
        submitButton = (Button) findViewById(R.id.submitBtn);
        addContactButton = (Button) findViewById(R.id.addContactBtn);
        ID = 1;

        //generate contacts on the screen
        final Context context = getApplicationContext();
        addContactButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //dynamically add buttons
                        ConstraintSet set = new ConstraintSet();
                        TextView newContact2TextView = new TextView(context);
                        EditText newContact2NameEditText = new EditText(context);
                        EditText newContact2PhoneEditText = new EditText(context);
                        EditText newContact2RelEditText = new EditText(context);

                        newContact2TextView.setId(ID *100); //wtf is this ID
                        newContact2NameEditText.setId(ID *100 +1); //wtf is this ID
                        newContact2PhoneEditText.setId(ID *100 +2); //wtf is this ID
                        newContact2RelEditText.setId(ID *100 +3);

                        //hopefully this points an arrow to the EditText objects, not make a copy
                        cNames.add(newContact2NameEditText);
                        cPhones.add(newContact2PhoneEditText);
                        cRels.add(newContact2RelEditText);

                        newContact2TextView.setText("Contact " + (ID+1) + ":");
                        newContact2NameEditText.setHint("Name of Contact");
                        newContact2PhoneEditText.setInputType(3); //phone input type = 3
                        newContact2PhoneEditText.setHint("Phone Number");
                        newContact2RelEditText.setHint("Relationship");

                        ConstraintLayout.LayoutParams textViewLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams EditTextNameLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams EditTextPhoneLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams EditTextRelLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);

                        newContact2TextView.setLayoutParams(textViewLayoutParams);
                        newContact2NameEditText.setLayoutParams(EditTextNameLayoutParams);
                        newContact2PhoneEditText.setLayoutParams(EditTextPhoneLayoutParams);
                        newContact2RelEditText.setLayoutParams(EditTextRelLayoutParams);

                        constraintLayout.addView(newContact2TextView);
                        constraintLayout.addView(newContact2NameEditText);
                        constraintLayout.addView(newContact2PhoneEditText);
                        constraintLayout.addView(newContact2RelEditText);

                        set.clone(constraintLayout);
                        set.connect(ID *100, ConstraintSet.TOP,(ID -1)*100+3, ConstraintSet.BOTTOM, 60);
                        set.connect(ID*100+1, ConstraintSet.TOP, ID*100, ConstraintSet.BOTTOM);
                        set.connect(ID*100+2, ConstraintSet.TOP, ID*100+1, ConstraintSet.BOTTOM);
                        set.connect(ID*100+3, ConstraintSet.TOP, ID*100+2, ConstraintSet.BOTTOM);
                        set.connect(addContactButton.getId(), ConstraintSet.TOP, ID*100+3, ConstraintSet.BOTTOM);

                        ID++;

                        set.applyTo(constraintLayout);
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
                        //.split(stringifyed list) to turn it back into a list...?
                        String[] cNamesAr = new String[ID];
                        String[] cPhonesAr = new String[ID];
                        String[] cRelsAr = new String[ID];
                        for (int i = 0; i < ID; i++){ //checkDatabase to make sure it stores the right amount!!!!!!!! AND UPDATED NAME/INFO!!! (because im linking the arraylist earlier)
                            //EditText finalcontactNameEditText = (EditText) findViewById(i*100 +1); - the EditText's weren't initialized globally... :(
                            //EditText finalcontactPhoneEditText = (EditText) findViewById(i*100 +2);
                            //EditText finalcontactRelEditText = (EditText) findViewById(i*100 +3);
                            cNamesAr[i] = cNames.get(i).getText().toString();
                            cPhonesAr[i] = cPhones.get(i).getText().toString();
                            cRelsAr[i] = cRels.get(i).getText().toString();
                        }
                        submitButton.setText("Names: " + cNames.toString() + "    Phones : " + cPhones.toString() + "   Rels: " + cRels.toString()); //probably wrong
                        boolean isInserted = myDB.insertData(editName.getText().toString(), editUsername.getText().toString(),
                                editPassword.getText().toString(), editEmail.getText().toString(), cNames.toString(), cPhones.toString(), cRels.toString());
                        if (isInserted)
                            Toast.makeText(MakeAccount.this, "Data Inserted!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MakeAccount.this, "Data not Inserted :(", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //public DatabaseHelper returnDB(){
    //    return myDB;
    //}

}
