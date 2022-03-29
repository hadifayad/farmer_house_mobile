package com.farmerhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.farmerhouse.inbox.Inbox;
import com.farmerhouse.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.farmerhouse.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    ImageView drawer_open,inbox,add;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseApp.initializeApp(this);
//        Intent k = new Intent(MainActivity.this, splash.class);
//        startActivity(k);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer_open = findViewById(R.id.drawer_open);
        inbox = findViewById(R.id.inbox);
        add = findViewById(R.id.add);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.home_bottom:
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment).commit();
                        return true;
                    case R.id.search_bottom:
//                        SearchFragment searchFragment = new SearchFragment();
//                        fragmentTransaction.replace(R.id.main_framelayout, searchFragment).commit();
                        return true;



                    case R.id.store_bottom:

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        String userId = prefs.getString("userId", "");
                        Log.d("tag", "onReselectItem: " + userId);

                        String role = prefs.getString("role", "");
                        if(role.contains("1")) {
                            Log.d("TAG", "onCreate: "+role);

                            if (!userId.equals(null) && !userId.equals("")) {

                                MandoubActivitiesFragment mandoobAkedActivity = new MandoubActivitiesFragment();
                                fragmentTransaction.replace(R.id.nav_host_fragment, mandoobAkedActivity).commit();
                                return true;

                            } else {
                                Toast.makeText(MainActivity.this, "Please Login first..",
                                        Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(MainActivity.this, LoginOrSignup.class);

                                startActivity(intent1);
                            }

                        }
                        else{
                            Log.d("TAG", "onCreate:else "+role);
                            if (!userId.equals(null) && !userId.equals("")) {

                                ActivitiesFragment activitiesFragment = new ActivitiesFragment();
                                fragmentTransaction.replace(R.id.nav_host_fragment, activitiesFragment).commit();
                                return true;

                            } else {
                                Toast.makeText(MainActivity.this, "Please Login first..",
                                        Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(MainActivity.this, LoginOrSignup.class);

                                startActivity(intent1);
                            }
                        }



                        return true;

                    case R.id.profile_bottom:


                        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        String userId1 = prefs1.getString("userId", "");
                        Log.d("tag", "onReselectItem: " + userId1);
                        if (!userId1.equals(null) && !userId1.equals("")) {

                            ProfileFragment profileFragment = new ProfileFragment();
                            fragmentTransaction.replace(R.id.nav_host_fragment, profileFragment).commit();
                            return true;

                        } else {
                            Toast.makeText(MainActivity.this, "Please Login first..",
                                    Toast.LENGTH_LONG).show();
                            Intent intent1 = new Intent(MainActivity.this, LoginOrSignup.class);

                            startActivity(intent1);
                        }

                }
                return false;
            }
        });


                mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
//                R.id.loginItem,
                R.id.search,
                R.id.profileFragment,
                R.id.activitiesFragment,
                R.id.createMessageOptions

        )
                .setDrawerLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateMessageOptions.class);
                startActivity(i);
            }
        });

        drawer_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }

        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Inbox.class);
//                startActivity(i);

                Intent i = new Intent(MainActivity.this, ChatWithMandoubActivity.class);
                startActivity(i);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}