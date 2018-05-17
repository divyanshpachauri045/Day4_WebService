package com.example.divyansh.day3;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView l;
    private CustomAdapter adapter;
    private ArrayList<MyPojo> arrayList = new ArrayList<>();

    private String URL = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    private RequestQueue requestQueue;
    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait");
        dialog.setCancelable(false);
        dialog.show();

        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            dialog.cancel();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("actors");

                    for(int i=0 ; i< jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String name = jsonObject1.getString("name");
                        String description = jsonObject1.getString("description");
                        String dob =jsonObject1.getString("dob");
                        String country =jsonObject1.getString("country");
                        String height =jsonObject1.getString("height");
                        String spouse =jsonObject1.getString("spouse");
                        String children =jsonObject1.getString("children");
                        String image =jsonObject1.getString("image");


                        MyPojo myPojo = new MyPojo();
                        myPojo.setName(name);
                        myPojo.setDescription(description);
                        myPojo.setDob(dob);
                        myPojo.setCountry(country);
                        myPojo.setHeight(height);
                        myPojo.setSpouse(spouse);
                        myPojo.setChildren(children);
                        myPojo.setImage(image);

                        arrayList.add(myPojo);
                    }

                    l = findViewById(R.id.list);
                    adapter = new CustomAdapter(MainActivity.this,R.layout.list_item,arrayList);
                    l.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.cancel();
                Toast.makeText(MainActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);




        }

}
