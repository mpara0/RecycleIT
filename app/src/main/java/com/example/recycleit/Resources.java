/**
 * this page will contain information on recycling(what can and can't be recycled, etc)
 */

package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Resources extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        //button to return home
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resources.this, MainActivity.class);
                Resources.this.startActivity(intent);
            }
        });
    }
}
