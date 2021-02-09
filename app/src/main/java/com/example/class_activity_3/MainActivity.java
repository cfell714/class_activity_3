package com.example.class_activity_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private Button button_main;
    private SearchView searchView_main;
    private List<String> all_date;
    private List<String> all_description;
    private List<String> all_feels;


    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_main = findViewById(R.id.button_main);
        searchView_main = findViewById(R.id.searchView_main);

        button_main.setOnClickListener(v ->
                launchNextActivity(v));
    }


    public void launchNextActivity(View view) {
        CharSequence query = searchView_main.getQuery().toString();
        String api_url = "https://api.openweathermap.org/data/2.5/forecast/?q=" + query + "&cnt=40&appid=997475d54886c4fb4db2803703fb4007&units=imperial";
      //  String api_url_2 = "https://api.openweathermap.org/data/2.5/weather/?q=" + query + "&appid=997475d54886c4fb4db2803703fb4007&units=imperial";

        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    JSONArray forecastArray = json.getJSONArray("list");

                    all_description = new ArrayList<String>();
                    all_date = new ArrayList<String>();
                    all_feels = new ArrayList<String>();

                    for (int i = 0; i < forecastArray.length(); i ++){
                        JSONObject forecastArray_1 = forecastArray.getJSONObject(i);
                        System.out.println("JSONObejct " + forecastArray_1);

                        // dt_txt
                        String date = forecastArray_1.getString("dt_txt");
                        System.out.println("THIS IS DATE " + date);

                        all_date.add(date);

                        // description
                        JSONArray weather = forecastArray_1.getJSONArray("weather");
                        System.out.println("Weather" + weather);
                        JSONObject obj = weather.getJSONObject(0);
                        String object = obj.getString("description");
                        System.out.println("descriptions: " + object);

                        all_description.add(object);

                        // feels_like
                        JSONObject main = forecastArray_1.getJSONObject("main");
                        String object_2 = main.getString("feels_like");
                        System.out.println("feels like: " + object_2);

                        all_feels.add(object_2);


                    }
                    JSONObject city = json.getJSONObject("city");
                    String country = city.getString("country");

                    intent.putExtra("country", country);
                    intent.putExtra("title", query);
                    intent.putStringArrayListExtra("description", (ArrayList<String>) all_description);
                    intent.putStringArrayListExtra("date", (ArrayList<String>) all_date);
                    intent.putStringArrayListExtra("feelslike", (ArrayList<String>) all_feels);

                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // when you get a 400-499 status code
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String message = "No city found";
                intent.putExtra("weather", message);
                startActivityForResult(intent, 1);
            }
        });

    }


}


