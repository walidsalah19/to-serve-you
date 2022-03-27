package com.example.toserveyou.it_employee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.device_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class maintanance_device extends Fragment {

    private EditText name,notes;
    private String  string_name,string_program,string_pay,string_notes;
    private Spinner program,pay;
    private Button update;
    private DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_manage_device, container, false);
        get_translated_data();
        intialization_tool(v);
        spinner_program(v);
        spinner_mony(v);
        update_method(v);
        return v;
    }
    private void  get_translated_data()
    {
        Bundle b=this.getArguments();
        string_name=b.getString("address");
        string_program=b.getString("program");
        string_pay=b.getString("pay");
        string_notes=b.getString("notes");
    }
    private void intialization_tool(View v)
    {
        name=v.findViewById(R.id.it_maintanance_address);
        notes=v.findViewById(R.id.it_maintanance_notes);
        name.setText(string_name);
        notes.setText(string_notes);


    }
    private void spinner_program(View v)
    {
        List<String> categories = new ArrayList<String>();
        categories.add("يوجد");
        categories.add("لايوجد");

         program = (Spinner) v.findViewById(R.id.it_maintanance_program);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categories);
        program.setAdapter(dataAdapter);
        if(string_program.equals("يوجد"))
        {
            program.setSelection(0);
        }
        else
        {
            program.setSelection(1);
        }


    }
    private void spinner_mony(View v)
    {
        List<String> categories = new ArrayList<String>();
        categories.add("خاص");
        categories.add("من المؤسسه");

        pay = (Spinner) v.findViewById(R.id.it_maintanance_pay);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categories);
        pay.setAdapter(dataAdapter);
        if(string_program.equals("خاص"))
        {
            pay.setSelection(0);
        }
        else
        {
            pay.setSelection(1);
        }
    }
    private void update_method(View v)
    {
        update=v.findViewById(R.id.it_update_ddvice);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    name.setError("من فضلك ادخل العنوان الفعلي للجهاز");
                }
               else if(TextUtils.isEmpty(notes.getText().toString()))
                {
                    name.setError("من فضلك ادخل ملاحظات علي لجهاز");
                }
               else
                {
                    update_to_database();
                }
            }
        });
    }

    private void update_to_database() {
        database= FirebaseDatabase.getInstance().getReference();
        device_class device_class=new device_class(name.getText().toString(),program.getSelectedItem().toString(),pay.getSelectedItem().toString(),notes.getText().toString());
       String id=name.getText().toString();
        database.child("devices").child(string_name).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    database.child("devices").child(id).setValue(device_class).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete())
                            {
                                Toast.makeText(getActivity(), "تم تعديل بيانات الجهاز", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}