package com.example.toserveyou.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.toserveyou.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class add_employee extends Fragment {

    private EditText username,section,email,password;
    private Button create_employee;
    private String employee_name,employee_section,employee_email,employee_password;
    private Spinner spinner;
    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth auth ;
    private DatabaseReference database;
    private CheckBox show_password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_employee, container, false);
        spinner(v);
        intialization_tool(v);
        create_employee_method(v);
        firebase_tools();
        show_password_method(v);
        return v;
    }
    private void firebase_tools()
    {
        database=FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();
    }

    private void create_employee_method(View v) {
        create_employee=v.findViewById(R.id.admin_add_employee);
        create_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_data();
            }
        });
    }
    private void show_password_method(View v) {
        show_password=v.findViewById(R.id.show_password_emp);
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (show_password.isChecked()) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });
    }

    private void check_data() {
        employee_name=username.getText().toString();
        employee_section=section.getText().toString();
        employee_email=email.getText().toString();
        employee_password=password.getText().toString();
        if(TextUtils.isEmpty(employee_name))
        {
            username.setError("من فضلك ادخل اسم المستخدم ");
        }
       else if(spinner.getSelectedItem().toString().equals("نوع الوظيفه"))
        {
            Toast.makeText(getActivity(), "من فضلك اختر نوع الوظيفه", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(employee_section))
        {
            section.setError("من فضلك ادخل القسم ");
        }
        else if(TextUtils.isEmpty(employee_email)||!employee_email.matches(emailpattern))
        {
            email.setError("البريد الالكتروني يجب ان يحتوي @ مثل employee@gmail.com");
        }
        else if(employee_password.length()<8)
        {
            password.setError("كلمة المرور يجب ان كون اكبر من 8  ");
        }
        else {
            create_employee_account();
        }

    }

    private void create_employee_account() {
        auth.createUserWithEmailAndPassword(employee_email, employee_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    add_to_data(task.getResult().getUser().getUid().toString());
                }
                else{
                    Toast.makeText(add_employee.this.getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void add_to_data(String user_id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("employee_name",employee_name);
        hashMap.put("section",employee_section);
        hashMap.put("employee_id",user_id);

        if(spinner.getSelectedItem().toString().equals("تقني"))
        {
            database_reference("it_employee",hashMap,user_id);
        }
        else if(spinner.getSelectedItem().toString().equals("اداري"))
        {

            database_reference("manager",hashMap,user_id);
        }
    }
    private void database_reference(String s,HashMap<String, String> hashMap,String user_id)
    {
        database.child(s).child(user_id).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()) {
                    Toast.makeText(getActivity(), "تم اضافه موظف جديد", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    section.setText("");
                    email.setText("");
                    password.setText("");
                    //spinner.setSelection(0);
                }
                else {
                    Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intialization_tool(View v)
    {
        username=v.findViewById(R.id.username_edittext);
        section=v.findViewById(R.id.admin_section_edittext);
        email=v.findViewById(R.id.employee_email_edittext);
         password=v.findViewById(R.id.employee_password_text);

    }
    private void spinner(View v)
    {
        List<String> categories = new ArrayList<String>();
        categories.add("نوع الوظيفه");
        categories.add("تقني");
        categories.add("اداري");
        spinner = (Spinner) v.findViewById(R.id.job_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, categories);
        spinner.setAdapter(dataAdapter);
    }
}