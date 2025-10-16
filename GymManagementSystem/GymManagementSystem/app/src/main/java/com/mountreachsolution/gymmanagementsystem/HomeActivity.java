package com.mountreachsolution.gymmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mountreachsolution.gymmanagementsystem.MyDetails.MyDetailsActivity;
import com.mountreachsolution.gymmanagementsystem.MyDiet.MyDietActivity;
import com.mountreachsolution.gymmanagementsystem.MyPackage.MyPackageActivity;
import com.mountreachsolution.gymmanagementsystem.MySchedule.MyScheduleActivity;

public class HomeActivity extends AppCompatActivity {
    boolean doubletap = false;

    CardView cardView11, cardView22,cardView33,cardView44,cardView55;
    ProgressBar progress;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Gym Member Dashboard");

        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preferences.edit();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firsttime = prefs.getBoolean("firsttime",true);

        if (firsttime)
        {
            welcome();
        }


        cardView11 = (CardView) findViewById(R.id.cardview11);
        cardView22 = (CardView) findViewById(R.id.cardview22);
        cardView33 = (CardView) findViewById(R.id.cardview33);
        cardView44 = (CardView) findViewById(R.id.cardview44);
        cardView55 = (CardView) findViewById(R.id.cardview55);

        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, MyDetailsActivity.class));
            }
        });

        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MyScheduleActivity.class));
            }
        });

        cardView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, MyDietActivity.class));
            }
        });

        cardView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, MyPackageActivity.class));
            }
        });

        cardView55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    public void logout()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Logout")
                .setMessage("Are You Sure You Want To Logout")
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putBoolean("islogin",false).commit();
                        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                        finish();
                    }
                });

        AlertDialog alertDialog = ad.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);

    }


    @Override
    public void onBackPressed() {
        if (doubletap){
            super.onBackPressed();
        }
        else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            doubletap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            }, 1000);
        }
    }

    public void welcome()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Gym Management System");
        ad.setMessage("Welcome to Member App");
        ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = ad.create();
        alertDialog.show();

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firsttime", false);
        editor.apply();
    }
}