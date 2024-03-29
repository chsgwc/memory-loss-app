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

import static androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;

public class MakeAccount extends AppCompatActivity {
    public static DatabaseHelper myDB;
    EditText editName, editUsername, editPassword, editEmail;
    Button submitButton, addContactButton;
    ConstraintLayout constraintLayout; //could be a linear layout if easier!!! (use this link:
    int ID;
    ArrayList<EditText> cNames = new ArrayList<EditText>();
    ArrayList<EditText> cPhones = new ArrayList<EditText>();
    ArrayList<EditText> cRels = new ArrayList<EditText>();
    private static final int GALLERY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);

        constraintLayout = findViewById(R.id.ogConstraintLayout);

        //make instance of class: DatabaseHelper, calls constructor (create DB + table)!
        myDB = new DatabaseHelper(this);

        //Initializing variables for general information
        TextView title = (TextView) findViewById(R.id.titleTextView);
        editName = (EditText) findViewById(R.id.nameEditText);
        editUsername = (EditText) findViewById(R.id.usernameEditText);
        editPassword = (EditText) findViewById(R.id.passwordText);
        editEmail = (EditText) findViewById(R.id.emailEditText);


        //Initializing variables for contacts
        TextView contactTitle = (TextView) findViewById(R.id.ContactTitleTextView);
        TextView contact1Header = (TextView) findViewById(R.id.Contact1TextView);
        EditText contactName1 = (EditText) findViewById(R.id.ContactName1EditText);
        EditText contactPhone1 = (EditText) findViewById(R.id.ContactPhoneEditText);
        final EditText contactRel = (EditText) findViewById(R.id.ContactRelEditText);
        final Button contactBut = (Button) findViewById(R.id.buttonPic);

        //Setting IDs to store in database easier
        contactName1.setId(1);
        contactPhone1.setId(2);
        contactRel.setId(3);
        contactBut.setId(4);
        cNames.add(contactName1);
        cPhones.add(contactPhone1);
        cRels.add(contactRel);
        contactBut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pickFromGallery();
                        contactBut.setBackgroundColor(0xFF38BD46);//blue:(0xFF73CCFF); //and shows up way too early :((
                    }
                }
        );


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
                        Button newBut = new Button(context);

                        newContact2TextView.setId(ID *100);
                        newContact2NameEditText.setId(ID *100 +1);
                        newContact2PhoneEditText.setId(ID *100 +2);
                        newContact2RelEditText.setId(ID *100 +3);
                        newBut.setId(ID *100 +4);

                        //hopefully this points an arrow to the EditText objects, not make a copy
                        cNames.add(newContact2NameEditText);
                        cPhones.add(newContact2PhoneEditText);
                        cRels.add(newContact2RelEditText);

                        newContact2TextView.setText("Contact " + (ID+1) + ":");
                        newContact2NameEditText.setHint("Name of Contact");
                        newContact2PhoneEditText.setInputType(3); //phone input type = 3
                        newContact2PhoneEditText.setHint("Phone Number");
                        newContact2RelEditText.setHint("Relationship");
                        newBut.setText("Attach Picture");

                        ConstraintLayout.LayoutParams textViewLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams EditTextNameLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams EditTextPhoneLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams EditTextRelLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        ConstraintLayout.LayoutParams ButRelLayoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);

                        newContact2TextView.setLayoutParams(textViewLayoutParams);
                        newContact2NameEditText.setLayoutParams(EditTextNameLayoutParams);
                        newContact2PhoneEditText.setLayoutParams(EditTextPhoneLayoutParams);
                        newContact2RelEditText.setLayoutParams(EditTextRelLayoutParams);
                        newBut.setLayoutParams(ButRelLayoutParams);

                        constraintLayout.addView(newContact2TextView);
                        constraintLayout.addView(newContact2NameEditText);
                        constraintLayout.addView(newContact2PhoneEditText);
                        constraintLayout.addView(newContact2RelEditText);
                        constraintLayout.addView(newBut);

                        set.clone(constraintLayout);

                        set.constrainWidth(ID*100, ConstraintSet.WRAP_CONTENT);
                        set.constrainWidth(ID*100+1, ConstraintSet.WRAP_CONTENT);
                        set.constrainWidth(ID*100+2, ConstraintSet.WRAP_CONTENT);
                        set.constrainWidth(ID*100+3, ConstraintSet.WRAP_CONTENT);
                        set.constrainWidth(ID*100+4, ConstraintSet.WRAP_CONTENT);

                        set.connect(ID *100, ConstraintSet.TOP,(ID -1)*100+4, ConstraintSet.BOTTOM, 60);
                        set.connect(ID *100, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
                        //set.connect(ID*100, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                        set.connect(ID*100+1, ConstraintSet.TOP, ID*100, ConstraintSet.BOTTOM);
                        set.connect(ID *100+1, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
                        set.connect(ID*100+2, ConstraintSet.TOP, ID*100+1, ConstraintSet.BOTTOM);
                        set.connect(ID *100+2, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
                        set.connect(ID*100+3, ConstraintSet.TOP, ID*100+2, ConstraintSet.BOTTOM);
                        set.connect(ID *100+3, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
                        set.connect(ID*100+4, ConstraintSet.TOP, ID*100+3, ConstraintSet.BOTTOM);
                        set.connect(ID *100+4, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
                        set.connect(addContactButton.getId(), ConstraintSet.TOP, ID*100+4, ConstraintSet.BOTTOM);

                        ID++;

                        set.applyTo(constraintLayout);

                        newBut.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        pickFromGallery();
                                        //some way of showing it is complete...
                                    }
                                }
                        );

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
                        //pickFromGallery();
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
                        //submitButton.setText("Names: " + String.join(",", cNamesAr) + "    Phones : " + cPhones.toString() + "   Rels: " + cRels.toString()); //probably wrong


                        //the real part
                        boolean isInserted = myDB.insertData(editName.getText().toString(), editUsername.getText().toString(),
                                editPassword.getText().toString(), editEmail.getText().toString(), String.join(",", cNamesAr), String.join(",", cPhonesAr), String.join(",", cRelsAr));
                        if (isInserted)
                            Toast.makeText(MakeAccount.this, "Data Inserted!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MakeAccount.this, "Data not Inserted :(", Toast.LENGTH_LONG).show();

                        Intent startIntent = new Intent(getApplicationContext(), ContactsPage.class);
                        startActivity(startIntent);
                    }
                }
        );
    }

    //public DatabaseHelper returnDB(){
    //    return myDB;
    //}
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

}
