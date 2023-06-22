package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class login extends AppCompatActivity {
    String setpass="";
    String En,enrol,pass;
    final int[] flag1 = {0};
    DatabaseReference databaseReference;
    EditText Password, Enrolmentno;
    public void login(View view){

        enrol = Enrolmentno.getText().toString();
        En=enrol;
        enrol = "login_"+enrol;
        pass = Password.getText().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference(enrol);
        retrieveData(enrol);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
    public void signup(View view){
        Intent intent3 = new Intent(this,signup.class);
        startActivity(intent3);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);
    }
    public void farzi(){
        Intent intenthomefromlogin = new Intent(this, home.class);
        startActivity(intenthomefromlogin);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        Password = (EditText) findViewById(R.id.Password);
        Enrolmentno = (EditText) findViewById(R.id.Enrolmentno);
//        Toast.makeText(this, GlobalVariables.tempData[7][3], Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();
        if(GlobalVariables.flaty==1){
//            Toast.makeText(this, "HI@", Toast.LENGTH_SHORT).show();
            GlobalVariables.flaty=0;
            for(int i=0;i<8;i++){
                GlobalVariables.tempData[i][0]=GlobalVariables.tempData[i][1];
                GlobalVariables.tempData[i][1]=GlobalVariables.tempData[i][2];
                GlobalVariables.tempData[i][2]=GlobalVariables.tempData[i][3];
                GlobalVariables.tempData[i][3]="00000";
            }
            WriteAllData();
        }
    }

    public void leftover(){
        String temp="";
        String data=GlobalVariables.list;
        for(int i=1;i<GlobalVariables.list.length();i++){
            if(data.charAt(i)!='_'){
                temp=temp+data.charAt(i);
            }
            else{
                String temp1=temp;
                temp="";
                retrievefac(temp1, "1");
                retrievefac(temp1, "2");
            }
        }
    }

    public void retrievefac(String username, String x){
//        Toast.makeText(this, "BT", Toast.LENGTH_SHORT).show();
        String us="user_"+username+"_"+x;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(us);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String facdetail = String.valueOf(task.getResult().getValue());
                        if(!facdetail.equals("_")){
                            char c1=facdetail.charAt(facdetail.length()-1);
                            char c2=facdetail.charAt(facdetail.length()-5);
                            if(c1!='1'){
                                if(c1=='2'){
                                    facdetail=facdetail.substring(0, facdetail.length()-5)+"0_"+facdetail.substring(facdetail.length()-3, facdetail.length()-1)+"1";
                                }
                                else if(c1=='3'){
                                    facdetail=facdetail.substring(0, facdetail.length()-5)+"1_"+facdetail.substring(facdetail.length()-3, facdetail.length()-1)+"2";
                                }
                                else if(c1=='4'){
                                    facdetail=facdetail.substring(0, facdetail.length()-5)+"2_"+facdetail.substring(facdetail.length()-3, facdetail.length()-1)+"3";
                                }
                            }
                            else{
                                facdetail="_";
                            }
                        }
                        reference.setValue(facdetail);
                        Log.d("TESTINGXXX", facdetail);
                    }
                    else{
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void WriteAllData(){
        leftover();
        WriteData("cricket1_0", 0, 0);
        WriteData("cricket1_1", 0, 1);
        WriteData("cricket1_2", 0, 2);
        WriteData("cricket1_3", 0, 3);
        WriteData("cricket2_0", 1, 0);
        WriteData("cricket2_1", 1, 1);
        WriteData("cricket2_2", 1, 2);
        WriteData("cricket2_3", 1, 3);
        WriteData("football1_0", 2, 0);
        WriteData("football1_1", 2, 1);
        WriteData("football1_2", 2, 2);
        WriteData("football1_3", 2, 3);
        WriteData("football2_0", 3, 0);
        WriteData("football2_1", 3, 1);
        WriteData("football2_2", 3, 2);
        WriteData("football2_3", 3, 3);
        WriteData("badminton1_0", 4, 0);
        WriteData("badminton1_1", 4, 1);
        WriteData("badminton1_2", 4, 2);
        WriteData("badminton1_3", 4, 3);
        WriteData("badminton2_0", 5, 0);
        WriteData("badminton2_1", 5, 1);
        WriteData("badminton2_2", 5, 2);
        WriteData("badminton2_3", 5, 3);
        WriteData("tennis1_0", 6, 0);
        WriteData("tennis1_1", 6, 1);
        WriteData("tennis1_2", 6, 2);
        WriteData("tennis1_3", 6, 3);
        WriteData("tennis2_0", 7, 0);
        WriteData("tennis2_1", 7, 1);
        WriteData("tennis2_2", 7, 2);
        WriteData("tennis2_3", 7, 3);
        writeTime();
    }

    public void WriteData(String username, int x, int y){
        DatabaseReference writeReference = FirebaseDatabase.getInstance().getReference(username);
        writeReference.setValue(GlobalVariables.tempData[x][y]);
    }

    public void writeTime(){
        String date;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(("d"));
        date = dateFormat.format(calendar.getTime());
        DatabaseReference writeReference = FirebaseDatabase.getInstance().getReference("date");
        writeReference.setValue(date);
    }

    public void retrieveData(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        setpass = String.valueOf(task.getResult().getValue());
                        if(pass.equals(setpass)){
                            GlobalVariables.ENum=En;
                            flag1[0] = 1;

                            Toast.makeText(login.this, "Welcome!", Toast.LENGTH_SHORT).show();
                            farzi();
                        }
                        else{
                            Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}