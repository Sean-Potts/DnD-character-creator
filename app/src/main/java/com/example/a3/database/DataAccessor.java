package com.example.a3.database;

import com.example.a3.CharacterInfo;

import java.util.ArrayList;

/*
Name: DataAccessor
Purpose: This class is to allow for interaction with the database using the CharacterInfo
 */
public class DataAccessor
{
    public DataAccessor(CharacterDB dbMain){
        this.db = dbMain;
    }

    public static CharacterDB db;

    public int addCharacter(CharacterInfo characterData)
    {
        ArrayList<String> Names = new ArrayList<String>(db.getCharacterNames());
        String characterName = characterData.getCharacterName();
        int count = 0;
        boolean nameExists = false;

        while (Names.size() > count)
        {
            nameExists = characterName.equals(Names.get(count));

            if(nameExists == true)
            {
                return 0;
            }
            count++;

         }


        CharacterInfoDB characterInsertData = new CharacterInfoDB();

        // Setting up Character Data
        characterInsertData.setCharacterName(characterData.getCharacterName());
        characterInsertData.setCharacterClass(characterData.getCharacterClass());
        characterInsertData.setRace(characterData.getRace());
        characterInsertData.setCharacterClass(characterData.getCharacterClass());
        characterInsertData.setBackground(characterData.getBackground());
        db.insertCharacterInfo(characterInsertData);

        // Stats
        StatsDB statsInsertData = new StatsDB();
        statsInsertData.setBaseCharisma(characterData.getBaseCharisma());
        statsInsertData.setBaseConstitution(characterData.getBaseConstitution());
        statsInsertData.setBaseDexterity(characterData.getBaseDexterity());
        statsInsertData.setBaseIntelligence(characterData.getBaseIntelligence());
        statsInsertData.setBaseStrength(characterData.getBaseStrength());
        statsInsertData.setBaseWisdom(characterData.getBaseWisdom());
        statsInsertData.setBonusCharisma(characterData.getBonusCharisma());
        statsInsertData.setBonusConstitution(characterData.getBonusConstitution());
        statsInsertData.setBonusDexterity(characterData.getBonusDexterity());
        statsInsertData.setBonusIntelligence(characterData.getBonusIntelligence());
        statsInsertData.setBonusStrength(characterData.getBonusStrength());
        statsInsertData.setBonusWisdom(characterData.getBonusWisdom());
        db.insertStats(statsInsertData);

        // Skills
        SkillsDB skill = new SkillsDB();
        skill.setSkillProficiencies(characterData.getSkillProficiencies());
        db.insertSkills(skill);

    return 1;
    }


    public CharacterInfo getCharacter(String characterName)
    {

        //db.getCharacter(characterName);
        CharacterInfoDB characterDataRec = new CharacterInfoDB(db.getCharacter(characterName));
        CharacterInfo characterReturn = new CharacterInfo();

        // Setting up Character Data
        characterReturn.setId(characterDataRec.getId());

        characterReturn.setCharacterName(characterDataRec.getCharacterName());
        characterReturn.setCharacterClass(characterDataRec.getCharacterClass());
        characterReturn.setRace(characterDataRec.getRace());
        characterReturn.setCharacterClass(characterDataRec.getCharacterClass());
        characterReturn.setBackground(characterDataRec.getBackground());

        // Stats
        StatsDB statsDataRec = new StatsDB(db.getStat(characterDataRec.getId()));


        characterReturn.setBaseCharisma(statsDataRec.getBaseCharisma());
        characterReturn.setBaseConstitution(statsDataRec.getBaseConstitution());
        characterReturn.setBaseDexterity(statsDataRec.getBaseDexterity());
        characterReturn.setBaseIntelligence(statsDataRec.getBaseIntelligence());
        characterReturn.setBaseStrength(statsDataRec.getBaseStrength());
        characterReturn.setBaseWisdom(statsDataRec.getBaseWisdom());
        characterReturn.setBonusCharisma(statsDataRec.getBonusCharisma());
        characterReturn.setBonusConstitution(statsDataRec.getBonusConstitution());
        characterReturn.setBonusDexterity(statsDataRec.getBonusDexterity());
        characterReturn.setBonusIntelligence(statsDataRec.getBaseIntelligence());
        characterReturn.setBonusStrength(statsDataRec.getBonusStrength());
        characterReturn.setBonusWisdom(statsDataRec.getBonusWisdom());


        // Skill s
        SkillsDB skill = new SkillsDB(db.getSkill(characterDataRec.getId()));

        characterReturn.setSkillProficiency(skill.getSkillProficiencies());


        return characterReturn;
    }


    public int deleteCharacter(String characterName)
    {
        ArrayList<String> Names = new ArrayList<String>(db.getCharacterNames());

        int count = 0;
        boolean nameExists = false;

        while (Names.size() > count)
        {
            nameExists = characterName.equals(Names.get(count));

            if(nameExists)
            {
               break;
            }

            count++;

        }

        if(!nameExists)
        {
            return 0;
        }

        int id = db.getCharacterId(characterName);
        db.deleteCharacter(id);
        db.deleteSkills(id);
        db.deleteStats(id);

        return 1;

        //return;
    }




}

