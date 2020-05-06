package com.example.recycleit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * necessary for recycle view
 * creates format for information that will displayed
 */

public class Business {
    private String name;
    private String image_url;
    private String phoneNum;
    private String address;

    public Business(String nameStr, String image_urlStr, String phoneNumStr, String addressStr) {
        name = nameStr;
        image_url = image_urlStr;
        phoneNum = phoneNumStr;
        address = addressStr;
    }
    //getters to use for Adapter class
    public String getName() {
        return name;
    }
    public String getImage_url() {
        return image_url;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getAddress() {
        return address;
    }

    //organizes information so that I can turn it into a list and get all the information from the JSON objects
    public static ArrayList<Business> createBusinessList(JSONArray businesses) {
        int businessesSize = businesses.length();
        ArrayList<Business> businessList = new ArrayList<Business>();
        ArrayList<String> addressList = new ArrayList<String>();
        for (int i = 0; i < businessesSize; i++) {
            try {
                JSONObject business = businesses.getJSONObject(i);
                JSONObject location = business.getJSONObject("location");
                String streetAddress = location.getString("address1");
                String city = location.getString("city");
                String state = location.getString("state");
                String zipCode = location.getString("zip_code");
                String businessAddress = streetAddress + " " + city + ", " + state + " " + zipCode;
                String businessImageurl = business.getString("image_url");
                String businessPhoneNum = business.getString("phone");
                String businessName = business.getString("name");
                Business newBusiness = new Business(businessName, businessImageurl, businessPhoneNum,
                        businessAddress);
                businessList.add(newBusiness);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return businessList;
    }
}
