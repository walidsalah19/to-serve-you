package com.example.toserveyou.it_employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.toserveyou.R;
import com.example.toserveyou.mover_report;


public class show_report extends Fragment {

private Button button_solve;
    private Button accept,reject;
    private TextView employee_name,section, physical_address,program,date,time,problem_description,report_number ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_report, container, false);
        solve_report_method(v);
        intialization(v);
        return v;
    }
    private void solve_report_method(View v)
    {
        button_solve=v.findViewById(R.id.it_solve_report);
        button_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.it_framelayout,new send_report_solve()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    private void intialization(View v)
    {
        date=v.findViewById(R.id.it_send_report_date);
        employee_name=v.findViewById(R.id.it_send_report_name);
        section=v.findViewById(R.id.it_send_report_section);
        time=v.findViewById(R.id.it_send_report_time);
        physical_address=v.findViewById(R.id.it_send_report_address);
        program=v.findViewById(R.id.it_send_report_program);
        problem_description=v.findViewById(R.id.it_send_report_discriptions);
        report_number=v.findViewById(R.id.it_send_report_number);
        date.setText(mover_report.getDate());
        employee_name.setText(mover_report.getEmployee_name());
        section.setText(mover_report.getSection());
        time.setText(mover_report.getTime());
        physical_address.setText(mover_report.getPhysical_address());
        program.setText(mover_report.getProgram());
        problem_description.setText(mover_report.getProblem_description());
        report_number.setText(mover_report.getReport_number()+"");

    }

}