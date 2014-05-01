package com.example.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;

import com.example.actionbar.R;


public class PhoneDatabaseclass {

	
	private static final String TAG = "PhoneDatabase";

//The columns we'll include in the dictionary table
public static final String COL_WORD = "WORD";
public static final String COL_DEFINITION = "DEFINITION";

private static final String DATABASE_NAME = "DB_sqllite_shop";
private static final String FTS_VIRTUAL_TABLE = "PHONE";
private static final int DATABASE_VERSION = 19;

private static DatabaseOpenHelper mDatabaseOpenHelper;

public PhoneDatabaseclass(Context context) {
	System.out.println("inside databse phone creation");
    mDatabaseOpenHelper = new DatabaseOpenHelper(context);
   
}

private static class DatabaseOpenHelper extends SQLiteOpenHelper {

    private final Context mHelperContext;
    private SQLiteDatabase mDatabase;

    private static final String FTS_TABLE_CREATE =
                "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE +
                " USING fts3 (" +
                COL_WORD + ", " +
                COL_DEFINITION + ")";

    DatabaseOpenHelper(Context context) {
   
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
     	System.out.println("inside database open helper");
        mHelperContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	System.out.println("inside on create of database");
        mDatabase = db;
        mDatabase.execSQL(FTS_TABLE_CREATE);
        addWord("RAM", "RandomAccessMemory");
        addWord("TRA", "TryReachAchieve");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
        onCreate(db);
    }
    private void loadDictionary() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    loadWords();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    private void loadWords() throws IOException {
    	System.out.println("inside adding words");
        final Resources resources = mHelperContext.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.phones);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "-");
                if (strings.length < 2) continue;
                long id = addWord(strings[0].trim(), strings[1].trim());
                if (id < 0) {
                    System.out.println("unable to add word: " + strings[0].trim());
                }
            }
        } finally {
            reader.close();
        }
    }
    public long addWord(String word, String definition) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_WORD, word);
        initialValues.put(COL_DEFINITION, definition);

        return mDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
    }
    
}
public Cursor getWordMatches(String query, String[] columns) {
	
	System.out.println("inside get word matches");
    String selection = COL_WORD + " MATCH ?";
    String[] selectionArgs = new String[] {"*"+query+"*"};
    
    return query(selection, selectionArgs, columns);
}

public Cursor query(String selection, String[] selectionArgs, String[] columns) {
	System.out.println("inside query returning cursor");
    SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
    builder.setTables(FTS_VIRTUAL_TABLE);

    Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(),
            columns, selection, selectionArgs, null, null, null);

    if (cursor == null) {
        return null;
    } else if (!cursor.moveToFirst()) {
        cursor.close();
        return null;
    }
    return cursor;
}


}
