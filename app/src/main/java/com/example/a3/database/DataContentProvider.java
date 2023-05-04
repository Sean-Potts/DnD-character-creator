package com.example.a3.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.a3.CharacterInfo;
/*
Name: DataContentProvider
Purpose: This class extends the ContentProvider and interacts with the database allowing for deleting, saving
         and getting back getting back all the tables from the database.
 */
public class DataContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.a3.DataContentProvider";

    public static final int NO_MATCH = -1;

    public static final int ALL_CHACATERS_URI = 0;

    public static final int SINGLE_CHACATER_URI = 1;


    private CharacterDB db = null;
    private DataAccessor acessor = null;

    private UriMatcher uriMatcher = null;





    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)) {
            case ALL_CHACATERS_URI:
                return "vnd.android.cursor.dir/vnd.a3.characterlist.character";
            case SINGLE_CHACATER_URI:
                return "vnd.android.cursor.item/vnd.a3.characterlist.character";
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }

    public boolean onCreate() {
        db = new CharacterDB(getContext());
        acessor = new DataAccessor(db);

        uriMatcher = new UriMatcher(NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "characters", ALL_CHACATERS_URI);
        uriMatcher.addURI(AUTHORITY, "characters/#", SINGLE_CHACATER_URI);
        return true;
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case ALL_CHACATERS_URI:

                return db.queryCharacters(projection, selection,
                        selectionArgs, sortOrder);
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }




    @Override
    public Uri insert(Uri uri, ContentValues values) {

        switch (uriMatcher.match(uri)) {
            case ALL_CHACATERS_URI:

                long insertId = acessor.addCharacter(new CharacterInfo(values));

                getContext().getContentResolver().notifyChange(uri, null);

                return uri.buildUpon().appendPath(
                        String.valueOf(insertId)).build();
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }





    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updateCount = 0;
        switch (uriMatcher.match(uri)) {
            case SINGLE_CHACATER_URI:

                String taskId = uri.getLastPathSegment();

                CharacterInfo Character = new CharacterInfo(values);

                updateCount = acessor.addCharacter(Character);

                getContext().getContentResolver().notifyChange(uri, null);
                return updateCount;
            case ALL_CHACATERS_URI:

                return updateCount;
            default:
                throw new UnsupportedOperationException("URI " + uri + " is not supported");
        }
    }
    @Override
    public int delete(Uri uri, String selection,
                      String[] selectionArgs) {

        int deleteCount = 0;

        deleteCount = acessor.deleteCharacter(selection);

        getContext().getContentResolver().notifyChange(uri, null);
        return deleteCount;
    }
}


