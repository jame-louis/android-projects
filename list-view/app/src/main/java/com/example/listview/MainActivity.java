package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> cities = CityData.getAllCities();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout city_list = findViewById(R.id.city_list);
        for(String city : cities) {
            TextView tv = new TextView(city_list.getContext());
            tv.setText(city);
            tv.setTextSize(30);
            tv.setGravity(Gravity.CENTER);
            tv.setHeight(128);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(tv.getContext(), tv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            city_list.addView(tv);
        }
    }
}