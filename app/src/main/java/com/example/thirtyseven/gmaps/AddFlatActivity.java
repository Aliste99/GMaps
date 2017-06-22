package com.example.thirtyseven.gmaps;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFlatActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText adrEdit;
    EditText areaEdit;
    EditText roomCounEdit;
    EditText descrEdit;
    EditText priceEdit;
    Button addBtn, addLatLng;
    String address, description, latStr, lngStr;
    int area, roomCount, price;
    Double lat, lng;
    private Flat flat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat);

        init();
        Intent newIntent = getIntent();
        if(newIntent.hasExtra("newlat") && newIntent.hasExtra("newlng")){
            lat = newIntent.getDoubleExtra("newlat", 0);
            lng = newIntent.getDoubleExtra("newlng", 0);
            adrEdit.setEnabled(true);
            areaEdit.setEnabled(true);
            roomCounEdit.setEnabled(true);
            descrEdit.setEnabled(true);
            priceEdit.setEnabled(true);

        }else {
            adrEdit.setEnabled(false);
            areaEdit.setEnabled(false);
            roomCounEdit.setEnabled(false);
            descrEdit.setEnabled(false);
            priceEdit.setEnabled(false);
        }


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adrEdit.getText() != null) {
                    address = adrEdit.getText().toString();
                    flat.setAddress(address);
                } else {
                    Toast.makeText(AddFlatActivity.this, "Заполните все поля!1!!", Toast.LENGTH_SHORT).show();
                }

                if (areaEdit.getText() != null) {
                    area = Integer.parseInt(areaEdit.getText().toString());
                    flat.setArea(area);
                } else {
                    Toast.makeText(AddFlatActivity.this, "Заполните все поля!1!!", Toast.LENGTH_SHORT).show();
                }

                if (roomCounEdit.getText() != null) {
                    roomCount = Integer.parseInt(roomCounEdit.getText().toString());
                    flat.setRoomCount(roomCount);
                } else {
                    Toast.makeText(AddFlatActivity.this, "Заполните все поля!1!!", Toast.LENGTH_SHORT).show();
                }

                if (descrEdit.getText() != null) {
                    description = descrEdit.getText().toString();
                    flat.setDescription(description);
                } else {
                    Toast.makeText(AddFlatActivity.this, "Заполните все поля!1!!", Toast.LENGTH_SHORT).show();
                }

                if (priceEdit.getText() != null) {
                    price = Integer.parseInt(priceEdit.getText().toString());
                    flat.setPrice(price);
                } else {
                    Toast.makeText(AddFlatActivity.this, "Заполните все поля!1!!", Toast.LENGTH_SHORT).show();
                }
                flat.setLat(lat);
                flat.setLng(lng);

                databaseReference.push().setValue(flat);
            }
        });

        addLatLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFlatActivity.this, SecondMapsActivity.class);
                /*intent.putExtra("address", address);
                intent.putExtra("area", area);
                intent.putExtra("roomCount", roomCount);
                intent.putExtra("description", description);
                intent.putExtra("price", price);*/
                finish();
                startActivity(intent);

            }
        });

    }

    private void init() {
        adrEdit = (EditText) findViewById(R.id.adrEditText);
        areaEdit = (EditText) findViewById(R.id.areaEditText);
        roomCounEdit = (EditText) findViewById(R.id.roomCountEditText);
        descrEdit = (EditText) findViewById(R.id.descriptionEditText);
        priceEdit = (EditText) findViewById(R.id.priceEditText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Flat");

        addBtn = (Button) findViewById(R.id.addButton);
        addLatLng = (Button) findViewById(R.id.addLatLng);
        flat = new Flat();

    }
}
