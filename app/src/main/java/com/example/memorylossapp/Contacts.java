package com.example.memorylossapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;
import static com.example.memorylossapp.MakeAccount.myDB;

public class Contacts extends AppCompatActivity {
    Button viewAllBtn;
    String id, name, username, password, email, contactNames, contactPhones, contactRels;
    String[] contNameAr, contPhoneAr, contRelAr;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        constraintLayout = findViewById(R.id.ogcontainer);

        //set ID of the previous textview to be 3...? (of their Name's TextView ig...
        viewAllBtn = (Button) findViewById(R.id.extractBtn);
        viewAll();

        contNameAr = contactNames.split(",");
        contPhoneAr = contactPhones.split(",");
        contRelAr = contactRels.split(",");

        //adding correct num of Contacts onto the View
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        nameTextView.setId(3);
        nameTextView.setText(name);

        int numContacts = contNameAr.length; //in reality, it's = size of Contact strings
        final Context context = getApplicationContext();
        setContactsDisplay(numContacts, context);


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

    public void setContactsDisplay(int numContacts, Context context){
        for (int ID = 1; ID <= numContacts; ID++){
            ConstraintSet set = new ConstraintSet();
            TextView newContact2TextView = new TextView(context);
            TextView newContact2NameEditText = new TextView(context);
            TextView newContact2PhoneEditText = new TextView(context);
            TextView newContact2RelEditText = new TextView(context);

            newContact2TextView.setId(ID *100); //wtf is this ID
            newContact2NameEditText.setId(ID *100 +1); //wtf is this ID
            newContact2PhoneEditText.setId(ID *100 +2); //wtf is this ID
            newContact2RelEditText.setId(ID *100 +3);

            //hopefully this points an arrow to the EditText objects, not make a copy
            //cNames.add(newContact2NameEditText);
            //cPhones.add(newContact2PhoneEditText);
            //cRels.add(newContact2RelEditText);

            newContact2TextView.setHint("Contact " + ID + ":");
            newContact2NameEditText.setText(contNameAr[ID-1]);
//            newContact2PhoneEditText.setInputType(3); //phone input type = 3
            newContact2PhoneEditText.setText(contPhoneAr[ID-1]);
            newContact2RelEditText.setText(contRelAr[ID-1]);

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
            set.constrainWidth(ID*100, ConstraintSet.WRAP_CONTENT);
            set.constrainWidth(ID*100+1, ConstraintSet.WRAP_CONTENT);
            set.constrainWidth(ID*100+2, ConstraintSet.WRAP_CONTENT);
            set.constrainWidth(ID*100+3, ConstraintSet.WRAP_CONTENT);

            set.connect(ID *100, ConstraintSet.TOP,(ID -1)*100+3, ConstraintSet.BOTTOM, 60);
            set.connect(ID *100, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
            //set.connect(ID*100, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
            set.connect(ID*100+1, ConstraintSet.TOP, ID*100, ConstraintSet.BOTTOM);
            set.connect(ID *100+1, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
            set.connect(ID*100+2, ConstraintSet.TOP, ID*100+1, ConstraintSet.BOTTOM);
            set.connect(ID *100+2, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
            set.connect(ID*100+3, ConstraintSet.TOP, ID*100+2, ConstraintSet.BOTTOM);
            set.connect(ID *100+3, ConstraintSet.LEFT, PARENT_ID, ConstraintSet.LEFT, 108);
            set.applyTo(constraintLayout);

        }

    }
    public void viewAll() {
//        viewAllBtn.setOnClickListener(
//                new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
        Cursor res = myDB.getAllData();
        if (res.getCount() == 0) { //no data, no rows in the database
            showMessage("Error", "Nothing found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) { //will set latest information in database to the global variables
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("NAME :" + res.getString(1) + "\n");
            buffer.append("Username :" + res.getString(2) + "\n");
            buffer.append("Password :" + res.getString(3) + "\n");
            buffer.append("Email :" + res.getString(4) + "\n");
            buffer.append("ContactName :" + res.getString(5) + "\n");
            buffer.append("ContactPhone :" + res.getString(6) + "\n");
            buffer.append("ContactRelationship :" + res.getString(7) + "\n\n");
            id = res.getString(0);
            name = res.getString(1);
            username = res.getString(2);
            password = res.getString(3);
            email = res.getString(4);
            contactNames = res.getString(5);
            contactPhones = res.getString(6);
            contactRels = res.getString(7);
        }

        //showMessage("Data", buffer.toString());
    }
 //               }
//        );
//    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
