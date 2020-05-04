/**
 * main page that the user will open up and direct him to the rest of the pages
 */
package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button for resources page
        findViewById(R.id.resources).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Resources.class);
                MainActivity.this.startActivity(intent);
            }
        });
        //button for about page
        findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                MainActivity.this.startActivity(intent);
            }
        });
        //button for search page
        findViewById(R.id.startRecycling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }


}





