package com.example.toserveyou.admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toserveyou.R;
import com.example.toserveyou.mover_report;


public class send_to_it extends Fragment {


   private Button accept,reject;
   private TextView employee_name,section, physical_address,program,date,time,problem_description,report_number,report_aswer,statuse ;
   private LinearLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_send_to_it, container, false);
       acception_method(v);
       reject_method(v);
       intialization(v);
        return v;
    }
    private void intialization(View v)
    {
        date=v.findViewById(R.id.admin_send_report_date);
        employee_name=v.findViewById(R.id.send_to_employee_name);
        section=v.findViewById(R.id.admin_send_report_section);
        time=v.findViewById(R.id.admin_send_report_time);
        physical_address=v.findViewById(R.id.admin_send_physical_address);
        program=v.findViewById(R.id.admin_send_device_program);
        problem_description=v.findViewById(R.id.admin_send_report_description);
        report_number=v.findViewById(R.id.admin_send_report_number);
        report_aswer=v.findViewById(R.id.admin_report_answer);
        statuse=v.findViewById(R.id.admin_statuse);

        layout=v.findViewById(R.id.linearLayout_send_report);

        report_aswer.setText(mover_report.getAnswer());
        date.setText(mover_report.getDate());
        employee_name.setText(mover_report.getEmployee_name());
        section.setText(mover_report.getSection());
        time.setText(mover_report.getTime());
        physical_address.setText(mover_report.getPhysical_address());
        program.setText(mover_report.getProgram());
        problem_description.setText(mover_report.getProblem_description());
        report_number.setText(mover_report.getReport_number()+"");


        String string_statuse=mover_report.getStatuse();
        if(string_statuse.equals("refuse"))
        {
            statuse.setText("سبب الرفض : ");
        }
        if(string_statuse.equals("refuse")||string_statuse.equals("answered"))
        {
            layout.setVisibility(View.INVISIBLE);
        }

    }

    private void reject_method(View v) {
        reject=v.findViewById(R.id.reject_report);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new reject_report_reson()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    private void acception_method(View v) {
        accept=v.findViewById(R.id.accept_report);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new send_to_employee()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
}