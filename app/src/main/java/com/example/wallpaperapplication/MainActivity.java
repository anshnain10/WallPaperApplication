package com.example.wallpaperapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private ArrayList<Model> modelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        requestQueue=VolleySingelton.getVInstance(this).getRequestQueue();


        String url="https://pixabay.com/api/?key=29462245-506771b83454422d2c35edbcd&q=yellow+flowers&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("hits");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        String imageUrl=jsonObject.getString("webformatURL");
                        String likes=jsonObject.getString("likes");
                        String downloads=jsonObject.getString("downloads");
                        String user=jsonObject.getString("user");
                        String largeimage=jsonObject.getString("largeImageURL");

                        modelArrayList.add(new Model(user,likes,downloads,imageUrl,largeimage));
                    }
                    CustomAdapter ca=new CustomAdapter(modelArrayList,MainActivity.this);
                    recyclerView.setAdapter(ca);
                    ca.notifyDataSetChanged();

                }
                catch (JSONException e){
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                
            }
        });

requestQueue.add(jsonObjectRequest);
    }
}