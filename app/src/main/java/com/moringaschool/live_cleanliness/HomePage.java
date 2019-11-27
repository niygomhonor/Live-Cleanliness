package com.moringaschool.live_cleanliness;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.FragmentManager;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.moringaschool.live_cleanliness.fragments.CleaningFragment;
import com.moringaschool.live_cleanliness.fragments.InstallationFragment;
import com.moringaschool.live_cleanliness.fragments.PaintingFragment;
import com.moringaschool.live_cleanliness.fragments.TransportFragment;
import com.moringaschool.live_cleanliness.fragments.WellnessFragment;
import com.squareup.picasso.Picasso;


public class
HomePage extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ImageView image, leaf;
    private LinearLayout animText, texth, menus;
    private Animation frombottom;
    private Button button;

    NavigationView navigationView;
    public Uri imgUri;
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    ImageView images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawer = (DrawerLayout) findViewById(R.id.activity_main);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);

        navigationView = findViewById(R.id.nav_view);

        images = findViewById(R.id.imageView);


        navigationView.setNavigationItemSelectedListener(this);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.button);


        image = (ImageView) findViewById(R.id.imageSlide);
        leaf = (ImageView) findViewById(R.id.leaf);
        animText = (LinearLayout) findViewById(R.id.animText);
        texth = (LinearLayout) findViewById(R.id.explore);
//        menus = (LinearLayout) findViewById(R.id.menus);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.anim);


        image.animate().translationY(-1900).setDuration(2000).setStartDelay(600);
        leaf.animate().alpha(0).setDuration(2000).setStartDelay(900);
        animText.animate().translationY(140).alpha(0).setDuration(1000).setStartDelay(600);

        texth.startAnimation(frombottom);
//        menus.startAnimation(frombottom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, DashBoardSections.class);
                startActivity(intent);
            }
        });


        //
//if(savedInstanceState==null){
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InstallationFragment()).commit();
//        navigationView.setCheckedItem(R.id.nav_Installation);
//    }


        images.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                FileChooser();
                Toast.makeText(HomePage.this, "He loves you", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent.createChooser(intent,"Select image"),CHOOSE_IMAGE);

        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            images.setImageURI(imgUri);
            Picasso.get().load(imgUri).into(images);
        }
    }





    @Override
    public void onBackPressed() {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else
                super.onBackPressed();
        }

    FragmentManager fm=getFragmentManager();
    InstallationFragment inst=new InstallationFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
        case  R.id.nav_Installation:
          getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InstallationFragment()).commit();
            inst.show(fm,"Businesses");
          break;
        case  R.id.nav_clean:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CleaningFragment()).commit();
            inst.show(fm,"Businesses");
            break;
        case  R.id.nav_transport:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TransportFragment()).commit();
            inst.show(fm,"Businesses");
            break;
        case  R.id.nav_wellness:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WellnessFragment()).commit();
            inst.show(fm,"Businesses");
            break;
        case  R.id.nav_painting:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PaintingFragment()).commit();
            inst.show(fm,"Businesses");
            break;
        case  R.id.nav_help:
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
            break;
        case  R.id.nav_rate:
            Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show();
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InstallationFragment()).commit();
            break;
    }


    drawer.closeDrawer(GravityCompat.START);
        return true;


    }




    }

