package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> cities = CityData.getAllCities();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.city_list);
        ArrayAdapter<String> ca = new ArrayAdapter<String>(this, R.layout.city, R.id.city_name, cities);
        listView.setAdapter(ca);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = ca.getItem(position);
                Toast.makeText(MainActivity.this, city, Toast.LENGTH_SHORT).show();
            }
        });
    }
}