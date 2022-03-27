package com.example.toserveyou.manager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toserveyou.R;
import com.example.toserveyou.data_class.report_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class manager_send_report extends Fragment {
  private EditText notes,physical_address,probleme_description;
  String date ,time,employee_name,employee_secsion,program;
  long report_number;
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
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manager_send_report, container, false);
        notes_method(v);
        firebase_tools();
        intialize(v);
        get_employee_data();
        get_numberofreports();
        add_method(v);
        return v;
    }
    private void firebase_tools()
    {
        database= FirebaseDatabase.getInstance().getReference();
        auth= FirebaseAuth.getInstance();
    }
    private void intialize(View v) {
        physical_address=v.findViewById(R.id.manager_report_device_number);
        probleme_description=v.findViewById(R.id.manager_report_describe);
    }
    private void add_method(View v)
    {
        add=v.findViewById(R.id.manager_send_report);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(physical_address.getText()))
                {
                    physical_address.setError("من فضلك أضف العنوان الفعلي ");
                }
                else if(TextUtils.isEmpty(physical_address.getText()))
                {
                    probleme_description.setError("من فضلك أضف وصف المشكلة  ");
                }
                else
                {
                    get_program();
                    get_date_time();

                }
            }
        });
    }

    private void add_to_database() {
        report_class report=new report_class(date,employee_name,physical_address.getText().toString()
                ,probleme_description.getText().toString(),program,report_number,employee_secsion,time,auth.getCurrentUser().getUid());
        database.child("new_reports").child(auth.getCurrentUser().getUid().toString()).push().setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete())
                {
                    Toast.makeText(getActivity(), "تم اضافه تقرير جديد", Toast.LENGTH_SHORT).show();
                    physical_address.setText("");
                    probleme_description.setText("");
                    database.child("report_number").child("number").setValue(report_number);

                }

            }
        });
    }

    private void notes_method(View v)
    {
      notes=v.findViewById(R.id.notes);
      notes.setText("１ -من قائمة ابدأ ادخل الى الإعدادات \n" +
              "２ -اضغط على خانة  الشبكة والانترنت \n" +
              "３ -اضغط على \" عرض خصائص الأجهزه والاتصالات \"\n" +
              "４ -سيظهر لك العنوان الفعلي (MAC)\n" +
              "５ -كتابة العنوان الذي ظهر لك في خانة العنوان الفعلي");
    }
    private void get_numberofreports()
    {
        database.child("report_number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    report_number = Integer.parseInt(snapshot.child("number").getValue().toString()) + 1;
                }
                else
                {
                    report_number=1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    boolean found=false;
    private void get_program()
    {
        String address=physical_address.getText().toString();
            database.child("devices").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for(DataSnapshot d:snapshot.getChildren())
                   {
                       if (d.getKey().equals(address)) {
                           program = d.child("program").getValue().toString();
                           add_to_database();
                           found=true;
                       }
                   }
                   if (!found)
                   {
                       physical_address.setError("هذا الجهاز غير موجود");
                   }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
    }
    private void get_employee_data()
    {
        String user_id=auth.getCurrentUser().getUid();
        database.child("manager").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()) {
                    if(data.getKey().equals(user_id)) {
                        employee_name = data.child("employee_name").getValue().toString();
                        employee_secsion = data.child("section").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void get_date_time()
    {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
        date=currentDate.format(calender.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("hh.mm");
        time=currenttime.format(calender.getTime());
    }
}