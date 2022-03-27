package com.example.toserveyou.it_employee.it_class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.device_class;
import com.example.toserveyou.it_employee.maintanance_device;

public class it_device_adapter extends RecyclerView.Adapter<it_device_adapter.holder> {
    java.util.ArrayList<device_class> ArrayList;
    Fragment fragment;
    public it_device_adapter(java.util.ArrayList<device_class> arrayList, Fragment fragment) {
        ArrayList = arrayList;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_search_device,parent,false);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.name.setText(ArrayList.get(position).getPhysical_address());
        holder.program.setText(ArrayList.get(position).getProgram());
        holder.pay.setText(ArrayList.get(position).getPay());
        holder.notes.setText(ArrayList.get(position).getNotes());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("address",ArrayList.get(position).getPhysical_address());
                b.putString("program",ArrayList.get(position).getProgram());
                b.putString("pay",ArrayList.get(position).getPay());
                b.putString("notes",ArrayList.get(position).getNotes());
                maintanance_device m=new maintanance_device();
                m.setArguments(b);
                fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.it_framelayout,m).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder
    {
        TextView name,program,pay,notes;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.admin_search_device_address);
            program=itemView.findViewById(R.id.admin_search_device_program);
            pay=itemView.findViewById(R.id.admin_search_device_Exchange);
            notes=itemView.findViewById(R.id.admin_search_device_notes);
        }
    }
}
