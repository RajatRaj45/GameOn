package com.example.gameonv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    //change begin

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView menuIcon;

    public void cricketbutton(View view){
        Intent intentcricket = new Intent(this,cricket.class);
        startActivity(intentcricket);
    }

    public void footballbutton(View view){
        Intent intentfootball = new Intent(this,football.class);
        startActivity(intentfootball);
    }

    public void badmintonbutton(View view){
        Intent intentbadminton = new Intent(this,badminton.class);
        startActivity(intentbadminton);
    }

    public void tennisbutton(View view){
        Intent intenttennis = new Intent(this,tennis.class);
        startActivity(intenttennis);
    }


    //change end


    public void gotoprofile(View view){
        Intent intentProfile = new Intent(this,profile.class);
        startActivity(intentProfile);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void gotoviewbooking(View view){
        if(GlobalVariables.booking1.equals("_") && GlobalVariables.booking2.equals("_")){
            Toast.makeText(this, "NO BOOKINGS MADE!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intentViewbooking = new Intent(this,viewbooking.class);
            overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

            startActivity(intentViewbooking);
        }
    }

    public void gotomakebooking(View view){
        Intent intentMakebooking = new Intent(this,sportselection.class);

        startActivity(intentMakebooking);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    public void gotofeedback(View view){
        gofeedback();
    }

    public void farzi1(){
        Intent intentFeedback = new Intent(this,feedback.class);
        startActivity(intentFeedback);
        overridePendingTransition(R.anim.slidefromright,R.anim.slidetoleft);

    }

    private void navigationDrawer(){
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);


        //change begin
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        menuIcon=findViewById(R.id.menu_icon);
//        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //change end


        navigationDrawer();



        getbooking1();
        getbooking2();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent1=new Intent(this,viewbooking.class);
                startActivity(intent1);
                break;
            case R.id.nav_feedback:
                Intent intent2=new Intent(this,feedback.class);
                startActivity(intent2);
                break;
            case R.id.nav_profile:
                Intent intent3=new Intent(this,profile.class);
                startActivity(intent3);
                break;
        }
        return true;
    }


    public void gofeedback(){
        String fac1 = "user_"+GlobalVariables.ENum+"_flag";
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(fac1);
        reference1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String b1 = String.valueOf(task.getResult().getValue());
                        if(b1.equals("NO")){
                            Toast.makeText(home.this, "Cannot Submit Feedback without making a booking!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            farzi1();
                        }
                    }
                }
            }
        });
    }

    public void getbooking1(){
        String fac1 = "user_"+GlobalVariables.ENum+"_1";
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(fac1);
        reference1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String b1 = String.valueOf(task.getResult().getValue());
                        GlobalVariables.booking1 = b1;
                    }
                }
            }
        });
    }

    public void getbooking2(){
        String fac2 = "user_"+GlobalVariables.ENum+"_2";
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(fac2);
        reference1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        String b2 = String.valueOf(task.getResult().getValue());
                        GlobalVariables.booking2 = b2;
                    }
                }
            }
        });
    }
}