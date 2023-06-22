package com.example.gameonv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class badminton extends AppCompatActivity {
    public static final String EXTRAbadminton1 = "com.example.gameonv3.EXTRAbadminton1";
    public static final String EXTRAbadminton2 = "com.example.gameonv3.EXTRAbadminton2";
    DatabaseReference databaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_badminton);
        String facname = "facilityname_"+GlobalVariables.ENum;
        databaseref = FirebaseDatabase.getInstance().getReference(facname);
    }

    public void badminton1(View view){
        databaseref.setValue("badminton1");
        Intent intentbadminton1 = new Intent(this,makebooking.class);
        intentbadminton1.putExtra(EXTRAbadminton1,"badminton1");
        startActivity(intentbadminton1);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
    public void badminton2(View view){
        databaseref.setValue("badminton2");
        Intent intentbadminton2 = new Intent(this,makebooking.class);
        intentbadminton2.putExtra(EXTRAbadminton2,"badminton2");
        startActivity(intentbadminton2);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
}