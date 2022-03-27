package com.example.toserveyou.admin.admin_class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toserveyou.R;
import com.example.toserveyou.admin.send_to_it;
import com.example.toserveyou.data_class.report_class;
import com.example.toserveyou.data_class.send_report_statuse;
import com.example.toserveyou.mover_report;

import java.util.ArrayList;

public class adapter_report_recycler extends RecyclerView.Adapter<adapter_report_recycler.helper> {
    ArrayList<send_report_statuse> ArrayList;
    Fragment main;
    public adapter_report_recycler(ArrayList<send_report_statuse> arrayList , Fragment Main)
    {
     this.ArrayList=arrayList;
     this.main=Main;
    }

    @NonNull
    @Override
    public helper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_report_recycler,parent,false);
        helper h=new helper(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull helper holder, int position) {
        holder.name.setText( ArrayList.get(position).getReport_number()+" ");
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
                mover_report.setStatuse(ArrayList.get(position).getStatuse());
                mover_report.setAnswer(ArrayList.get(position).getAnswer());
                mover_report.setReport_id(ArrayList.get(position).getReport_id());
  /* Bundle b=new Bundle();
                b.putString("date",ArrayList.get(position).getDate());
                b.putString("Physical_address",ArrayList.get(position).getPhysical_address());
                b.putString("Problem_description",ArrayList.get(position).getProblem_description());
                b.putString("Program",ArrayList.get(position).getProgram());
                b.putString("section",ArrayList.get(position).getSection());
                b.putString("time",ArrayList.get(position).getTime());
                b.putString("Report_number",ArrayList.get(position).getReport_number()+"");
                b.putString("date",ArrayList.get(position).getDate());
                send_to_it it=new send_to_it();
                it.setArguments(b);*/
                main.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new send_to_it()).addToBackStack(null).commitAllowingStateLoss();

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
