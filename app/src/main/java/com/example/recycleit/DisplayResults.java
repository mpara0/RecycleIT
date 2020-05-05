/**
 * this page will display the results for the location the user input in the search page,
 * web api will happen here
 */

package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DisplayResults extends AppCompatActivity {

    private String location;
    private JSONArray businessesArr;
    private List<Business> businesses;


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
        //loads information from API
        getAPI();
        //get RecycleViewer
        RecyclerView rv = (RecyclerView) findViewById(R.id.results);
        //initialize data and makes list
        businesses = Business.createBusinessList(businessesArr);
        //passes information to adapter
        BusinessAdapter adapter = new BusinessAdapter(businesses);
        //populates recyclerviewer
        rv.setAdapter(adapter);
        //sets layout
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
    //go back and add error listeners later
    public void getAPI() {
        String url = "https://api.yelp.com/v3/businesses/search";
        url += "?term=recycle&location=" + location;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject business = null;
                    try {
                        business = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    businessesArr.put(business);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
    }

}