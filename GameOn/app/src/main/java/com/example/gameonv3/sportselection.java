package com.example.gameonv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class sportselection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sportselection);
    }

    public void cricketbutton(View view){
        Intent intentcricket = new Intent(this,cricket.class);
        startActivity(intentcricket);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void footballbutton(View view){
        Intent intentfootball = new Intent(this,football.class);
        startActivity(intentfootball);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void badmintonbutton(View view){
        Intent intentbadminton = new Intent(this,badminton.class);
        startActivity(intentbadminton);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void tennisbutton(View view){
        Intent intenttennis = new Intent(this,tennis.class);
        startActivity(intenttennis);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
}