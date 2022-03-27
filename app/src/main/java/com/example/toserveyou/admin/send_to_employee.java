package com.example.toserveyou.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toserveyou.R;

import com.example.toserveyou.admin.admin_class.adapter_send_report_to_employee;
import com.example.toserveyou.data_class.employee_data;
import com.example.toserveyou.mover_report;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class send_to_employee extends Fragment {

    private RecyclerView admin_send;
    private ArrayList<employee_data> arrayList;
    private  DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_send_to_employee, container, false);
        database_intialize();
        intialize_recycler(v);
        get_employee();
        return v;
    }
    private void intialize_recycler(View v)
    {
        admin_send=(RecyclerView)v.findViewById(R.id.admin_send_report_employee);
        admin_send.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void database_intialize()
    {
        database= FirebaseDatabase.getInstance().getReference();
    }
    private void get_employee()
    {
        arrayList=new ArrayList<>();
        database.child("it_employee").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for(DataSnapshot snap:snapshot.getChildren())
                 {
                     employee_data data=new employee_data(snap.child("employee_name").getValue().toString(),snap.child("section").getValue().toString()
                             ,snap.child("employee_id").getValue().toString());
                     arrayList.add(data);
                 }
                adapter_send_report_to_employee adapter=new adapter_send_report_to_employee(arrayList,getContext());
                admin_send.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}