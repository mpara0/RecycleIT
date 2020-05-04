package com.example.recycleit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import 	android.view.View;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

import java.nio.DoubleBuffer;
import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView businessInformation;
        public ImageView businessImage;

        public ViewHolder(View view) {
            super(view);
            //gets attributes from item_results file
            businessInformation = (TextView) view.findViewById(R.id.business_information);
            businessImage = (ImageView) view.findViewById(R.id.business_image);
        }
    }

    private List<Business> myBusinesses;

    public BusinessAdapter(List<Business> businesses) {
        myBusinesses = businesses;
    }

    @Override
    public BusinessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //layout stuff so that the information goes to the item_result xml file and follows that format
        View businessView = layoutInflater.inflate(R.layout.item_result, parent, false);
        ViewHolder viewHolder = new ViewHolder(businessView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BusinessAdapter.ViewHolder viewHolder, int position) {
        Business newBusiness = myBusinesses.get(position);
        //turn business strings into one string to display
        String information = newBusiness.getName() + "\nAddress: " + newBusiness.getAddress() + "\nPhone Number: " +
                newBusiness.getPhoneNum();
        //adds it to the textview
        TextView textView = viewHolder.businessInformation;
        textView.setText(information);
        //loads image using business url and picasso
        ImageView imageView = viewHolder.businessImage;
        String url = newBusiness.getImage_url();


        Picasso.get().load(url).into(imageView);

    }
    //this is useless but it wouldn't stop giving me errors without it bc its a class requirement
    public int getItemCount() {
        return myBusinesses.size();
    }


}
