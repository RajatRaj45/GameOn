package com.example.gameonv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    TextView enrolshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        enrolshow = findViewById(R.id.EnrolShow);
        enrolshow.setText(GlobalVariables.ENum);
    }

    public void changePass(View view){
        Intent intent1=new Intent(this, ChangePassword.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void loggingOut(View view){
        Toast.makeText(profile.this,"You are now logged out of your account",Toast.LENGTH_SHORT).show();
        Intent intent2=new Intent(this, login.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.slidefromleft,R.anim.slidetoright);

    }
}