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
import com.android.volley.AuthFailureError;
import com.android.volley.RetryPolicy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DisplayResults extends AppCompatActivity {

    private String location;
    private JSONArray businessesArr = new JSONArray();
    private List<Business> businesses = new ArrayList<>();
    private RecyclerView rv;
    private BusinessAdapter adapter;



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
        rv = (RecyclerView) findViewById(R.id.results);
        System.out.println("PRINTING PRINTING PRINTING PRINTING PRINTING PRINTING");

        //initialize data and makes list
        //businesses = Business.createBusinessList(businessesArr);

        //passes information to adapter
        //BusinessAdapter adapter = new BusinessAdapter(businesses);

        //populates recyclerviewer
        //rv.setAdapter(adapter);

        //sets layout
        //rv.setLayoutManager(new LinearLayoutManager(this));

    }
    //go back and add error listeners later
    public void getAPI() {
        String url = "https://api.yelp.com/v3/businesses/search";
        url += "?term=recycle&location=" + location;
        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject business = null;
                    try {
                        business = response.getJSONObject(i);
                        String businessName = business.getString("name");
                        System.out.println(businessName);
                        System.out.println("HELLO HELLO HELLO HELLO HELLO");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                        businessesArr.put(business);
                    System.out.println("DIFF DIFF DIFF DIFF");

                }
                System.out.println("HOLA HOLA HOLA HOLA HOLA");
                businesses = Business.createBusinessList(businessesArr);
                adapter = new BusinessAdapter(businesses);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(DisplayResults.this));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString() + "this is error");
                String body;
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                if (error.networkResponse.data != null) {
                    try {
                        body = error.networkResponse.data.toString();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println(body);
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer <t2rpiy5aGFcKdcI5E8WkU2dkyEXAHXE4XDRHTlLiBamezV" +
                        "-206YNfEo-C6H01oZgYpuk_gEZZA_Ka_On2VE-JUPy8ZPhA_M6Jnc7kVjHbhoFkbLSZnv6pU1TMgaxXnYx>");
                return headers;
            }
        };
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }
            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }
            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        queue.add(request);
    }

}