package com.example.a3.functions;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class mapFunctions {
    static Random rand = new Random();

    public static void addRandomPoint(GoogleMap googleMap, LatLng currentLocation) {
        LatLng newPoint = new LatLng(currentLocation.latitude + (rand.nextDouble()-0.5)/50,
                currentLocation.longitude + (rand.nextDouble()-0.5)/100);
        googleMap.addMarker(new MarkerOptions().position(newPoint).title(getRandomMarkerName()));
    }

    public static LatLng getRandomPoint(LatLng currentLocation) {
        LatLng newPoint = new LatLng(currentLocation.latitude + (rand.nextDouble()-0.5)/50,
                currentLocation.longitude + (rand.nextDouble()-0.5)/100);
        return newPoint;
    }

    static final String names[] = {"Weaver's ", "Eric's ", "Sarah's ", "Riley's ", "Ryan's ", "Owen's ", "Issac's ", "Kira's "};
    static final String descriptor[] = {"dnd ", "DnD ", "D&D ", " "};
    public static String getRandomMarkerName() {
        String markerName = "";
        markerName = names[rand.nextInt(names.length-1)];
        markerName += descriptor[rand.nextInt(descriptor.length-1)];
        markerName += "game";
        return markerName;
    }
}

