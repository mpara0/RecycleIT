/**
 * this page will display the results for the location the user input in the search page,
 * web api will happen here
 */

package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayResults extends AppCompatActivity {

    private String location;
    private JSONArray businesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        Bundle bundle = getIntent().getExtras();
        location = bundle.getString("location");
        //button to return home
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayResults.this, MainActivity.class);
                DisplayResults.this.startActivity(intent);
            }
        });
        //button to return home
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayResults.this, Search.class);
                DisplayResults.this.startActivity(intent);
            }
        });
    }
    //thinking of making all businesses into map instead
    /**
    public JSONArray getAPI() {
        String url = "https://api.yelp.com/v3/businesses/search";
        url += "?term=recycle&location=" + location;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            //this method will determine how the information will be extracted and saved into a jsonarray

            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //handle exception
                        JSONObject business = null;
                        if (response.get(i).is_closed == false) {
                            business = response.getJSONObject(i);
                        }
                    } catch {

                    }
                }
            }
        });
    }**/

}