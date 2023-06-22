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

public class ChangePassword extends AppCompatActivity {
    EditText oldpass, newpass, renewpass;
    String setpass,oldPass,newPass,renewPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);
        oldpass=(EditText) findViewById(R.id.oldPassTxt);
        newpass=(EditText) findViewById(R.id.newPassTxt);
        renewpass=(EditText) findViewById(R.id.RenewPassTxt);
    }

    public void changeP(View view){
        oldPass=oldpass.getText().toString();
        newPass=newpass.getText().toString();
        renewPass=renewpass.getText().toString();
        String x = "login_"+GlobalVariables.ENum;
        Log.d("TESTq",x);
        retrieveData(x);
    }
    public void farzi(){
        Intent intentchangepasstohome = new Intent(this, home.class);
        startActivity(intentchangepasstohome);
        overridePendingTransition(R.anim.slidefromleft,R.anim.slidetoright);

    }

    public void retrieveData(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        setpass = String.valueOf(task.getResult().getValue());
                        Log.d("TESTq",setpass);
                        if(setpass.equals(oldPass)){
                            if(newPass.equals(renewPass)){
                                reference.setValue(newPass);
                                Toast.makeText(ChangePassword.this,"Password changed successfully",Toast.LENGTH_SHORT).show();
                                farzi();
                            }
                            else{
                                Toast.makeText(ChangePassword.this, "The 2 entered passwords don't match!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(ChangePassword.this, "Old Password doesn't match!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(ChangePassword.this, "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}