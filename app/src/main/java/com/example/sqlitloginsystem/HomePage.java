package com.example.sqlitloginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomNavigationView=findViewById(R.id.bottomnevigation);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Intent intent=getIntent();
        String phoneno=intent.getStringExtra("userPhone");

        if (savedInstanceState==null)
        {
            fragmentManager.beginTransaction().replace(R.id.fragment,new Home_fragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        Home_fragment homeFragment=new Home_fragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment,homeFragment).commit();
                        break;
                    case R.id.add:
                        Add_fragment addFragment=new Add_fragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment,addFragment).commit();
                        break;
                    case R.id.profile:
                        Profile_fragment profileFragment=new Profile_fragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("phone",phoneno);
                        profileFragment.setArguments(bundle);
                        fragmentManager.beginTransaction().replace(R.id.fragment,profileFragment).commit();
                        break;

                }
                return true;
            }
        });


    }


    public void logout(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("LOGEDIN","0");
        editor.commit();
        startActivity(new Intent(HomePage.this,LaunchActivity.class));
    }
}