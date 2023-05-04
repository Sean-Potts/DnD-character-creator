package com.example.a3.database;

public class SkillsDB {

   public boolean[] skillProficiencies = new boolean[18];

    public boolean[] getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(boolean[] skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
        this.id = 0;
    }


    public SkillsDB(SkillsDB skill) {
        this.id = skill.getId();
        this.skillProficiencies = skill.getSkillProficiencies();
    }
    public SkillsDB(){
    }

    private int characterId = 0;
    private int id = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getCharacterId() {
        return characterId;
    }
}

