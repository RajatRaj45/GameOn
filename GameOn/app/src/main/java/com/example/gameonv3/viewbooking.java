package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class viewbooking extends AppCompatActivity {
    TextView Fac1,Fac2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_viewbooking);
        Fac1 = findViewById(R.id.Fac1);
        Fac2 = findViewById(R.id.Fac2);
        if(!GlobalVariables.booking1.equals("_")){
            Fac1.setText(parse(GlobalVariables.booking1));
        }
        else{
            Fac1.setText("NO BOOKING MADE");
        }
        if(!GlobalVariables.booking2.equals("_")){
            Fac2.setText(parse(GlobalVariables.booking2));
        }
        else{
            Fac2.setText("NO BOOKING MADE");
        }
    }

    public String parse(String s){
        String date;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(("d MMM"));
        date = dateFormat.format(calendar.getTime());
        String details = s.substring(s.length()-5);
        String facility = s.substring(0,s.length()-6);
        int slot_time;
        String time_="";
        int dateoffset;
        dateoffset = Integer.parseInt(Character.toString(details.charAt(0)));
        slot_time = Integer.parseInt(Character.toString(details.charAt(2)));
        if(slot_time==0){
            time_ = "6am-7am";
        }
        if(slot_time==1){
            time_ = "7am-8am";
        }
        if(slot_time==2){
            time_ = "5pm-6pm";
        }
        if(slot_time==3){
            time_ = "6pm-7pm";
        }
        if(slot_time==4){
            time_ = "7pm-8pm";
        }
        if(facility.equals("cricket1")){
            facility = "LBS Nets, ";
        }
        if(facility.equals("cricket2")){
            facility = "LBS Main Pitch, ";
        }
        if(facility.equals("football1")){
            facility = "Major Dhyan Chand St. Field 1, ";
        }
        if(facility.equals("football2")){
            facility = "Major Dhyan Chand St. Field 2, ";
        }
        if(facility.equals("badminton1")){
            facility = "SAC Badminton Court, ";
        }
        if(facility.equals("badminton2")){
            facility = "Indoor Badminton Court, ";
        }
        if(facility.equals("tennis1")){
            facility = "Tennis Court 1, ";
        }
        if(facility.equals("tennis2")){
            facility = "Tennis Court 2, ";
        }
        calendar.add(Calendar.DATE,dateoffset);
        String s1 = facility + " " + dateFormat.format(calendar.getTime()) + " " + time_;
        return s1;

    }

    public void deletebooking1(View view){
        if(GlobalVariables.booking1.equals("_")){
            Toast.makeText(this, "No Booking made!", Toast.LENGTH_SHORT).show();
        }
        else{
            changeduetodeletion(GlobalVariables.booking1,1);
        }
    }

    public void deletebooking2(View view){
        if(GlobalVariables.booking2.equals("_")){
            Toast.makeText(this, "No Booking made!", Toast.LENGTH_SHORT).show();
        }
        else{
            changeduetodeletion(GlobalVariables.booking2,2);
        }
    }

    public void farzi45(){
        Intent intent123 = new Intent(this,home.class);
        startActivity(intent123);
        overridePendingTransition(R.anim.slidefromleft,R.anim.slidetoright);

    }

    public void changeduetodeletion(String x, int num){
        String fac,userfac;
        fac = x.substring(0,x.length()-4);
        userfac = "user_"+GlobalVariables.ENum+"_"+Integer.toString(num);

        int col = Character.getNumericValue(x.charAt(x.length()-3));
        DatabaseReference referencefacility = FirebaseDatabase.getInstance().getReference(fac);
        DatabaseReference referenceuserfac = FirebaseDatabase.getInstance().getReference(userfac);
        referencefacility.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String aval = String.valueOf(task.getResult().getValue());
                        aval = aval.substring(0,col)+"0"+aval.substring(col+1);
                        referencefacility.setValue(aval);
                        referenceuserfac.setValue("_");
                        if(num==1){
                            GlobalVariables.booking1 = "_";
                        }
                        else{
                            GlobalVariables.booking2 = "_";
                        }
                        Toast.makeText(viewbooking.this, "Booking Cancelled Succesfully!", Toast.LENGTH_SHORT).show();
                        farzi45();
                    }
                }
            }
            });
    }
}

