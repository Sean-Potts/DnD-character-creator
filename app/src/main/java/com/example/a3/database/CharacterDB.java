package com.example.a3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/*
Name: CharacterDB
Purpose: This class is for the database used in the application. It creates a database and has methods to interact with the database.
*/
public class CharacterDB {
    // database constants
    public static final String DB_NAME = "characterlist.db";
    public static final int    DB_VERSION = 1;

    // character table constants
    public static final String CHARACTER_TABLE = "character";

    public static final String CHARACTER_ID = "id";
    public static final int    CHARACTER_ID_COL = 0;

    public static final String CHARACTER_NAME = "name";
    public static final int    CHARACTER_NAME_COL = 1;

    public static final String CHARACTER_CLASS = "class";
    public static final int    CHARACTER_CLASS_COL = 2;
    public static final String CHARACTER_RACE = "race";
    public static final int    CHARACTER_RACE_COL = 3;
    public static final String CHARACTER_BACKGROUND = "background";
    public static final int    CHARACTER_BACKGROUND_COL = 4;
////////////////////////////////////////////////////////////////////////////////


    // STATS table constants
    public static final String STATS_TABLE = "stats";

    public static final String STATS_ID = "id";
    public static final int    STATS_ID_COL = 0;

    public static final String STATS_CHARACTER_ID = "character_id";
    public static final int    STATS_CHARACTER_ID_COL = 1;


    // base stats
    public static final String STATS_BASE_STRENGTH = "base_strength";
    public static final int    STATS_BASE_STRENGTH_COL = 2;

    public static final String STATS_BASE_DEXTERITY = "base_dexterity";
    public static final int    STATS_BASE_DEXTERITY_COL = 3;

    public static final String STATS_BASE_CONSTITUTION = "base_constitution";
    public static final int    STATS_BASE_CONSTITUTION_COL = 4;

    public static final String STATS_BASE_INTELLIGENCE = "base_intelligence";
    public static final int    STATS_BASE_INTELLIGENCE_COL = 5;

    public static final String STATS_BASE_WISDOM  = "base_wisdom";
    public static final int    STATS_BASE_WISDOM_COL = 6;

    public static final String STATS_BASE_CHARISMA = "base_charisma";
    public static final int    STATS_BASE_CHARISMA_COL = 7;

    // bonus stats
    public static final String STATS_BONUS_STRENGTH = "bonus_strength";
    public static final int    STATS_BONUS_STRENGTH_COL = 8;

    public static final String STATS_BONUS_DEXTERITY = "bonus_dexterity";
    public static final int    STATS_BONUS_DEXTERITY_COL = 9;

    public static final String STATS_BONUS_CONSTITUTION = "bonus_constitution";
    public static final int    STATS_BONUS_CONSTITUTION_COL = 10;

    public static final String STATS_BONUS_INTELLIGENCE = "bonus_intelligence";
    public static final int    STATS_BONUS_INTELLIGENCE_COL = 11;

    public static final String STATS_BONUS_WISDOM = "bonus_wisdom";
    public static final int    STATS_BONUS_WISDOM_COL = 12;


    public static final String STATS_BONUS_CHARISMA = "bonus_charisma";
    public static final int    STATS_BONUS_CHARISMA_COL = 13;

/* ******************************************************* */

    public static final String SKILLS_TABLE = "SKILLS";


    public static final String SKILLS_ID = "id";
    public static final int    SKILLS_ID_COL = 0;

    public static final String SKILLS_CHARACTER_ID = "skills_character_id";
    public static final int    SKILLS_CHARACTER_ID_COL = 1;

    // base SKILLS
    public static final String SKILLS_ATHLETICS = "athletics";
    public static final int    SKILLS_ATHLETICS_COL = 2;

    public static final String SKILLS_ACROBATICS = "acrobatics";
    public static final int    SKILLS_ACROBATICS_COL = 3;

    public static final String SKILLS_SLEIGHT_OF_HAND = "sleight_of_Hand";
    public static final int    SKILLS_SLEIGHT_OF_HAND_COL = 4;

    public static final String SKILLS_STEALTH = "stealth";
    public static final int    SKILLS_STEALTH_COL = 5;

    public static final String SKILLS_ARCANA  = "arcana";
    public static final int    SKILLS_ARCANA_COL = 6;

    public static final String SKILLS_HISTORY = "history";
    public static final int    SKILLS_HISTORY_COL = 7;

    // bonus SKILLS
    public static final String SKILLS_INVESTIGATION = "investigation";
    public static final int    SKILLS_INVESTIGATION_COL = 8;

    public static final String SKILLS_NATURE = "nature";
    public static final int    SKILLS_NATURE_COL = 9;

    public static final String SKILLS_RELIGION = "religion";
    public static final int    SKILLS_RELIGION_COL = 10;

    public static final String SKILLS_ANIMAL_HANDLING = "animal_handling";
    public static final int    SKILLS_ANIMAL_HANDLING_COL = 11;

    public static final String SKILLS_INSIGHT = "insight";
    public static final int    SKILLS_INSIGHT_COL = 12;


    public static final String SKILLS_MEDICINE = "medicine";
    public static final int    SKILLS_MEDICINE_COL = 13;

    public static final String SKILLS_PERCEPTION = "perception";
    public static final int    SKILLS_PERCEPTION_COL = 14;


    public static final String SKILLS_SURVIVAL = "survival";
    public static final int    SKILLS_SURVIVAL_COL = 15;

    public static final String SKILLS_DECEPTION = "deception";
    public static final int    SKILLS_DECEPTION_COL = 16;



    public static final String SKILLS_INTIMIDATION = "intimidation";
    public static final int    SKILLS_INTIMIDATION_COL = 17;

    public static final String SKILLS_PERFORMANCE = "performance";
    public static final int    SKILLS_PERFORMANCE_COL = 18;

    public static final String SKILLS_PERSUASION = "persuasion";
    public static final int    SKILLS_PERSUASION_COL = 19;



    public static final String CREATE_CHARACTER_TABLE =
            "CREATE TABLE " + CHARACTER_TABLE + " (" +
                    CHARACTER_ID   + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    CHARACTER_NAME  + " TEXT NOT NULL UNIQUE, " +
                    CHARACTER_CLASS  + " INTEGER NOT NULL, " +
                    CHARACTER_RACE  + " INTEGER NOT NULL, " +
                    CHARACTER_BACKGROUND+ " INTEGER NOT NULL);";

    public static final String CREATE_STATS_TABLE =
            "CREATE TABLE " + STATS_TABLE + "(" +
                    STATS_ID         + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    STATS_CHARACTER_ID    + " INTEGER , " +

                    STATS_BASE_STRENGTH      + " INTEGER , " +
                    STATS_BASE_DEXTERITY      + " INTEGER , " +
                    STATS_BASE_CONSTITUTION  + " INTEGER , " +
                    STATS_BASE_INTELLIGENCE       + " INTEGER , " +
                    STATS_BASE_WISDOM     + " INTEGER , " +
                    STATS_BASE_CHARISMA  + " INTEGER , " +

                    STATS_BONUS_STRENGTH      + " INTEGER , " +
                    STATS_BONUS_DEXTERITY      + " INTEGER ," +
                    STATS_BONUS_CONSTITUTION  + " INTEGER , " +
                    STATS_BONUS_INTELLIGENCE       + " INTEGER , " +
                    STATS_BONUS_WISDOM     + " INTEGER, " +
                    STATS_BONUS_CHARISMA    + " INTEGER);";

    public static final String CREATE_SKILLS_TABLE =
            "CREATE TABLE " + SKILLS_TABLE + "(" +
                    SKILLS_ID         + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    SKILLS_CHARACTER_ID    + " INTEGER , " +

                    SKILLS_ATHLETICS      + " INTEGER , " +
                    SKILLS_ACROBATICS      + " INTEGER , " +
                    SKILLS_SLEIGHT_OF_HAND  + " INTEGER , " +
                    SKILLS_STEALTH       + " INTEGER , " +
                    SKILLS_ARCANA     + " INTEGER , " +
                    SKILLS_HISTORY  + " INTEGER , " +


                    SKILLS_INVESTIGATION      + " INTEGER , " +
                    SKILLS_NATURE      + " INTEGER , " +
                    SKILLS_RELIGION  + " INTEGER , " +
                    SKILLS_ANIMAL_HANDLING       + " INTEGER , " +
                    SKILLS_INSIGHT     + " INTEGER , " +


                    SKILLS_MEDICINE      + " INTEGER , " +
                    SKILLS_PERCEPTION      + " INTEGER ," +
                    SKILLS_SURVIVAL      + " INTEGER ," +
                    SKILLS_DECEPTION  + " INTEGER , " +
                    SKILLS_INTIMIDATION       + " INTEGER , " +
                    SKILLS_PERFORMANCE     + " INTEGER, " +
                    SKILLS_PERSUASION    + " INTEGER);";

    public static final String DROP_CHARACTER_TABLE =
            "DROP TABLE IF EXISTS " + CHARACTER_TABLE;

    public static final String DROP_STATS_TABLE =
            "DROP TABLE IF EXISTS " + STATS_TABLE;


    public static final String DROP_SKILLS_TABLE =
            "DROP TABLE IF EXISTS " + SKILLS_TABLE;



    private static class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_CHARACTER_TABLE);
            db.execSQL(CREATE_STATS_TABLE);

            db.execSQL(CREATE_SKILLS_TABLE);
            // add skills

        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Stats list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(CharacterDB.DROP_CHARACTER_TABLE);
            db.execSQL(CharacterDB.DROP_STATS_TABLE);

            db.execSQL(CharacterDB.DROP_SKILLS_TABLE);

            db.execSQL("INSERT INTO character VALUES (1, 'Personal', 1, 1, 1)");

            onCreate(db);
        }
    }

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public CharacterDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }




    ////////////////

    public void closeDB() {
        if (db != null)
            db.close();
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    public ArrayList<CharacterInfoDB> getCharacters() {
        ArrayList<CharacterInfoDB> characters = new ArrayList<CharacterInfoDB>();
        openReadableDB();
        Cursor cursor = db.query(CHARACTER_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            CharacterInfoDB character = new CharacterInfoDB();
            character.setId(cursor.getInt(CHARACTER_ID_COL));
            character.setCharacterName(cursor.getString(CHARACTER_NAME_COL));
            character.setCharacterClass(cursor.getInt(CHARACTER_CLASS_COL));
            character.setRace(cursor.getInt(CHARACTER_RACE_COL));
            character.setBackground(cursor.getInt(CHARACTER_BACKGROUND_COL));
            characters.add(character);
        }
        closeCursor(cursor);
        closeDB();

        return characters;
    }

    public CharacterInfoDB getCharacter(String name) {
        String where = CHARACTER_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDB();
        Cursor cursor = db.query(CHARACTER_TABLE, null,
                where, whereArgs, null, null, null);
        CharacterInfoDB character = null;
        cursor.moveToFirst();
        character = new CharacterInfoDB(cursor.getInt(CHARACTER_ID_COL),
                cursor.getString(CHARACTER_NAME_COL),cursor.getInt(CHARACTER_CLASS_COL), cursor.getInt(CHARACTER_RACE_COL), cursor.getInt(CHARACTER_BACKGROUND_COL) );
        this.closeCursor(cursor);
        this.closeDB();

        return character;
    }


    private static CharacterInfoDB getCharacterInfoFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                CharacterInfoDB characterInfo = new CharacterInfoDB(
                        cursor.getInt(CHARACTER_ID_COL),
                        cursor.getString(CHARACTER_NAME_COL),
                        cursor.getInt(CHARACTER_CLASS_COL),
                        cursor.getInt(CHARACTER_RACE_COL),
                        cursor.getInt(CHARACTER_BACKGROUND_COL));

                return characterInfo;
            }
            catch(Exception e) {
                return null;
            }
        }
    }
    // for DB_ContentProvider
    public Cursor queryCharacters(String[] columns, String where,
                             String[] whereArgs, String orderBy) {
        this.openReadableDB();
        Cursor cursor = db.query(CHARACTER_TABLE, columns,
                where, whereArgs,
                null, null, orderBy);
        return cursor;
    }

    public ArrayList<String> getCharacterNames(Cursor cursor) {
        ArrayList<String> characters = new ArrayList<String>();
        openReadableDB();
        boolean check;

        while (cursor.moveToNext()) {
            String character = null;
            character = cursor.getString(CHARACTER_NAME_COL);
            characters.add(character);
        }

        closeCursor(cursor);
        closeDB();

        return characters;
    }

    public Cursor getStatsCursor(String characterName) {
        String where =
                STATS_CHARACTER_ID + "= ?;";
        //AND " +
        //STATS_HIDDEN + "!=1";

        int characterID = getCharacter(characterName).getId();
        String[] whereArgs = { Integer.toString(characterID) };

        this.openReadableDB();
        Cursor cursor = db.query(STATS_TABLE, null,
                where, whereArgs,
                null, null, null);
        return cursor;
    }


    public ArrayList<StatsDB> getStats(String characterName) {
        String where = STATS_CHARACTER_ID + "= ?;";//  AND " +
        //TASK_HIDDEN + "!=1";
        int characterID = getCharacter(characterName).getId();
        String[] whereArgs = { String.valueOf(characterID) };

        this.openReadableDB();
        Cursor cursor = db.query(STATS_TABLE, null,
                where, whereArgs,
                null, null, null);
        ArrayList<StatsDB> stats = new ArrayList<StatsDB>();
        while (cursor.moveToNext()) {
            stats.add((StatsDB) getStatsCursor(String.valueOf(cursor)));  // don't know why this is a thing
        }
        this.closeCursor(cursor);
        this.closeDB();

        return stats;
    }

    public StatsDB getStat(int id) {
        String where = STATS_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openReadableDB();
        Cursor cursor = db.query(STATS_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        StatsDB stats = getStatsFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        return stats;
    }
    private static StatsDB getStatsFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                StatsDB stats = new StatsDB(
                        cursor.getInt(STATS_ID_COL),
                        cursor.getInt(STATS_CHARACTER_ID_COL),
                        cursor.getInt(STATS_BASE_STRENGTH_COL),
                        cursor.getInt(STATS_BASE_DEXTERITY_COL),
                        cursor.getInt(STATS_BASE_CONSTITUTION_COL),
                        cursor.getInt(STATS_BASE_INTELLIGENCE_COL),
                        cursor.getInt(STATS_BASE_WISDOM_COL),
                        cursor.getInt(STATS_BASE_CHARISMA_COL),
                        cursor.getInt(STATS_BONUS_STRENGTH_COL),
                        cursor.getInt(STATS_BONUS_DEXTERITY_COL),
                        cursor.getInt(STATS_BONUS_CONSTITUTION_COL),
                        cursor.getInt(STATS_BONUS_INTELLIGENCE_COL),
                        cursor.getInt(STATS_BONUS_WISDOM_COL),
                        cursor.getInt(STATS_BONUS_CHARISMA_COL));

                return stats;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    public long insertStats(StatsDB stats) {
        ContentValues cv = new ContentValues();
        cv.put(STATS_CHARACTER_ID, stats.getCharacterId());
        cv.put(STATS_BASE_STRENGTH, stats.getBaseStrength());
        cv.put(STATS_BASE_DEXTERITY, stats.getBaseDexterity());
        cv.put(STATS_BASE_CONSTITUTION, stats.getBaseConstitution());
        cv.put(STATS_BASE_INTELLIGENCE, stats.getBaseIntelligence());
        cv.put(STATS_BASE_WISDOM, stats.getBaseWisdom());
        cv.put(STATS_BASE_CHARISMA, stats.getBaseCharisma());

        cv.put(STATS_BONUS_STRENGTH, stats.getBonusStrength());
        cv.put(STATS_BONUS_DEXTERITY, stats.getBonusDexterity());
        cv.put(STATS_BONUS_CONSTITUTION, stats.getBonusConstitution());
        cv.put(STATS_BONUS_INTELLIGENCE, stats.getBonusIntelligence());
        cv.put(STATS_BONUS_WISDOM, stats.getBonusWisdom());
        cv.put(STATS_BONUS_CHARISMA, stats.getBonusCharisma());

        this.openWriteableDB();
        long rowID = db.insert(STATS_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }




    public int updateStats(StatsDB stats) {
        ContentValues cv = new ContentValues();
        cv.put(STATS_CHARACTER_ID, stats.getCharacterId());
        cv.put(STATS_BASE_STRENGTH, stats.getBaseStrength());
        cv.put(STATS_BASE_DEXTERITY, stats.getBaseDexterity());
        cv.put(STATS_BASE_CONSTITUTION, stats.getBaseConstitution());
        cv.put(STATS_BASE_INTELLIGENCE, stats.getBaseIntelligence());
        cv.put(STATS_BASE_WISDOM, stats.getBaseWisdom());
        cv.put(STATS_BASE_CHARISMA, stats.getBaseCharisma());

        cv.put(STATS_BONUS_STRENGTH, stats.getBonusStrength());
        cv.put(STATS_BONUS_DEXTERITY, stats.getBonusDexterity());
        cv.put(STATS_BONUS_CONSTITUTION, stats.getBonusConstitution());
        cv.put(STATS_BONUS_INTELLIGENCE, stats.getBonusIntelligence());
        cv.put(STATS_BONUS_WISDOM, stats.getBonusWisdom());
        cv.put(STATS_BONUS_CHARISMA, stats.getBonusCharisma());

        String where = STATS_ID + "= ?";
        String[] whereArgs = { String.valueOf(stats.getId()) };

        this.openWriteableDB();
        int rowCount = db.update(STATS_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteStats(long id) {
        String where = STATS_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(STATS_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }



    public long insertCharacterInfo(CharacterInfoDB characterInfo) {
        ContentValues cv = new ContentValues();

        cv.put(CHARACTER_NAME, characterInfo.getCharacterName());
        cv.put(CHARACTER_CLASS, characterInfo.getCharacterClass());
        cv.put(CHARACTER_RACE, characterInfo.getRace());
        cv.put(CHARACTER_BACKGROUND, characterInfo.getBackground());

        this.openWriteableDB();
        long rowID = db.insert(CHARACTER_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }






    // methods for skills fingers crossed




    public Cursor getSkillsCursor(String characterName) {
        String where =
                SKILLS_CHARACTER_ID + "= ?;";
        //AND " +
        //SKILLS_HIDDEN + "!=1";

        int characterID = getCharacter(characterName).getId();
        String[] whereArgs = { Integer.toString(characterID) };

        this.openReadableDB();
        Cursor cursor = db.query(SKILLS_TABLE, null,
                where, whereArgs,
                null, null, null);
        return cursor;
    }



    public SkillsDB getSkill(int id) {
        String where = SKILLS_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openReadableDB();
        Cursor cursor = db.query(SKILLS_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        SkillsDB skill = getSkillsFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        return skill;
    }



    private static SkillsDB getSkillsFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                SkillsDB skill = new SkillsDB();
                int makeBool = 0;
                boolean[] skillProficiencies = new boolean[18];
                skill.setId(cursor.getInt(SKILLS_ID_COL));
                skill.setCharacterId(cursor.getInt(SKILLS_CHARACTER_ID_COL));
                skillProficiencies[0] = makeBool != cursor.getInt(SKILLS_ATHLETICS_COL);
                skillProficiencies[1] = makeBool != cursor.getInt(SKILLS_ACROBATICS_COL);
                skillProficiencies[2] = makeBool != cursor.getInt(SKILLS_SLEIGHT_OF_HAND_COL);
                skillProficiencies[3] = makeBool !=   cursor.getInt(SKILLS_STEALTH_COL);
                skillProficiencies[4] = makeBool != cursor.getInt(SKILLS_ARCANA_COL);
                skillProficiencies[5] = makeBool !=   cursor.getInt(SKILLS_HISTORY_COL);
                skillProficiencies[6] = makeBool !=  cursor.getInt(SKILLS_INVESTIGATION_COL);
                skillProficiencies[7] = makeBool !=  cursor.getInt(SKILLS_NATURE_COL);
                skillProficiencies[8] = makeBool !=   cursor.getInt(SKILLS_RELIGION_COL);
                skillProficiencies[9] = makeBool !=  cursor.getInt(SKILLS_ANIMAL_HANDLING_COL);
                skillProficiencies[10] = makeBool !=  cursor.getInt(SKILLS_INSIGHT_COL);
                skillProficiencies[11] = makeBool !=  cursor.getInt(SKILLS_MEDICINE_COL);
                skillProficiencies[12] = makeBool !=  cursor.getInt(SKILLS_PERCEPTION_COL);
                skillProficiencies[13] = makeBool != cursor.getInt(SKILLS_SURVIVAL_COL);
                skillProficiencies[14] = makeBool !=  cursor.getInt(SKILLS_DECEPTION_COL);
                skillProficiencies[15] = makeBool != cursor.getInt(SKILLS_INTIMIDATION_COL);
                skillProficiencies[16] = makeBool !=  cursor.getInt(SKILLS_PERFORMANCE_COL);
                skillProficiencies[17] = makeBool !=  cursor.getInt(SKILLS_PERSUASION_COL);
                skill.setSkillProficiencies(skillProficiencies);
                return skill;
            }
            catch(Exception e) {
                return null;
            }
        }
    }
    public long insertSkills(SkillsDB skills) {
        ContentValues cv = new ContentValues();
        boolean[] skillProficiencies = skills.getSkillProficiencies();

        cv.put(SKILLS_CHARACTER_ID, skills.getCharacterId());
        cv.put(SKILLS_ATHLETICS, skillProficiencies[0]);
        cv.put(SKILLS_ACROBATICS, skillProficiencies[1]);
        cv.put(SKILLS_SLEIGHT_OF_HAND, skillProficiencies[2]);
        cv.put(SKILLS_STEALTH, skillProficiencies[3]);
        cv.put(SKILLS_ARCANA, skillProficiencies[4]);
        cv.put(SKILLS_HISTORY, skillProficiencies[5]);

        cv.put(SKILLS_INVESTIGATION, skillProficiencies[6]);
        cv.put(SKILLS_NATURE, skillProficiencies[7]);
        cv.put(SKILLS_RELIGION, skillProficiencies[8]);
        cv.put(SKILLS_ANIMAL_HANDLING, skillProficiencies[9]);
        cv.put(SKILLS_INSIGHT, skillProficiencies[10]);
        cv.put(SKILLS_MEDICINE, skillProficiencies[11]);


        cv.put(SKILLS_PERCEPTION, skillProficiencies[12]);
        cv.put(SKILLS_SURVIVAL, skillProficiencies[13]);
        cv.put(SKILLS_DECEPTION, skillProficiencies[14]);
        cv.put(SKILLS_INTIMIDATION, skillProficiencies[15]);
        cv.put(SKILLS_PERFORMANCE, skillProficiencies[16]);
        cv.put(SKILLS_PERSUASION, skillProficiencies[17]);

        this.openWriteableDB();
        long rowID = db.insert(SKILLS_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }


    public int updateSkills(SkillsDB skills){
    ContentValues cv = new ContentValues();
        boolean[] skillProficiencies = skills.getSkillProficiencies();

        cv.put(SKILLS_CHARACTER_ID, skills.getCharacterId());
        cv.put(SKILLS_ATHLETICS, skillProficiencies[0]);
        cv.put(SKILLS_ACROBATICS, skillProficiencies[1]);
        cv.put(SKILLS_SLEIGHT_OF_HAND, skillProficiencies[2]);
        cv.put(SKILLS_STEALTH, skillProficiencies[3]);
        cv.put(SKILLS_ARCANA, skillProficiencies[4]);
        cv.put(SKILLS_HISTORY, skillProficiencies[5]);

        cv.put(SKILLS_INVESTIGATION, skillProficiencies[6]);
        cv.put(SKILLS_NATURE, skillProficiencies[7]);
        cv.put(SKILLS_RELIGION, skillProficiencies[8]);
        cv.put(SKILLS_ANIMAL_HANDLING, skillProficiencies[9]);
        cv.put(SKILLS_INSIGHT, skillProficiencies[10]);
        cv.put(SKILLS_MEDICINE, skillProficiencies[11]);


        cv.put(SKILLS_PERCEPTION, skillProficiencies[12]);
        cv.put(SKILLS_SURVIVAL, skillProficiencies[13]);
        cv.put(SKILLS_DECEPTION, skillProficiencies[14]);
        cv.put(SKILLS_INTIMIDATION, skillProficiencies[15]);
        cv.put(SKILLS_PERFORMANCE, skillProficiencies[16]);
        cv.put(SKILLS_PERSUASION, skillProficiencies[17]);

    String where = SKILLS_ID + "= ?";
    String[] whereArgs = {String.valueOf(skills.getId())};

        this.openWriteableDB();
        int rowCount = db.update(SKILLS_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
        }


    public int deleteSkills(long id) {
        String where = SKILLS_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(SKILLS_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteStates(long id) {
        String where = STATS_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(STATS_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteCharacter(long id) {
        String where = CHARACTER_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(CHARACTER_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }



    // Returns an ArrayList of Names from table Characters
    public ArrayList<String> getCharacterNames() {
        ArrayList<String> characters = new ArrayList<String>();
        openReadableDB();
        Cursor cursor = db.query(CHARACTER_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String character = null;
            character = cursor.getString(CHARACTER_NAME_COL);
            characters.add(character);
        }
        closeCursor(cursor);
        closeDB();

        return characters;
    }

    public int getCharacterId(String name) {
        String where = CHARACTER_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDB();
        Cursor cursor = db.query(CHARACTER_TABLE, null,
                where, whereArgs, null, null, null);
        int characterId = 0;
        cursor.moveToFirst();
        characterId = (cursor.getInt(CHARACTER_ID_COL));

        this.closeCursor(cursor);
        this.closeDB();

        return characterId;
    }


}
