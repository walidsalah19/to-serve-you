package com.example.toserveyou.manager;

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

import com.example.toserveyou.data_class.send_report_statuse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class manager_reports extends Fragment {
    private RecyclerView admin_report;
    private ArrayList<send_report_statuse> arrayList;
    private DatabaseReference database;
    private FirebaseAuth auth;
    String user_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manager_reports, container, false);


        intialize_recycler(v);
        getreport();
        return v;
    }
    private void intialize_recycler(View v)
    {
        admin_report=v.findViewById(R.id.manager_report);
        admin_report.setLayoutManager(new LinearLayoutManager(getActivity()));
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance().getReference();
        arrayList=new ArrayList<>();

    }
    public void getreport()
    {
       user_id=auth.getCurrentUser().getUid().toString();
        database.child("reports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        if (snap.getKey().equals(user_id)) {
                            database.child("reports").child(user_id).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot d : snapshot.getChildren()) {
                                        if (d.child("statuse").exists()) {
                                            send_report_statuse report = new send_report_statuse(d.child("date").getValue().toString(), d.child("employee_name").getValue().toString(), d.child("physical_address").getValue().toString(), d.child("problem_description").getValue().toString(),
                                                    d.child("program").getValue().toString(), (Long) d.child("report_number").getValue(), d.child("section").getValue().toString()
                                                    , d.child("time").getValue().toString(), user_id,"", d.child("statuse").getValue().toString(), d.child("answer").getValue().toString());
                                            arrayList.add(report);
                                        }
                                    }
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
                                        Collections.sort(arrayList, Comparator.comparing(send_report_statuse::getReport_number));
                                        Collections.reverse(arrayList);
                                        manager_adapter adapter = new manager_adapter(arrayList, manager_reports.this);
                                        admin_report.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}