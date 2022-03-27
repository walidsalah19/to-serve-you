package com.example.toserveyou.manager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toserveyou.R;
import com.example.toserveyou.mover_report;


public class manager_report_solution extends Fragment {

    private TextView employee_name,section, physical_address,program,answer,problem_description,report_number,statuse ;
    private String string_employee_name,string_section, string_physical_address,string_program,string_answer
            ,string_problem_description,string_report_number,string_statuse ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manager_report_solution, container, false);
        get_send_data();
        intialization(v);
        return v;
    }
    private void get_send_data()
    {
        Bundle b=this.getArguments();
        string_employee_name=b.getString("employee_name");
        string_section=b.getString("section");
        string_physical_address=b.getString("Physical_address");
        string_program=b.getString("Program");
        string_answer=b.getString("answer");
        string_problem_description=b.getString("Problem_description");
        string_report_number=b.getString("Report_number");
        string_statuse=b.getString("statuse");
    }
    private void intialization(View v)
    {
        employee_name=v.findViewById(R.id.manager_report_employee);
        section=v.findViewById(R.id.manager_report_section);
        physical_address=v.findViewById(R.id.manager_report_address);
        program=v.findViewById(R.id.manager_report_program);
        problem_description=v.findViewById(R.id.manager_report_discription);
        report_number=v.findViewById(R.id.manager_report_number);
         answer =v.findViewById(R.id.manager_report_answer);
         statuse=v.findViewById(R.id.answered_report_statuse);


         employee_name.setText(string_employee_name);
         section.setText(string_section);
         physical_address.setText(string_physical_address);
         program.setText(string_program);
         problem_description.setText(string_problem_description);
         report_number.setText(string_report_number);
         answer.setText(string_answer);
         if(string_statuse.equals("refuse"))
         {
             statuse.setText("سبب الرفض : ");
         }
    }
}