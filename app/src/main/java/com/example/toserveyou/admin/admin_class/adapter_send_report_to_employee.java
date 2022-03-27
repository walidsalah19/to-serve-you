package com.example.toserveyou.admin.admin_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.employee_data;
import com.example.toserveyou.data_class.report_class;
import com.example.toserveyou.mover_report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adapter_send_report_to_employee extends RecyclerView.Adapter<adapter_send_report_to_employee.holder>  {
    ArrayList<employee_data> ArrayList;
    Context context;
    DatabaseReference database;
    public adapter_send_report_to_employee(java.util.ArrayList<employee_data> arrayList, Context context) {
        ArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_send_report,parent,false);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
         holder.name.setText(ArrayList.get(position).getEmployee_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_data_to_employee(position);
            }
        });
    }

    private void send_data_to_employee(int position) {
        database= FirebaseDatabase.getInstance().getReference();
        report_class report= new report_class(mover_report.getDate(),mover_report.getEmployee_name(),mover_report.getPhysical_address(),mover_report.getProblem_description(),
                mover_report.getProgram(),mover_report.getReport_number(),mover_report.getSection(),mover_report.getTime(),mover_report.getEmployee_id());
        database.child("accepted_report").child(ArrayList.get(position).getId()).push().setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()) {
                    Toast.makeText(context, "تم ارسال التقرير الي موظف الدعم الفني", Toast.LENGTH_SHORT).show();
                    delete_from_requests();
                }
            }
        });
    }
    private void delete_from_requests() {
        database.child("new_reports").child(mover_report.getEmployee_id()).child(mover_report.getReport_id()).removeValue();
    }
    @Override
    public int getItemCount() {
        return  ArrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder
    {
        TextView name;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.send_report_to_emloyee);
        }
    }
}
