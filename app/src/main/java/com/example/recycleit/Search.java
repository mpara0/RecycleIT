package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //sends data to displayresults so it can query results
        //button for resources page
        //findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
                //EditText editText = findViewById(R.id.location);
                //String locationInput = editText.getText().toString();
                //check for exceptions
                //Intent intent = new Intent(Search.this, DisplayResults.class);
                //intent.putExtra("location", locationInput);
                //Search.this.startActivity(intent);
            //}
        //});
        //button to return home
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MainActivity.class);
                Search.this.startActivity(intent);
            }
        });

        //button to search
        findViewById(R.id.locationzz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, Testing.class);
                Search.this.startActivity(intent);
            }
        });
        //we should add something here to make sure the user doesn't input an empty box and we do the query w no parameters and crash our app
        //use alertdialog for that
    }

    public JSONArray getAPI(String input) {
        String url = "https://api.yelp.com/v3/businesses/search";
        url += "?term=recycle&location=" + input;
        final JSONArray businessesArr = new JSONArray();
        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject business = response.getJSONObject(i);
                        businessesArr.put(business);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        queue.add(request);
        return businessesArr;
    }


}
