package com.example.toserveyou.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.report_class;
import com.example.toserveyou.data_class.send_report_statuse;

import java.util.ArrayList;

public class manager_adapter extends RecyclerView.Adapter<manager_adapter.holder> {
    ArrayList<send_report_statuse> ArrayList;
Fragment fragment;
    public manager_adapter(java.util.ArrayList<send_report_statuse> arrayList,Fragment fragment) {
        this.ArrayList = arrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_report_recycler,parent,false);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.name.setText(ArrayList.get(position).getReport_number()+"");
        holder.number.setText(ArrayList.get(position).getPhysical_address());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("Physical_address",ArrayList.get(position).getPhysical_address());
                b.putString("Problem_description",ArrayList.get(position).getProblem_description());
                b.putString("Program",ArrayList.get(position).getProgram());
                b.putString("section",ArrayList.get(position).getSection());
                b.putString("employee_name",ArrayList.get(position).getEmployee_name());
                b.putString("Report_number",ArrayList.get(position).getReport_number()+"");
                b.putString("statuse",ArrayList.get(position).getStatuse());
                b.putString("answer",ArrayList.get(position).getAnswer());
                manager_report_solution m=new manager_report_solution();
                m.setArguments(b);
                fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.manager_framelayout,m).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    public class holder extends RecyclerView.ViewHolder
    {
        TextView name,number;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.m_report_recycler_name);
            number=itemView.findViewById(R.id.m_report_recycler_number);
        }
    }
}
