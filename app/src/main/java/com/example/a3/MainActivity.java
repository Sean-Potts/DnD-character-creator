/*
File: MainActivity.java
Project: MAD-A3
Programmer: Isaiah Bartlett
Date: 3/14/2023
Description: the main activity that holds all of the other fragments
 */


package com.example.a3;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.a3.broadcast.GameReceiver;
import com.example.a3.databinding.ActivityMainBinding;
import com.example.a3.service.GameFinderService;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    IntentFilter gameFilter;
    GameReceiver gameReceiver;

    Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_overview, R.id.nav_base, R.id.nav_stats,
                R.id.nav_skills, R.id.nav_save_load, R.id.nav_map)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // run all of our start up

        // create receiver
        gameFilter = new IntentFilter();
        gameFilter.addAction(getPackageName() + ".GAME_BROADCAST");
        gameReceiver = new GameReceiver();
        registerReceiver(gameReceiver, gameFilter);

        // check if we are connected
        boolean connected = tryGetConnection();

        // check if we have GPS access
        boolean gotLocation = tryGetLocation();

        // our service would theoretically be checking a remote database so don't start it if we  cant connect to the database
        // likewise, we cant know where a local game is if we do not know where we are
        if (connected && gotLocation) {
            // create service
            service = new Intent(this, GameFinderService.class);
            startService(service);
        }

        /* This is causing problems with the navigation backstack which made you unable to get back to the overview fragment

        if (getIntent().getBooleanExtra("findGame", false)) {
            navController.navigate(R.id.nav_map);
        }
        */



    }

    @Override
    protected void onPause() {
        unregisterReceiver(gameReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        LocalGame.sync();
        registerReceiver(gameReceiver, gameFilter);
        super.onResume();
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



    private boolean tryGetConnection() {
        boolean status = false;

        // do we have permission to access internet?
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {

            // are we actually connected?
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
            status = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        }

        return status;
    }

    private boolean tryGetLocation() {
        boolean status = false;

        // request permission for location
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                });
        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        // if we are given permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // get location manager
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, new LocationListener() {
                @Override public void onLocationChanged(@NonNull Location location) {}});

            // get location
            try {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LocalGame.currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                status = true;
            }
            catch (Exception e) {
                Log.d("Error", "tryGetLocation: unable to get location: " + e.getMessage());
            }
        }

        return status;
    }
}
