/*
File: CharacterFileParserTask.java
Project: MAD-A2
Programmer: Isaiah Bartlett
Date: 3/17/2023
Description: Converts the character to a string output and then calls the AsyncWriteTask
 */


package com.example.a3.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.a3.CharacterInfo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
 *  Class : CharacterFileParserTask
 *  Description : Parses all of the information about the character into a single string for easier file writing
 */
public class CharacterFileParserTask extends AsyncTask<CharacterInfo, Void, String> {
    public Context context;

    String characterName;

    @Override
    protected String doInBackground(CharacterInfo... characters) {
        String characterString = "";
        CharacterInfo character = characters[0];
        characterName = character.getCharacterName();

        List<String> classNames = Stream.of(CharacterInfo.Classes.values()).map(Enum::name).collect(Collectors.toList());
        List<String> raceNames = Stream.of(CharacterInfo.Races.values()).map(Enum::name).collect(Collectors.toList());
        List<String> backgroundNames = Stream.of(CharacterInfo.Backgrounds.values()).map(Enum::name).collect(Collectors.toList());

        characterString += "Name: " + character.getCharacterName() + "\n";
        characterString += "Class: " + classNames.get(character.getCharacterClass()) + "\n";
        characterString += "Race: " + raceNames.get(character.getRace()) + "\n";
        characterString += "Background: " + backgroundNames.get(character.getBackground()) + "\n";

        characterString += "\n==================================\n\n";

        characterString += "Strength: " + String.valueOf(character.getStrength()) + "\n";
        characterString += "Dexterity: " + String.valueOf(character.getDexterity()) + "\n";
        characterString += "Constitution: " + String.valueOf(character.getConstitution()) + "\n";
        characterString += "Intelligence: " + String.valueOf(character.getIntelligence()) + "\n";
        characterString += "Wisdom: " + String.valueOf(character.getWisdom()) + "\n";
        characterString += "Charisma: " + String.valueOf(character.getCharisma()) + "\n";

        characterString += "\n==================================\n\n";

        List<String> skillNames = Stream.of(CharacterInfo.SkillsList.values()).map(Enum::name).collect(Collectors.toList());
        int i = 0;
        for (; i < 1; i++) { // str skill
            int statVal = character.getStrength();
            if (character.getSkillProficiencies()[i] == true) {
                statVal += character.getProficiencyBonus();
            }

            characterString += skillNames.get(i) + " : Str : " + String.valueOf(statVal) + "\n";
        }
        characterString += "\n";
        for (; i < 4; i++) { // dex skills
            int statVal = character.getStrength();
            if (character.getSkillProficiencies()[i] == true) {
                statVal += character.getProficiencyBonus();
            }

            characterString += skillNames.get(i) + " : Dex : " + String.valueOf(statVal) + "\n";
        }
        characterString += "\n";
        for (; i < 9; i++) { // int skills
            int statVal = character.getStrength();
            if (character.getSkillProficiencies()[i] == true) {
                statVal += character.getProficiencyBonus();
            }

            characterString += skillNames.get(i) + " : Int : " + String.valueOf(statVal) + "\n";
        }
        characterString += "\n";
        for (; i < 14; i++) { // wis skills
            int statVal = character.getStrength();
            if (character.getSkillProficiencies()[i] == true) {
                statVal += character.getProficiencyBonus();
            }

            characterString += skillNames.get(i) + " : Wis : " + String.valueOf(statVal) + "\n";
        }
        characterString += "\n";
        for (; i < 18; i++) { // Cha skills
            int statVal = character.getStrength();
            if (character.getSkillProficiencies()[i] == true) {
                statVal += character.getProficiencyBonus();
            }

            characterString += skillNames.get(i) + " : Cha : " + String.valueOf(statVal) + "\n";
        }

        return characterString;
    }

    @Override
    protected void onPostExecute(String result) {
        AsyncWriteTask task = new AsyncWriteTask();
        task.context = this.context;
        task.execute(characterName, result);
    }
}
