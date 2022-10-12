package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Activity extends AppCompatActivity {
    Button googlemap,supportMapFragment,supportMapFragment2,currentlocationupdate1,currentlocationupdate2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        googlemap=findViewById(R.id.googlemap_id);
        supportMapFragment=findViewById(R.id.supportMapFragment_id);
        supportMapFragment2=findViewById(R.id.supportMapFragment2_id);
        currentlocationupdate1=findViewById(R.id.currentlocationupdate_id);
        currentlocationupdate2=findViewById(R.id.currentlocationupdate2_id);

        googlemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        supportMapFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SupportMapFragment_A.class));
            }
        });
        supportMapFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SupportMapFragment_A2.class));
            }
        });
        currentlocationupdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Current_Location_Update1.class));
            }
        });
        currentlocationupdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Current_Location_Update2.class));
            }
        });
    }
}