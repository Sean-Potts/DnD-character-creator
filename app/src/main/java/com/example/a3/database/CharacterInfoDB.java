package com.example.a3.database;

import java.util.ArrayList;


public class CharacterInfoDB {

    public ArrayList<CharacterInfoDB> taskList = new ArrayList<CharacterInfoDB>();

    public  ArrayList<CharacterInfoDB> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<CharacterInfoDB> taskList) {
        this.taskList = taskList;
    }

    private int id;
    String characterName;
    int characterClass; // class is not a valid variable name
    int race;
    int background;

    public CharacterInfoDB(int id, String charactername, int characterClass, int race, int background){
        this.id = id;
        this.characterName = charactername;
        this.characterClass = characterClass;
        this.race = race;
        this.background = background;
    }
    public CharacterInfoDB(CharacterInfoDB characterInfo){
        this.id = characterInfo.getId();
        this.characterName = characterInfo.getCharacterName();
        this.characterClass =  characterInfo.getCharacterClass();
        this.race = characterInfo.getRace();
        this.background = characterInfo.getBackground();
    }


    public CharacterInfoDB(){};

    public CharacterInfoDB(ArrayList<CharacterInfoDB> characters) {
        this.taskList = characters;
    }


    public int getBackground() {
        return background;
    }

    public String getCharacterName() {
        return characterName;
    }

    public int getRace() {
        return race;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(int characterClass) {
        this.characterClass = characterClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
