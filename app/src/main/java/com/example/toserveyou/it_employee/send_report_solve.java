package com.example.toserveyou.it_employee;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class send_report_solve extends Fragment {
    private EditText answer;
    private Button send;
    private DatabaseReference database;
    private FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_send_report_solve, container, false);
        send_method(v);
        return v;
    }
    private void send_method(View v)
    {
        answer=v.findViewById(R.id.it_employee_problem_solve);
        send=v.findViewById(R.id.it_send_problem_solve);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(answer.getText().toString()))
                {
                    answer.setError("من فضلك ادخل حل المشكلة");
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
                mover_report.getProgram(),mover_report.getReport_number(),mover_report.getSection(),mover_report.getTime(),mover_report.getEmployee_id(),"","answered",answer.getText().toString());
        database.child("reports").child(mover_report.getEmployee_id()).push().setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete())
                {
                    Toast.makeText(getActivity(), "تم ارسال حل الطلب بنجاح", Toast.LENGTH_SHORT).show();
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
        auth=FirebaseAuth.getInstance();
        database.child("accepted_report").child(auth.getCurrentUser().getUid().toString()).child(mover_report.getReport_id()).removeValue();
    }

}