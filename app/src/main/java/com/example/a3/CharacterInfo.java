/*
File: CharacterInfo.java
Project: MAD-A3
Programmer: Isaiah Bartlett
Date: 3/15/2023
Description: contains the local game class
 */


package com.example.a3;

import android.content.ContentValues;
import android.net.Uri;

import com.example.a3.database.CharacterDB;

import java.util.ArrayList;
import java.util.Arrays;

/*
Name: CharacterInfo
Purpose: this class contains all the information about the current character you are working on.
this contains the wide range of character details, stats, and skills/
it also stores information about all of the possible classes, races, and backgrounds that the player could choose from
 */

public class CharacterInfo {
    public static CharacterInfo CurrentCharacter = new CharacterInfo();

    public int id = 0;

    public int getId() {
        return id;
    }

    public boolean isSaved = false;

    public CharacterInfo(CharacterInfo characterInfo)
    {

        this.id=(characterInfo.getId());
        this.characterName=(characterInfo.getCharacterName());
        this.characterClass=(characterInfo.getCharacterClass());
        this.race=(characterInfo.getRace());
        this.characterClass=(characterInfo.getCharacterClass());
        this.background=(characterInfo.getBackground());

        this.baseCharisma=(characterInfo.getBaseCharisma());
        this.baseConstitution=(characterInfo.getBaseConstitution());
        this.baseDexterity=(characterInfo.getBaseDexterity());
        this.baseIntelligence=(characterInfo.getBaseIntelligence());
        this.baseStrength=(characterInfo.getBaseStrength());
        this.baseWisdom=(characterInfo.getBaseWisdom());
        this.bonusCharisma=(characterInfo.getBonusCharisma());
        this.bonusConstitution=(characterInfo.getBonusConstitution());
        this.bonusDexterity=(characterInfo.getBonusDexterity());
        this.bonusIntelligence=(characterInfo.getBaseIntelligence());
        this.bonusStrength=(characterInfo.getBonusStrength());
        this.bonusWisdom=(characterInfo.getBonusWisdom());

        this.skillProficiencies = characterInfo.getSkillProficiencies();
    }

    public CharacterInfo(ContentValues values)
    {

        this.id=Integer.parseInt((String)values.get(CharacterDB.CHARACTER_ID));

        this.characterName= (String)values.get(CharacterDB.CHARACTER_NAME);
        this.race= Integer.parseInt( (String)values.get(CharacterDB.CHARACTER_RACE));

        this.characterClass=Integer.parseInt(String.valueOf(values.get(CharacterDB.CHARACTER_CLASS)));

        this.background=Integer.parseInt( (String)values.get(CharacterDB.CHARACTER_BACKGROUND));
        this.baseCharisma=Integer.parseInt( (String)values.get(CharacterDB.STATS_BASE_CHARISMA));
        this.baseConstitution=Integer.parseInt( (String)values.get(CharacterDB.STATS_BASE_CONSTITUTION));
        this.baseDexterity=Integer.parseInt((String)values.get(CharacterDB.STATS_BASE_DEXTERITY));
        this.baseIntelligence=Integer.parseInt( (String)values.get(CharacterDB.STATS_BASE_INTELLIGENCE));
        this.baseStrength=Integer.parseInt( (String)values.get(CharacterDB.STATS_BASE_STRENGTH));
        this.baseWisdom=Integer.parseInt( (String)values.get(CharacterDB.STATS_BASE_WISDOM));
        this.bonusCharisma=Integer.parseInt( (String)values.get(CharacterDB.STATS_BONUS_CHARISMA));
        this.bonusConstitution=Integer.parseInt( (String)values.get(CharacterDB.STATS_BONUS_CONSTITUTION));
        this.bonusDexterity=Integer.parseInt( (String)values.get(CharacterDB.STATS_BONUS_DEXTERITY));
        this.bonusIntelligence=Integer.parseInt( (String)values.get(CharacterDB.STATS_BONUS_INTELLIGENCE));
        this.bonusStrength=Integer.parseInt( (String)values.get(CharacterDB.STATS_BONUS_STRENGTH));
        this.bonusWisdom=Integer.parseInt( (String)values.get(CharacterDB.STATS_BONUS_WISDOM));


        boolean[] myFalseArray = new boolean[18];
        Arrays.fill(myFalseArray, false);
        this.skillProficiencies =  myFalseArray;

    }


    public CharacterInfo(){
        this.id = 0;
    }
    public void setId(int id) {
        this.id = id;
    }

    // character base
    public enum Classes {
        none,
        Barbarian,
        Bard,
        Cleric,
        Druid,
        Fighter,
        Monk,
        Paladin,
        Ranger,
        Rogue,
        Sorcerer,
        Warlock,
        Wizard
    };

    public enum Races {
        none,
        Dragonborn,
        Dwarf,
        Elf,
        Gnome,
        HalfElf,
        Halfling,
        HalfOrc,
        Human,
        Teifling
    }

    public enum Backgrounds {
        none,
        Acolyte,
        Charlatan,
        Criminal,
        Entertainer,
        FolkHero,
        GuildArtisan,
        Hermit,
        Noble,
        Outlander,
        Sage,
        Sailor,
        Soldier,
        Urchin
    }

    public String savedStatus()
    {
        if (isSaved)
        {
            return "\nsaved";
        }
        else
        {
            return "\nnot saved";
        }

    }

    String characterName = "NewCharacter";
    int characterClass = 0; // class is not a valid variable name
    int race = 0;
    int background = 0;
    Uri imageUri = null;

    public String getCharacterName() { return characterName; }

    public void setCharacterName(String characterName) { this.characterName = characterName; this.isSaved = false; }
    public int getCharacterClass() { return characterClass; }
    public void setCharacterClass(int characterClass) { this.characterClass = characterClass; this.isSaved = false; }
    public int getRace() { return race; }
    public void setRace(int race) { this.race = race; this.isSaved = false; }
    public int getBackground() { return background; }
    public void setBackground(int background) { this.background = background; this.isSaved = false; }
    public Uri getImageUri() { return imageUri; }
    public void setImageUri(Uri imageUri) { this.imageUri = imageUri; }

    // character stats
    ArrayList<Integer> statsList = new ArrayList<Integer>();

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
    public enum SkillsList {
        Athletics,

        Acrobatics,
        Sleight_of_Hand,
        Stealth,

        Arcana,
        History,
        Investigation,
        Nature,
        Religion,

        Animal_Handling,
        Insight,
        Medicine,
        Perception,
        Survival,

        Deception,
        Intimidation,
        Performance,
        Persuasion
    }
    boolean[] skillProficiencies = new boolean[18];
    int proficiencyBonus = 2;



    public ArrayList<Integer> getStatsList() { return statsList; }
    public void setStatsList(ArrayList<Integer> newStats) {
        statsList = newStats;
    }

    public boolean[] getSkillProficiencies() { return skillProficiencies; }
    public void setSkillProficiency(int index, boolean isProficient) {
        if (index > -1 && index < 18) {
            skillProficiencies[index] = isProficient;
        }
    }
    public void setSkillProficiency(boolean[] setSkillProficiency) { this.skillProficiencies = setSkillProficiency; }

    public int getProficiencyBonus() { return proficiencyBonus; }

    // really wish I had properties for this
    public int getBaseStrength() { return baseStrength; }
    public void setBaseStrength(int baseStrength) { this.baseStrength = baseStrength; }
    public int getBaseDexterity() { return baseDexterity; }
    public void setBaseDexterity(int baseDexterity) { this.baseDexterity = baseDexterity; }
    public int getBaseConstitution() { return baseConstitution; }
    public void setBaseConstitution(int baseConstitution) { this.baseConstitution = baseConstitution; }
    public int getBaseIntelligence() { return baseIntelligence; }
    public void setBaseIntelligence(int baseIntelligence) { this.baseIntelligence = baseIntelligence; }
    public int getBaseWisdom() { return baseWisdom; }
    public void setBaseWisdom(int baseWisdom) { this.baseWisdom = baseWisdom; }
    public int getBaseCharisma() { return baseCharisma; }
    public void setBaseCharisma(int baseCharisma) { this.baseCharisma = baseCharisma; }

    public int getBonusStrength() { return bonusStrength; }
    public void setBonusStrength(int bonusStrength) { this.bonusStrength = bonusStrength; }
    public int getBonusDexterity() { return bonusDexterity; }
    public void setBonusDexterity(int bonusDexterity) { this.bonusDexterity = bonusDexterity; }
    public int getBonusConstitution() { return bonusConstitution; }
    public void setBonusConstitution(int bonusConstitution) { this.bonusConstitution = bonusConstitution; }
    public int getBonusIntelligence() { return bonusIntelligence; }
    public void setBonusIntelligence(int bonusIntelligence) { this.bonusIntelligence = bonusIntelligence; }
    public int getBonusWisdom() { return bonusWisdom; }
    public void setBonusWisdom(int bonusWisdom) { this.bonusWisdom = bonusWisdom; }
    public int getBonusCharisma() { return bonusCharisma; }
    public void setBonusCharisma(int bonusCharisma) { this.bonusCharisma = bonusCharisma; }

    public int getStrength() { return baseStrength + bonusStrength; }
    public int getDexterity() { return baseDexterity + bonusDexterity; }
    public int getConstitution() { return baseConstitution + bonusConstitution; }
    public int getIntelligence() { return baseIntelligence + bonusIntelligence; }
    public int getWisdom() { return baseWisdom + bonusWisdom; }
    public int getCharisma() { return baseCharisma + bonusCharisma; }
}
