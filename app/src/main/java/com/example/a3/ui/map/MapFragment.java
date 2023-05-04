package com.example.a3.ui.map;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.LOCATION_SERVICE;


import android.Manifest;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a3.LocalGame;
import com.example.a3.R;
import com.example.a3.databinding.FragmentMapBinding;
import com.example.a3.functions.mapFunctions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapViewModel mapViewModel;
    FragmentMapBinding mapBinding;

    private GoogleMap googleMap;

    boolean findGame = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        mapBinding = FragmentMapBinding.inflate(inflater);
        View root = mapBinding.getRoot();

        findGame = getActivity().getIntent().getBooleanExtra("findGame", false);
        if (findGame) {
            getActivity().getIntent().removeExtra("findGame");
        }

        // get connectivity manager and check if we are connected
        boolean connected = tryGetConnection();

        if (connected) {
            mapBinding.MyMap.setVisibility(View.VISIBLE);
            mapBinding.noInternetText.setVisibility(View.GONE);

            try {
                if (googleMap == null) {
                    // start up the map
                    SupportMapFragment mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MyMap);
                    mMapFragment.getMapAsync(this);
                }
                else {
                    this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder().target(LocalGame.currentLocation).zoom(15.0f).bearing(1).tilt(10).build()
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            mapBinding.MyMap.setVisibility(View.GONE);
            mapBinding.noInternetText.setVisibility(View.VISIBLE);
        }

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (this.googleMap != null) {
            for (LocalGame game: LocalGame.localGameList ) {
                googleMap.addMarker(new MarkerOptions().position(game.getGameLocation()).title(game.getGameTitle()));
            }

            LatLng location;
            if (findGame) {
                // since the notification will clear all other notifications we know that it will always be the last one in the list
                location = LocalGame.localGameList.get(LocalGame.localGameList.size()-1).getGameLocation();
            }
            else {
                location = LocalGame.currentLocation;
            }

            this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                    new CameraPosition.Builder().target(location).zoom(15.0f).bearing(1).tilt(10).build()
            ));

            this.googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            this.googleMap.getUiSettings().setCompassEnabled(true);
            this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }



    private boolean tryGetConnection() {
        boolean status = false;

        // do we have permission to access internet?
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {

            // are we actually connected?
            ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(CONNECTIVITY_SERVICE);
            status = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        }

        return status;
    }
}