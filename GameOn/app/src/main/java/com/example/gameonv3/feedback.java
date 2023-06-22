package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {
    EditText FeedbackText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feedback);
        FeedbackText = (EditText) findViewById(R.id.FeedbackText);
    }

    public void submitfeedback(View view){
        String enteredfeedback = FeedbackText.getText().toString();
        setfeed(enteredfeedback);
    }
    public void farzi2(){
        Intent intenthomefromlogin = new Intent(this, home.class);
        startActivity(intenthomefromlogin);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }
    public void setfeed(String x){
        String fac1 = "user_"+GlobalVariables.ENum+"_feedback";
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(fac1);
        reference1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String b1 = String.valueOf(task.getResult().getValue());
                        b1 = b1 +x+"_&_";
                        reference1.setValue(b1);
                        Toast.makeText(feedback.this, "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                        farzi2();
                    }
                }
            }
        });
    }
}