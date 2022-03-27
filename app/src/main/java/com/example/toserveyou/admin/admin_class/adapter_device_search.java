package com.example.toserveyou.admin.admin_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.device_class;

import java.util.ArrayList;

public class adapter_device_search extends RecyclerView.Adapter<adapter_device_search.holder>{
    ArrayList<device_class> ArrayList;
    Context context;
    public adapter_device_search(ArrayList<device_class> ArrayList, Context context)
    {
        this.ArrayList=ArrayList;
        this.context=context;
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


    }

    @Override
    public int getItemCount() {
        return  ArrayList.size();
    }

    public class  holder extends RecyclerView.ViewHolder
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
