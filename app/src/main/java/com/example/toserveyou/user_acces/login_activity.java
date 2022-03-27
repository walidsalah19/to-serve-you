package com.example.toserveyou.user_acces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toserveyou.R;
import com.example.toserveyou.admin.admin_main;
import com.example.toserveyou.it_employee.it_employee_main;
import com.example.toserveyou.manager.manager_main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_activity extends AppCompatActivity {
   private Button login_button;
   private EditText email,password;
   private FirebaseAuth auth;
   private DatabaseReference data;
    private CheckBox show_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        login_button=findViewById(R.id.login_button);
        email=findViewById(R.id.email_edittext_login);
        password=findViewById(R.id.email_password_text_login);
        auth=FirebaseAuth.getInstance();
        data=FirebaseDatabase.getInstance().getReference();
        sign_in_method();
        show_password_method();
    }
    private void check_data_inter()
    {
        if(email.getText().toString().equals(""))
        {
            email.setError("من فضلك ادخل البريد الالكتروني");
        }
        else if (password.getText().toString().equals(""))
        {
            password.setError("من فضلك ادخل كلمة المرور");
        }

    }

    private void sign_in_method() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_data_inter();
        auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    go_activity_main(task.getResult().getUser().getUid());
                }
                else
                {
                    Toast.makeText(login_activity.this, "حدث خطاء", Toast.LENGTH_SHORT).show();
                }
            }
        });

            }
        });
    }
    int found=0;
    private void go_activity_main(String uid) {
        if(found==0)
        {
            data.child("manager").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        for(DataSnapshot snap:snapshot.getChildren())
                        {
                            String id=snap.getKey().toString();
                            if(id.equals(uid))
                            {
                                found=1;
                                startActivity(new Intent(login_activity.this, manager_main.class));
                                finish();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        if(found==0)
        {
            data.child("it_employee").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        for(DataSnapshot snap:snapshot.getChildren())
                        {
                            String id=snap.child("employee_id").getValue().toString();
                            if(id.equals(uid))
                            {
                                found=1;
                                startActivity(new Intent(login_activity.this, it_employee_main.class));
                                finish();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        if (found==0)
        {
            startActivity(new Intent(login_activity.this, admin_main.class));
            finish();
        }
    }
    private void show_password_method() {
        show_password=findViewById(R.id.show_password_login);
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
}