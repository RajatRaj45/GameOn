package com.example.gameonv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class football extends AppCompatActivity {
    public static final String EXTRAfootball1 = "com.example.gameonv3.EXTRAfootball1";
    public static final String EXTRAfootball2 = "com.example.gameonv3.EXTRAfootball2";
    DatabaseReference databaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_football);
        String facname = "facilityname_"+GlobalVariables.ENum;
        databaseref = FirebaseDatabase.getInstance().getReference(facname);
    }

    public void football1(View view){
        databaseref.setValue("football1");
        Intent intentfootball1 = new Intent(this,makebooking.class);
        intentfootball1.putExtra(EXTRAfootball1,"football1");
        startActivity(intentfootball1);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void football2(View view){
        databaseref.setValue("football2");
        Intent intentfootball2 = new Intent(this,makebooking.class);
        intentfootball2.putExtra(EXTRAfootball2,"football2");
        startActivity(intentfootball2);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
}