package com.example.thirtyseven.gmaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    private ArrayAdapter adapter;
    Flat flat;
    ArrayList<Flat> listOfFlats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();
        setListeners();
        listOfFlats = new ArrayList<>();
        adapter = new CustomItemAdapter(this, R.layout.custom_item, listOfFlats);
        listView.setAdapter(adapter);

    }

    private void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, InfoActivity.class);
                intent.putExtra("ProductIdKey", listOfFlats.get(position).getIdKey());
                startActivity(intent);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                flat = dataSnapshot.getValue(Flat.class);
                flat.setIdKey(dataSnapshot.getKey());
                listOfFlats.add(flat);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //listView.append(String.valueOf(proxducts.getName()) + "\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                recreate();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        listView = (ListView) findViewById(R.id.list);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Flat");
    }
}
