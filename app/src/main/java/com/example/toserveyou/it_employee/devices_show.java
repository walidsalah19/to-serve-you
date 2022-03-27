package com.example.toserveyou.it_employee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.device_class;
import com.example.toserveyou.it_employee.it_class.it_device_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class devices_show extends Fragment {

    private RecyclerView it_device;
    private ArrayList<device_class> arrayList;
    private FloatingActionButton add_device;
    private DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_device_managment, container, false);
        firebase_tools();
        intialize_recycler(v);
        add_device_method(v);
        get_device_fromdatabase();
        return v;
    }
    private void intialize_recycler(View v)
    {
        it_device=v.findViewById(R.id.it_device);
        it_device.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList=new ArrayList<>();
    }
    private void firebase_tools()
    {
        database= FirebaseDatabase.getInstance().getReference();
    }
    private void add_device_method(View v)
    {
        add_device=v.findViewById(R.id.it_add_device);
        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.it_framelayout,new add_device()).addToBackStack(null).commit();

            }
        });
    }
    private void get_device_fromdatabase() {
        database.child("devices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {

                        device_class device_class = new device_class(snap.child("physical_address").getValue().toString(), snap.child("program").getValue().toString(),
                                snap.child("pay").getValue().toString(), snap.child("notes").getValue().toString());
                        arrayList.add(device_class);
                        it_device_adapter adapter = new it_device_adapter(arrayList, devices_show.this);
                        it_device.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}