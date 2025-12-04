package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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
            View view = getLayoutInflater().inflate(R.layout.city, city_list, false);
            TextView tv = view.findViewById(R.id.city_name);
            tv.setText(city);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(tv.getContext(), tv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            city_list.addView(view);
        }
    }
}