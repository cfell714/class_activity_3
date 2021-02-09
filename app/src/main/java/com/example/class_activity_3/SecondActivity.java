package com.example.class_activity_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity{

    private ArrayList<Forecast> forecasts;
    private RecyclerView recyclerView;
    private TextView textView_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerView);
        textView_title = findViewById(R.id.textView_title);
        forecasts = new ArrayList<>();

        Intent intent = getIntent();


        if(intent.getStringArrayListExtra("description") == null){
            textView_title.setText("NO CITY FOUND");

        }else {

            String title = intent.getStringExtra("title");
            String country = intent.getStringExtra("country");
            textView_title.setText(title + ", " + country);

            ArrayList<String> test = intent.getStringArrayListExtra("description");
            ArrayList<String> test2 = intent.getStringArrayListExtra("date");
            ArrayList<String> test3 = intent.getStringArrayListExtra("feelslike");

            int N = test.size();
            for (int i = 0; i < N; i++) {
                Forecast forecast = new Forecast(test.get(i),
                        test2.get(i),
                        test3.get(i));

                forecasts.add(forecast);
            }
            ForecastAdapter adapter = new ForecastAdapter(forecasts);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
    }

}
