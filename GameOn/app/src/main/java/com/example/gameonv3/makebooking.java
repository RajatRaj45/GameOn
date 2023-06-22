package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class makebooking extends AppCompatActivity {
    String avl1,avl2,avl3,avl4,fac;
    TextView A1,A2,A3,A4,A5,B1,B2,B3,B4,B5,C1,C2,C3,C4,C5,D1,D2,D3,D4,D5,row1,row2,row3,row4;
    String facility1,facility2,facility3,facility4;
    String book="";
    int flag1=0;
    int flag2=0;
    int flag3=0;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makebooking);
        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        A3 = findViewById(R.id.A3);
        A4 = findViewById(R.id.A4);
        A5 = findViewById(R.id.A5);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);
        B5 = findViewById(R.id.B5);
        C1 = findViewById(R.id.C1);
        C2 = findViewById(R.id.C2);
        C3 = findViewById(R.id.C3);
        C4 = findViewById(R.id.C4);
        C5 = findViewById(R.id.C5);
        D1 = findViewById(R.id.D1);
        D2 = findViewById(R.id.D2);
        D3 = findViewById(R.id.D3);
        D4 = findViewById(R.id.D4);
        D5 = findViewById(R.id.D5);
        row1 = findViewById(R.id.textView);
        row2 = findViewById(R.id.textView2);
        row3 = findViewById(R.id.textView3);
        row4 = findViewById(R.id.textView4);
        retrieveFacility();
    }

    public void refresh(View view){
        retrieveData1(facility1);
        retrieveData2(facility2);
        retrieveData3(facility3);
        retrieveData4(facility4);
    }

    public void a1booking(View view){
        retrieveData(facility1,0, 1);
    }
    public void a2booking(View view){
        retrieveData(facility1,1, 1);
    }
    public void a3booking(View view){
        retrieveData(facility1,2, 1);
    }
    public void a4booking(View view){
        retrieveData(facility1,3, 1);
    }
    public void a5booking(View view){
        retrieveData(facility1,4, 1);
    }
    public void b1booking(View view){
        retrieveData(facility2,0, 2);
    }
    public void b2booking(View view){
        retrieveData(facility2,1, 2);
    }
    public void b3booking(View view){
        retrieveData(facility2,2, 2);
    }
    public void b4booking(View view){
        retrieveData(facility2,3, 2);
    }
    public void b5booking(View view){
        retrieveData(facility2,4, 2);
    }
    public void c1booking(View view){
        retrieveData(facility3,0, 3);
    }
    public void c2booking(View view){
        retrieveData(facility3,1, 3);
    }
    public void c3booking(View view){
        retrieveData(facility3,2, 3);
    }
    public void c4booking(View view){
        retrieveData(facility3,3, 3);
    }
    public void c5booking(View view){
        retrieveData(facility3,4, 3);
    }
    public void d1booking(View view){
        retrieveData(facility4,0, 4);
    }
    public void d2booking(View view){
        retrieveData(facility4,1, 4);
    }
    public void d3booking(View view){
        retrieveData(facility4,2,4);
    }
    public void d4booking(View view){
        retrieveData(facility4,3,4);
    }
    public void d5booking(View view){
        retrieveData(facility4,4,4);
    }

    public void retrieveData(String username, int x, int y){
        if(GlobalVariables.booking1.equals("_") || GlobalVariables.booking2.equals("_")){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
            String book1 = "user_"+GlobalVariables.ENum+"_1";
            String book2 = "user_"+GlobalVariables.ENum+"_2";
            String flaguser = "user_"+GlobalVariables.ENum+"_flag";
            DatabaseReference referencebooking1 = FirebaseDatabase.getInstance().getReference(book1);
            DatabaseReference referencebooking2 = FirebaseDatabase.getInstance().getReference(book2);
            DatabaseReference referenceflag = FirebaseDatabase.getInstance().getReference(flaguser);
            reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            avl1 = String.valueOf(task.getResult().getValue());
                            if(avl1.charAt(x)=='0'){
                                avl1 = avl1.substring(0,x) + "1" + avl1.substring(x+1);
                                reference.setValue(avl1);
                                Toast T = Toast.makeText(makebooking.this,"BOOKING COMPLETE!",Toast.LENGTH_LONG);
                                T.show();
                                retrieveData1(facility1);
                                retrieveData2(facility2);
                                retrieveData3(facility3);
                                retrieveData4(facility4);
                                if(GlobalVariables.booking1.equals("_")){
                                    String facset = username+"_"+Integer.toString(x)+"_"+Integer.toString(y);
                                    GlobalVariables.booking1 = facset;
                                    referencebooking1.setValue(facset);
                                }
                                else if(GlobalVariables.booking2.equals("_")){
                                    String facset1 = username+"_"+Integer.toString(x)+"_"+Integer.toString(y);
                                    referencebooking2.setValue(facset1);
                                    GlobalVariables.booking2 = facset1;
                                }
                                referenceflag.setValue("YES");
                                farzi();
                            }
                            else{
                                Toast.makeText(makebooking.this, "SLOT NOT EMPYTY!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(makebooking.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "ALREADY REACHED MAXIMUM LIMIT OF BOOKINGS!", Toast.LENGTH_SHORT).show();
        }
    }

    public void farzi(){
        Intent intentViewbooking = new Intent(this,home.class);
        startActivity(intentViewbooking);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void retrieveFacility(){
        String facname = "facilityname_"+GlobalVariables.ENum;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(facname);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        fac = String.valueOf(task.getResult().getValue());
                        Log.d("FACxxx",fac);
                        facility1 = fac+"_0";
                        facility2 = fac+"_1";
                        facility3 = fac+"_2";
                        facility4 = fac+"_3";
                        retrieveData1(facility1);
                        retrieveData2(facility2);
                        retrieveData3(facility3);
                        retrieveData4(facility4);
                    }
                    else{
                        Toast.makeText(makebooking.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void retrieveData1(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    String date,date1,date2,date3;
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM"));

                    date = dateFormat.format(calendar.getTime());
                    calendar.add(Calendar.DATE,1);
                    date1 = dateFormat.format(calendar.getTime());
                    calendar.add(Calendar.DATE,1);
                    date2 = dateFormat.format(calendar.getTime());
                    calendar.add(Calendar.DATE,1);
                    date3 = dateFormat.format(calendar.getTime());
                        row1.setText(date);
                        row2.setText(date1);
                        row3.setText(date2);
                        row4.setText(date3);





                    if(task.getResult().exists()){
                        String str1 = "1";
                        String str2 = "0";
                        avl1 = String.valueOf(task.getResult().getValue());
                        if(str1.equals(Character.toString(avl1.charAt(0)))) {

                            A1.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl1.charAt(0)))){


                            A1.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl1.charAt(1)))) {

                            A2.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl1.charAt(1)))){


                            A2.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl1.charAt(2)))) {

                            A3.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl1.charAt(2)))){


                            A3.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl1.charAt(3)))) {

                            A4.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl1.charAt(3)))){


                            A4.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl1.charAt(4)))) {

                            A5.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl1.charAt(4)))){


                            A5.setBackgroundColor(Color.GREEN);
                        }

                        Log.d("TESTING","VALUE IN DATABASE FOR CRICKET0_1: "+avl1);
                    }
                    else{
                        Toast.makeText(makebooking.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void retrieveData2(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String str1 = "1";
                        String str2 = "0";
                        avl2 = String.valueOf(task.getResult().getValue());

                        if(str1.equals(Character.toString(avl2.charAt(0)))) {

                            B1.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl2.charAt(0)))){

                            B1.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl2.charAt(1)))) {

                            B2.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl2.charAt(1)))){

                            B2.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl2.charAt(2)))) {

                            B3.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl2.charAt(2)))){

                            B3.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl2.charAt(3)))) {

                            B4.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl2.charAt(3)))){


                            B4.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl2.charAt(4)))) {

                            B5.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl2.charAt(4)))){


                            B5.setBackgroundColor(Color.GREEN);
                        }
                        Log.d("TESTING",avl2);
                    }
                    else{
                        Toast.makeText(makebooking.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void retrieveData3(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String str1 = "1";
                        String str2 = "0";
                        avl3 = String.valueOf(task.getResult().getValue());
                        if(str1.equals(Character.toString(avl3.charAt(0)))) {

                            C1.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl3.charAt(0)))){

                            C1.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl3.charAt(1)))) {

                            C2.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl3.charAt(1)))){

                            C2.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl3.charAt(2)))) {

                            C3.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl3.charAt(2)))){

                            C3.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl3.charAt(3)))) {

                            C4.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl3.charAt(3)))){


                            C4.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl3.charAt(4)))) {

                            C5.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl3.charAt(4)))){


                            C5.setBackgroundColor(Color.GREEN);
                        }
                        Log.d("TESTING",avl3);
                    }
                    else{
                        Toast.makeText(makebooking.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void retrieveData4(String username){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(username);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        avl4 = String.valueOf(task.getResult().getValue());
                        String str1 = "1";
                        String str2 = "0";
                        if(str1.equals(Character.toString(avl4.charAt(0)))) {

                            D1.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl4.charAt(0)))){

                            D1.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl4.charAt(1)))) {

                            D2.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl4.charAt(1)))){

                            D2.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl4.charAt(2)))) {

                            D3.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl4.charAt(2)))){

                            D3.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl4.charAt(3)))) {

                            D4.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl4.charAt(3)))){


                            D4.setBackgroundColor(Color.GREEN);
                        }
                        if(str1.equals(Character.toString(avl4.charAt(4)))) {

                            D5.setBackgroundColor(Color.RED);
                        }
                        else if(str2.equals(Character.toString(avl4.charAt(4)))){


                            D5.setBackgroundColor(Color.GREEN);
                        }
                        Log.d("TESTING",avl4);
                    }
                    else{
                        Toast.makeText(makebooking.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}