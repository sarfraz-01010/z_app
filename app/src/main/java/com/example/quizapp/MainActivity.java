package com.example.quizapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        // Setting toolbar
        setSupportActionBar(toolbar);

        // Making it so it can be added to toggle the drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        // Adding listener to drawer and passing toggle
        drawerLayout.addDrawerListener(toggle);

        // Managing state of drawer open or close
        toggle.syncState();

        // Click listener for items in the drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_quiz){
                    if(flag != 1){
                        firstFragment(new Quiz(MainActivity.this), flag);
                        flag = 1;
                    }
                    else {
                        loadFragment(new Quiz(MainActivity.this), flag);
                    }
                } else if (id == R.id.nav_result) {
                    if(flag!=1){
                        firstFragment(new QuizResult(), flag);
                        flag = 1;
                    }
                    else{
                        loadFragment(new QuizResult(), flag);
                    }

                }
                else if(id == R.id.nav_github){
                    String websiteUrl = "https://www.github.com/sarfraz01010";

// In your click event listener or method
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                    startActivity(intent);

                }
                drawerLayout.closeDrawer(GravityCompat.START);

                // return will be true to view the selection
                return true;
            }
        });

    private void firstFragment() {
    } }

    private void replaceFragement(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    public void loadFragment(Fragment fragment, int flag)
    {
        if (flag == 1) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_content_main, fragment);
            ft.commit();
        }
    }

    public void firstFragment(Fragment fragment, int flag){
        if (flag == 0) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.nav_host_fragment_content_main, fragment);
            ft.commit();
        }
    }
}