package com.example.gameonv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cricket extends AppCompatActivity {
    public static final String EXTRAcricket1 = "com.example.gameonv3.EXTRAcricket1";
    public static final String EXTRAcricket2 = "com.example.gameonv3.EXTRAcricket2";
    DatabaseReference databaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cricket);String facname = "facilityname_"+GlobalVariables.ENum;
        databaseref = FirebaseDatabase.getInstance().getReference(facname);
    }

    public void cricket1(View view){
        databaseref.setValue("cricket1");
        Intent intentcricket1 = new Intent(this,makebooking.class);
//        intentcricket1.putExtra(EXTRAcricket1,"cricket1");
        startActivity(intentcricket1);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
    public void cricket2(View view){
        databaseref.setValue("cricket2");
        Intent intentcricket2 = new Intent(this,makebooking.class);
//        intentcricket2.putExtra(EXTRAcricket2,"cricket2");
        startActivity(intentcricket2);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
}