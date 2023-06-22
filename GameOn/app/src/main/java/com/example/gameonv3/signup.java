package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    EditText Password1,Password2,Enrolno1;
    String enrolexist="";
    String pass1,pass2,enrol,enrol_st;
    DatabaseReference databaseReference;
    String En;

    public void gotohome(View view) throws InterruptedException {
        enrol = Enrolno1.getText().toString();
        En=enrol;
        enrol_st = "login_" + enrol;
        pass1 = Password1.getText().toString();
        pass2 = Password2.getText().toString();
        retrieveData1(enrol);
    }

    public void farzi(){
        Intent intenthomefromsignup = new Intent(this, home.class);
        startActivity(intenthomefromsignup);
    }
    public void retrieveData1(String username){
        String username_st = "login_"+username;
        String username_fac1 = "user_"+username+"_1";
        String username_fac2 = "user_"+username+"_2";
        String flaguser = "user_"+username+"_flag";
        String feedbackuser = "user_"+username+"_feedback";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username_st);
        DatabaseReference reference_fac1 = FirebaseDatabase.getInstance().getReference(username_fac1);
        DatabaseReference reference_fac2 = FirebaseDatabase.getInstance().getReference(username_fac2);
        DatabaseReference referenceflag = FirebaseDatabase.getInstance().getReference(flaguser);
        DatabaseReference referencefeedback = FirebaseDatabase.getInstance().getReference(feedbackuser);
        DatabaseReference referencelist = FirebaseDatabase.getInstance().getReference("listenrol");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast T = Toast.makeText(getApplicationContext(), "Enrolment number already exists!!", Toast.LENGTH_SHORT);
                        T.show();
                    }
                    else {
                        if (pass1.equals(pass2)) {
                            databaseReference = FirebaseDatabase.getInstance().getReference(enrol_st);
                            databaseReference.setValue(pass1);
                            GlobalVariables.ENum=En;
                            reference_fac1.setValue("_");
                            reference_fac2.setValue("_");
                            referenceflag.setValue("NO");
                            referencefeedback.setValue("_&_");
                            retrievelist(username);
                        } else {
                            Toast T = Toast.makeText(getApplicationContext(), "Both passwords don't match!", Toast.LENGTH_SHORT);
                            T.show();
                        }
                    }
                }
            }
        });
    }

    public void retrievelist(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("listenrol");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String setpass = String.valueOf(task.getResult().getValue());
                        setpass = setpass + username + "_";
                        reference.setValue(setpass);
                        farzi();
                    }
                }
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        Password1 = (EditText) findViewById(R.id.Password1);
        Password2 = (EditText) findViewById(R.id.Password2);
        Enrolno1 = (EditText) findViewById(R.id.Enrolno1);
    }
}