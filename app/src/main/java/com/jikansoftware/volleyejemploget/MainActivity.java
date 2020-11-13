package com.jikansoftware.volleyejemploget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jikansoftware.volleyejemploget.adapter.ListViewAdapter;
import com.jikansoftware.volleyejemploget.model.Hero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://www.tecn0project.com/appi/array.php";

    ListView listView;

    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        heroList = new ArrayList<>();

        loadHeroList();
    }

    private void loadHeroList() {

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray heroArray = obj.getJSONArray("empleados");

                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                Hero hero = new Hero(heroObject.getString("nombre"), heroObject.getString("descripcion"), heroObject.getString("carrera"));
                                heroList.add(hero);
                            }

                            ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
