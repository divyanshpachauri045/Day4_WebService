package com.example.divyansh.day3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<MyPojo> arrayList;
    private int resource;


    public CustomAdapter(@NonNull Context context, int resource,ArrayList<MyPojo> arraylist) {
        super(context, resource,arraylist);
        this.context = context;
        this.arrayList = arraylist;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource,null,false);

        TextView tname = view.findViewById(R.id.nameid);
        TextView tdes = view.findViewById(R.id.descriptionid);
        TextView tdob = view.findViewById(R.id.dobid);
        TextView tcountry = view.findViewById(R.id.countryid);
        TextView theight = view.findViewById(R.id.heightid);
        TextView tspouse = view.findViewById(R.id.spouseid);
        TextView tchildren  = view.findViewById(R.id.childrenid);

        ImageView imageView = view.findViewById(R.id.image);

        MyPojo myPojo = arrayList.get(position);

        tname.setText(myPojo.getName());
        tdes.setText(myPojo.getDescription());
        tdob.setText(myPojo.getDob());
        tcountry.setText(myPojo.getCountry());
        theight.setText(myPojo.getHeight());
        tspouse.setText(myPojo.getSpouse());
        tchildren.setText(myPojo.getChildren());

        Glide.with(context).load(myPojo.getImage()).into(imageView);
        return view;

    }
}
