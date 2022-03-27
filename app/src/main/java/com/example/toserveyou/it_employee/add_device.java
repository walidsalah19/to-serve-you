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
import android.widget.Toast;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.device_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add_device extends Fragment {

    private EditText physical_address,notes;
    private Spinner program,pay;
    private Button add;
    private FirebaseAuth auth ;
    private DatabaseReference database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_add_device, container, false);
        spinner_program(v);
        spinner_mony(v);
        firebase_tools();
        intialize(v);
        return v;
    }
    private void firebase_tools()
    {
        database= FirebaseDatabase.getInstance().getReference();
        auth= FirebaseAuth.getInstance();
    }
    private void intialize(View v)
    {
        physical_address=v.findViewById(R.id.it_add_device_address);
        notes=v.findViewById(R.id.it_add_device_notes);
        add=v.findViewById(R.id.it_add_new_ddvice);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_data();
            }
        });
    }

    private void check_data() {
        if (TextUtils.isEmpty(physical_address.getText().toString()))
        {
            physical_address.setError("من فضلك اضف العنوان الفعلي ");
        }
        else {
            add_to_database();
        }
    }
    private void add_to_database() {
        device_class dataclass=new device_class(physical_address.getText().toString(),program.getSelectedItem().toString()
                ,pay.getSelectedItem().toString(),notes.getText().toString());
        database.child("devices").child(physical_address.getText().toString()).setValue(dataclass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()) {
                    physical_address.setText("");
                    notes.setText("");
                    program.setSelection(0);
                    pay.setSelection(0);
                    Toast.makeText(getActivity(), "تمت إضافه جهاز جديد ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void spinner_program(View v)
    {
        List<String> categories = new ArrayList<String>();
        categories.add("يوجد");
        categories.add("لايوجد");

        program = (Spinner) v.findViewById(R.id.it_add_device_program);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categories);
        program.setAdapter(dataAdapter);


    }
    private void spinner_mony(View v)
    {
        List<String> categories = new ArrayList<String>();
        categories.add("خاص");
        categories.add("من المؤسسه");

        pay= (Spinner) v.findViewById(R.id.it_add_device_mony);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categories);
        pay.setAdapter(dataAdapter);
    }
}