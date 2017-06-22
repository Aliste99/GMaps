package com.example.thirtyseven.gmaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity {


    TextView address, area, roomCount, description, price;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String key;
    Flat flat;
    Button viewMap;
    Double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
        Intent intent = getIntent();
        key = intent.getStringExtra("ProductIdKey");
        flat = new Flat();

        databaseReference = firebaseDatabase.getReference("Flat").child(key);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flat = dataSnapshot.getValue(Flat.class);
                address.setText(String.valueOf(flat.getAddress()));
                area.setText(String.valueOf(flat.getArea()));
                roomCount.setText(String.valueOf(flat.getRoomCount()));
                description.setText(String.valueOf(flat.getDescription()));
                price.setText(String.valueOf(flat.getPrice()));
                lat = flat.getLat();
                lng = flat.getLng();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, MapsActivityView.class);
                intent.putExtra("latMap", lat);
                intent.putExtra("lngMap", lng);
                startActivity(intent);
            }
        });
    }

    private void init() {
        address = (TextView) findViewById(R.id.adrTextView2);
        area = (TextView) findViewById(R.id.areaTextView2);
        roomCount = (TextView) findViewById(R.id.roomCountTextView2);
        description = (TextView) findViewById(R.id.descrTextView2);
        price = (TextView) findViewById(R.id.priceTextView2);

        viewMap = (Button) findViewById(R.id.showOnMap);

        firebaseDatabase = FirebaseDatabase.getInstance();
    }
}
