package com.example.toserveyou.it_employee.it_class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.report_class;
import com.example.toserveyou.it_employee.show_report;
import com.example.toserveyou.mover_report;

import java.util.ArrayList;

public class employee_report extends RecyclerView.Adapter<employee_report.helper> {
    ArrayList<report_class> ArrayList;
    ArrayList<String> report_id;
    Fragment main;
    public employee_report(ArrayList<report_class> arrayList ,ArrayList<String> report_id, Fragment main)
    {
        this.ArrayList=arrayList;
        this.report_id=report_id;
        this.main=main;
    }

    @NonNull
    @Override
    public helper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_report_recycler,parent,false);
        return new  helper(v);
    }

    @Override
    public void onBindViewHolder(@NonNull helper holder, int position) {
        holder.name.setText(ArrayList.get(position).getReport_number()+"");
        holder.date.setText(ArrayList.get(position).getDate());
        holder.time.setText(ArrayList.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mover_report.setDate(ArrayList.get(position).getDate());
                mover_report.setEmployee_name(ArrayList.get(position).getEmployee_name());
                mover_report.setPhysical_address(ArrayList.get(position).getPhysical_address());
                mover_report.setProblem_description(ArrayList.get(position).getProblem_description());
                mover_report.setProgram(ArrayList.get(position).getProgram());
                mover_report.setSection(ArrayList.get(position).getSection());
                mover_report.setTime(ArrayList.get(position).getTime());
                mover_report.setReport_number(ArrayList.get(position).getReport_number());
                mover_report.setEmployee_id(ArrayList.get(position).getEmployee_id());
                mover_report.setReport_id(report_id.get(position));
                main.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.it_framelayout,new show_report()).addToBackStack(null).commitAllowingStateLoss();

            }
        });

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public class helper extends RecyclerView.ViewHolder
    {
        TextView name,date,time;
        public helper(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.report_recycler_name);
            date=itemView.findViewById(R.id.report_recycler_date);
            time=itemView.findViewById(R.id.report_recycler_time);

        }
    }
}
