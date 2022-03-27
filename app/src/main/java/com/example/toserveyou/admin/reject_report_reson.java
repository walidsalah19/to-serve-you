package com.example.toserveyou.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.send_report_statuse;
import com.example.toserveyou.mover_report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class reject_report_reson extends Fragment {
    private EditText reason;
    private Button send;
    private DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_reject_report_reson, container, false);
        send_method(v);

        return v;
    }
    private void send_method(View v)
    {
        reason=v.findViewById(R.id.report_reject_reason);
        send=v.findViewById(R.id.send_report_reject_reason);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(reason.getText().toString()))
                {
                    reason.setError("من فضلك ادخل سبب الرفض");
                }
                else{
                    send_to_database();
                }
            }
        });
    }

    private void send_to_database() {
        database= FirebaseDatabase.getInstance().getReference();
        send_report_statuse status =new send_report_statuse(mover_report.getDate(),mover_report.getEmployee_name(),mover_report.getPhysical_address(),mover_report.getProblem_description(),
                mover_report.getProgram(),mover_report.getReport_number(),mover_report.getSection(),mover_report.getTime(),mover_report.getEmployee_id(),mover_report.getReport_id(),"refuse",reason.getText().toString());
        database.child("reject_reports").child(mover_report.getEmployee_id()).child(mover_report.getReport_id()).setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "تم ارسال سبب رفض الطلب", Toast.LENGTH_SHORT).show();
                    delete_from_requests();
                }
                else
                {
                    Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void delete_from_requests() {
        database.child("new_reports").child(mover_report.getEmployee_id()).child(mover_report.getReport_id()).removeValue();
    }
}