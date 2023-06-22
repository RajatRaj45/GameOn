package com.example.gameonv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tennis extends AppCompatActivity {
    public static final String EXTRAtennis1 = "com.example.gameonv3.EXTRAtennis1";
    public static final String EXTRAtennis2 = "com.example.gameonv3.EXTRAtennis2";
    DatabaseReference databaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tennis);
        String facname = "facilityname_"+GlobalVariables.ENum;
        databaseref = FirebaseDatabase.getInstance().getReference(facname);
    }
    public void tennis1(View view){
        databaseref.setValue("tennis1");
        Intent intenttennis1 = new Intent(this,makebooking.class);
        intenttennis1.putExtra(EXTRAtennis1,"tennis1");
        startActivity(intenttennis1);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void tennis2(View view){
        databaseref.setValue("tennis2");
        Intent intenttennis2 = new Intent(this,makebooking.class);
        intenttennis2.putExtra(EXTRAtennis2,"tennis2");
        startActivity(intenttennis2);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
}