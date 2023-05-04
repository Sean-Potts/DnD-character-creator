/*
File: LocalGame.java
Project: MAD-A3
Programmer: Isaiah Bartlett
Date: 4/11/2023
Description: contains the local game class
 */

package com.example.a3;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/*
Name: LocalGame
Purpose: this class contains all of the information to store the list of local games.
this includes information about its location and the title of the game.
it stores all of this information in a static list to facilitate communication between the GameReceiver and the map fragment
 */

public class LocalGame {
    static public LatLng currentLocation = new LatLng(43.478031, -80.519787); // default is waterloo campus, will be replaced later with call to phones GPS

    static public ArrayList<LocalGame> localGameList = new ArrayList<>();
    static public ArrayList<LocalGame> serverList = new ArrayList<>();


    static public boolean add(String title, LatLng location) {
        return localGameList.add(new LocalGame(title, location));
    }
    static public boolean add(LocalGame game) {
        return localGameList.add(game);
    }

    static public void sync() {
        for (int i = localGameList.size(); i < serverList.size(); i++) {
            localGameList.add(serverList.get(i));
        }
    }




    public LocalGame(String title, LatLng location) {
        gameTitle = title;
        gameLocation = location;
    }

    String gameTitle;
    public String getGameTitle() { return gameTitle; }
    public void setGameTitle(String title) { gameTitle = title; }

    LatLng gameLocation;
    public LatLng getGameLocation() { return gameLocation; }
    public void setGameLocation(LatLng location) { gameLocation = location; }


}
