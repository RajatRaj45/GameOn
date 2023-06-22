package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    String currTime, date, fac;
    public void directly(View view){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        retrieveData("date");
    }


    public void retrieveData(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        currTime = String.valueOf(task.getResult().getValue());
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat dateFormat = new SimpleDateFormat(("d"));
                        date = dateFormat.format(calendar.getTime());

                        if(!currTime.equals(date)){
                            retrieveALLdata();
                            GlobalVariables.flaty=1;
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void retrieveALLdata(){
        retrieveData1("cricket1_0", 0, 0);
        retrieveData1("cricket1_1", 0, 1);
        retrieveData1("cricket1_2", 0, 2);
        retrieveData1("cricket1_3", 0, 3);
        retrieveData1("cricket2_0", 1, 0);
        retrieveData1("cricket2_1", 1, 1);
        retrieveData1("cricket2_2", 1, 2);
        retrieveData1("cricket2_3", 1, 3);
        retrieveData1("football1_0", 2, 0);
        retrieveData1("football1_1", 2, 1);
        retrieveData1("football1_2", 2, 2);
        retrieveData1("football1_3", 2, 3);
        retrieveData1("football2_0", 3, 0);
        retrieveData1("football2_1", 3, 1);
        retrieveData1("football2_2", 3, 2);
        retrieveData1("football2_3", 3, 3);
        retrieveData1("badminton1_0", 4, 0);
        retrieveData1("badminton1_1", 4, 1);
        retrieveData1("badminton1_2", 4, 2);
        retrieveData1("badminton1_3", 4, 3);
        retrieveData1("badminton2_0", 5, 0);
        retrieveData1("badminton2_1", 5, 1);
        retrieveData1("badminton2_2", 5, 2);
        retrieveData1("badminton2_3", 5, 3);
        retrieveData1("tennis1_0", 6, 0);
        retrieveData1("tennis1_1", 6, 1);
        retrieveData1("tennis1_2", 6, 2);
        retrieveData1("tennis1_3", 6, 3);
        retrieveData1("tennis2_0", 7, 0);
        retrieveData1("tennis2_1", 7, 1);
        retrieveData1("tennis2_2", 7, 2);
        retrieveData1("tennis2_3", 7, 3);
        retrievelist("listenrol");
    }

    public void retrieveData1(String username, int x, int y){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        GlobalVariables.tempData[x][y] = String.valueOf(task.getResult().getValue());
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void retrievelist(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        GlobalVariables.list = String.valueOf(task.getResult().getValue());
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}