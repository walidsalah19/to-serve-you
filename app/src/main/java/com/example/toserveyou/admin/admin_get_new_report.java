package com.example.toserveyou.admin;

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
import com.example.toserveyou.admin.admin_class.adapter_report_recycler;
import com.example.toserveyou.data_class.send_report_statuse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class admin_get_new_report extends Fragment {
    private RecyclerView admin_report;
    private ArrayList<send_report_statuse> arrayList;
    private DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_admin_get_new_report, container, false);

        intialize_recycler(v);
        return v;
    }
    private void intialize_recycler(View v)
    {
        admin_report=v.findViewById(R.id.admin_new_report);
        admin_report.setLayoutManager(new LinearLayoutManager(getActivity()));
        getreport();
    }
    public void getreport()
    {
        arrayList=new ArrayList<>();
        database= FirebaseDatabase.getInstance().getReference();
        database.child("new_reports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()) {
                    database.child("new_reports").child(snap.getKey().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot d:snapshot.getChildren())
                            {
                                    send_report_statuse report = new send_report_statuse(d.child("date").getValue().toString(), d.child("employee_name").getValue().toString(), d.child("physical_address").getValue().toString(), d.child("problem_description").getValue().toString(),
                                            d.child("program").getValue().toString(), (Long) d.child("report_number").getValue(), d.child("section").getValue().toString()
                                            , d.child("time").getValue().toString(), d.child("employee_id").getValue().toString(),d.getKey().toString(), "???? ??????", "???? ?????? ???? ??????????????");
                                    arrayList.add(report);

                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                                Collections.sort(arrayList, Comparator.comparing(send_report_statuse::getReport_number));
                                Collections.reverse(arrayList);
                                adapter_report_recycler adapter=new adapter_report_recycler(arrayList,admin_get_new_report.this);
                                admin_report.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}