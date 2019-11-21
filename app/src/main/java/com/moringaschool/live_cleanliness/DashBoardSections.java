package com.moringaschool.live_cleanliness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.moringaschool.live_cleanliness.fragments.CleaningFragment;

public class DashBoardSections extends AppCompatActivity implements ViewPager.OnClickListener {
    CardView installation;
    CardView cleaners;
    CardView wellness;
    CardView paintings;
    CardView transport;
    LinearLayout frameLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_sections);
        installation=(CardView) findViewById(R.id.cardView1);
        cleaners=(CardView) findViewById(R.id.cardView2);
        wellness=(CardView) findViewById(R.id.cardView3);
        paintings=(CardView) findViewById(R.id.cardView4);
//        transport=(CardView) findViewById(R.id.cardView5);
frameLayout=findViewById(R.id.fragment_contain);

        installation.setOnClickListener(this);
        cleaners.setOnClickListener(this);
        wellness.setOnClickListener(this);
        paintings.setOnClickListener(this);
//        transport.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        if (v==installation){

            Intent clean=new Intent(DashBoardSections.this,InstallationDetails.class);
            startActivity(clean);
        }
        if (v==cleaners){
            Intent intent = new Intent(DashBoardSections.this,CleanersDetails.class);
            startActivity(intent);
        }

        if (v==wellness){
            Intent clean=new Intent(DashBoardSections.this,WellnessAndCareDetails.class);
            startActivity(clean);
        }
        if (v==paintings){

            Intent clean=new Intent(DashBoardSections.this,PaintingsDetails.class);
            startActivity(clean);
        }
//        if (v==transport){
//
//            Intent clean=new Intent(DashBoardSections.this,HomePage.class);
//            startActivity(clean);
//        }
    }

}
