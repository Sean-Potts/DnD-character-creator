package com.example.a3.database;

import java.util.ArrayList;

public class StatsDB {

    public static ArrayList<StatsDB> taskList = new ArrayList<StatsDB>();

    private int id;
    private long characterId;

    // character stats
    ArrayList<Integer> statsList = new ArrayList<Integer>();

    public StatsDB(){};

public StatsDB(StatsDB copy){
    this.id = copy.id;
    this.characterId = copy.characterId;
    this.baseStrength = copy.baseStrength;
    this.baseDexterity = copy.baseDexterity;
    this.baseConstitution = copy.baseConstitution;
    this.baseIntelligence = copy.baseIntelligence;
    this.baseWisdom = copy.baseWisdom;
    this.baseCharisma = copy.baseCharisma;
    this.bonusStrength = copy.bonusStrength;
    this.bonusDexterity = copy.bonusDexterity;
    this.bonusConstitution = copy.bonusConstitution;
    this.bonusIntelligence = copy.bonusIntelligence;
    this.bonusWisdom = copy.bonusWisdom;
    this.bonusCharisma = copy.bonusCharisma;
}
    public StatsDB(int id, int characterId, int baseStrength, int baseDexterity, int baseConstitution, int baseIntelligence, int baseWisdom, int baseCharisma, int bonusStrength,
                   int bonusDexterity, int bonusConstitution, int bonusIntelligence , int bonusWisdom , int bonusCharisma) {
        this.id = id;
        this.characterId = characterId;
        this.baseStrength = baseStrength;
        this.baseDexterity = baseDexterity;
        this.baseConstitution = baseConstitution;
        this.baseIntelligence = baseIntelligence;
        this.baseWisdom = baseWisdom;
        this.baseCharisma = baseCharisma;
        this.bonusStrength = bonusStrength;
        this.bonusDexterity = bonusDexterity;
        this.bonusConstitution = bonusConstitution;
        this.bonusIntelligence = bonusIntelligence;
        this.bonusWisdom = bonusWisdom;
        this.bonusCharisma = bonusCharisma;
    }

    public StatsDB(int id) {
        this.id = id;
    }

    public StatsDB(int characterId, int baseStrength, int baseDexterity, int baseConstitution, int baseIntelligence, int baseWisdom, int baseCharisma, int bonusStrength,
                   int bonusDexterity, int bonusConstitution, int bonusIntelligence , int bonusWisdom , int bonusCharisma) {
        this.id = 0;
        this.characterId = characterId;
        this.baseStrength = baseStrength;
        this.baseDexterity = baseDexterity;
        this.baseConstitution = baseConstitution;
        this.baseIntelligence = baseIntelligence;
        this.baseWisdom = baseWisdom;
        this.baseCharisma = baseCharisma;
        this.bonusStrength = bonusStrength;
        this.bonusDexterity = bonusDexterity;
        this.bonusConstitution = bonusConstitution;
        this.bonusIntelligence = bonusIntelligence;
        this.bonusWisdom = bonusWisdom;
        this.bonusCharisma = bonusCharisma;
    }
    int baseStrength = 0;
    int baseDexterity = 0;
    int baseConstitution = 0;
    int baseIntelligence = 0;
    int baseWisdom = 0;
    int baseCharisma = 0;

    int bonusStrength = 0;
    int bonusDexterity = 0;
    int bonusConstitution = 0;
    int bonusIntelligence = 0;
    int bonusWisdom = 0;
    int bonusCharisma = 0;

    // skills

    public ArrayList<Integer> getStatsList() {
        return statsList;
    }

    public void setStatsList(ArrayList<Integer> newStats) {
        statsList = newStats;
    }

    // really wish I had properties for this

    public long getCharacterId() {
        return characterId;
    }

    public int getId() {
        return id;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public int getBaseDexterity() {
        return baseDexterity;
    }

    public void setBaseDexterity(int baseDexterity) {
        this.baseDexterity = baseDexterity;
    }

    public int getBaseConstitution() {
        return baseConstitution;
    }

    public void setBaseConstitution(int baseConstitution) {
        this.baseConstitution = baseConstitution;
    }

    public int getBaseIntelligence() {
        return baseIntelligence;
    }

    public void setBaseIntelligence(int baseIntelligence) {
        this.baseIntelligence = baseIntelligence;
    }

    public int getBaseWisdom() {
        return baseWisdom;
    }

    public void setBaseWisdom(int baseWisdom) {
        this.baseWisdom = baseWisdom;
    }

    public int getBaseCharisma() {
        return baseCharisma;
    }

    public void setBaseCharisma(int baseCharisma) {
        this.baseCharisma = baseCharisma;
    }

    public int getBonusStrength() {
        return bonusStrength;
    }

    public void setBonusStrength(int bonusStrength) {
        this.bonusStrength = bonusStrength;
    }

    public int getBonusDexterity() {
        return bonusDexterity;
    }

    public void setBonusDexterity(int bonusDexterity) {
        this.bonusDexterity = bonusDexterity;
    }

    public int getBonusConstitution() {
        return bonusConstitution;
    }

    public void setBonusConstitution(int bonusConstitution) {
        this.bonusConstitution = bonusConstitution;
    }

    public int getBonusIntelligence() {
        return bonusIntelligence;
    }

    public void setBonusIntelligence(int bonusIntelligence) {
        this.bonusIntelligence = bonusIntelligence;
    }

    public int getBonusWisdom() {
        return bonusWisdom;
    }

    public void setBonusWisdom(int bonusWisdom) {
        this.bonusWisdom = bonusWisdom;
    }

    public int getBonusCharisma() {
        return bonusCharisma;
    }

    public void setBonusCharisma(int bonusCharisma) {
        this.bonusCharisma = bonusCharisma;
    }
}
