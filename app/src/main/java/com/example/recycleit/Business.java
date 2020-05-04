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
            //gets attributes from JSON array that was extracted from API
            JSONObject business = null;
            JSONObject location = null;
            String businessName = null;
            String businessImageURL = null;
            String businessStreetAddress = null;
            String businessCity = null;
            String businessZipcode = null;
            String businessState = null;
            String businessPhoneNum = null;
            try {
                business = businesses.getJSONObject(i);
                location = business.getJSONObject("location");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //gets individual attributes
            try {
                businessName = business.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                businessImageURL = business.getString("image_url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                businessPhoneNum = location.getString("phone");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                businessStreetAddress = location.getString("address1");
                addressList.add(businessStreetAddress);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                businessCity = location.getString("city");
                addressList.add(businessCity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                businessState = location.getString("state");
                addressList.add(businessState);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                businessZipcode = location.getString("zip_code");
                addressList.add(businessZipcode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //makes address one string
            String address = null;
            for (int j = 0; j < addressList.size(); j++) {
                address += addressList.get(i) + " ";
            }
            //creates business object for list and adds it
            Business newBusiness = new Business(businessName, businessImageURL, businessPhoneNum,
                    address);
            businessList.add(newBusiness);

        }
        return businessList;
    }
}
