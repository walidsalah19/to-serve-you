package com.example.toserveyou.it_employee;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.toserveyou.R;
import com.example.toserveyou.data_class.report_class;
import com.example.toserveyou.it_employee.it_class.employee_report;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class it_employee_report extends Fragment {
    private RecyclerView admin_report;
    private ArrayList<report_class> arrayList;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private ArrayList<String> report_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_it_employee_report, container, false);
        intialize_recycler(v);
        getreport();
        return v;
    }
    private void intialize_recycler(View v)
    {
        admin_report=v.findViewById(R.id.it_employee_report);
        admin_report.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList=new ArrayList<>();

    }
    public void getreport()
    {
        database= FirebaseDatabase.getInstance().getReference();
        auth= FirebaseAuth.getInstance();
        String id=auth.getCurrentUser().getUid().toString();
        arrayList=new ArrayList<>();
        report_id=new ArrayList<>();
                        database.child("accepted_report").child(id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot d : snapshot.getChildren()) {
                                    report_class report = new report_class(d.child("date").getValue().toString(), d.child("employee_name").getValue().toString(), d.child("physical_address").getValue().toString(), d.child("problem_description").getValue().toString(),
                                            d.child("program").getValue().toString(), (Long) d.child("report_number").getValue(), d.child("section").getValue().toString()
                                            , d.child("time").getValue().toString(),d.child("employee_id").getValue().toString());
                                    arrayList.add(report);
                                    report_id.add(d.getKey().toString());
                                }
                                    Collections.reverse(arrayList);
                                    Collections.reverse(report_id);
                                    employee_report adapter=new employee_report(arrayList,report_id,it_employee_report.this);
                                    admin_report.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


    }
}