package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //sends data to displayresults so it can query results
        //button for resources page
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.location);
                String locationInput = editText.getText().toString();
                //check for exceptions
                Intent intent = new Intent(Search.this, DisplayResults.class);
                intent.putExtra("location", locationInput);
                Search.this.startActivity(intent);
            }
        });
        //button to return home
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MainActivity.class);
                Search.this.startActivity(intent);
            }
        });
        //we should add something here to make sure the user doesn't input an empty box and we do the query w no parameters and crash our app
        //use alertdialog for that
    }


}
