package com.example.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.actionbar.R;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;

public class DatabaseTable {
	
	private static final String TAG = "DictionaryDatabase";

//The columns we'll include in the dictionary table
public static final String COL_WORD = "WORD";
public static final String COL_DEFINITION = "DEFINITION";

private static final String DATABASE_NAME = "DB_sqllite_shop";
private static final String FTS_VIRTUAL_TABLE = "FTS";
private static final int DATABASE_VERSION = 19;

public static DatabaseOpenHelper mDatabaseOpenHelper;

public DatabaseTable(Context context) {
    mDatabaseOpenHelper = new DatabaseOpenHelper(context);
   /*mDatabaseOpenHelper.addWord("mobile 32GB iphone ", "iphone4s");
   mDatabaseOpenHelper.addWord("video game ", "Xbox360");
   mDatabaseOpenHelper.addWord("video game ", "Playstation 4");*/
   mDatabaseOpenHelper.addWord("appliances black and decker juicer", "black and decker juicer");
   mDatabaseOpenHelper.addWord("appliances Breville Juicer", "Breville Juicer");
   mDatabaseOpenHelper.addWord("appliances Hamilton Electric Kettle", "Hamilton Electric Kettle");
   mDatabaseOpenHelper.addWord("appliances Mr.Coffee Coffee Maker", "Mr.Coffee Coffee Maker");
   mDatabaseOpenHelper.addWord("appliances Toastess Sandwich Grill", "Toastess Sandwich Grill");
   mDatabaseOpenHelper.addWord("appliances Panasonic Toaster Oven", "Panasonic Toaster Oven");
   mDatabaseOpenHelper.addWord("appliances Hamilton Electric Kettle", "Hamilton Electric Kettle");
   mDatabaseOpenHelper.addWord("appliances Oster Rice Cooker", "Oster Rice Cooker");
   mDatabaseOpenHelper.addWord("appliances Panasonic Rice Cooker", "Panasonic Rice Cooker");
   mDatabaseOpenHelper.addWord("appliances Cuisinart Chopper", "Cuisinart Chopper");
   mDatabaseOpenHelper.addWord("appliances Proctor Hand Mixer", "Proctor Hand Mixer");
   mDatabaseOpenHelper.addWord("appliances Hoover Vaccuum", "Hoover Vaccuum");
	mDatabaseOpenHelper.addWord("appliances Shark Vaccuum", "Shark Vaccuum");
    mDatabaseOpenHelper.addWord("appliances Haier Refridgerator", "Haier Refridgerator");
    mDatabaseOpenHelper.addWord("appliances Honeywell TurboForce Fan", "Honeywell TurboForce Fan");
    mDatabaseOpenHelper.addWord("appliances ESTEAM Steamer", "ESTEAM Steamer");
    mDatabaseOpenHelper.addWord("appliances NewAir Wine Cooler", "NewAir Wine Cooler");
    mDatabaseOpenHelper.addWord("appliances Nesco Cooktop", "Nesco Cooktop");
    mDatabaseOpenHelper.addWord("appliances NewAir Ice Maker", "NewAir Ice Maker");
    mDatabaseOpenHelper.addWord("appliances Sunbeam Microwave", "Sunbeam Microwave");
    mDatabaseOpenHelper.addWord("mobile smart phones Nokia Lumia 520 Dual Core 1GHz Processor 5MP Camera 4 inch WVGA LCD display 4G Windows 8 ", "Nokia Lumia 520");
    mDatabaseOpenHelper.addWord("mobile smart phones Motorola DROID X USB Bluetooth WiFi Mobile Hotspot 4 inch Capacitive Touch Screen ", "Motorola DROID X");
    mDatabaseOpenHelper.addWord("mobile smart phones Samsung Galaxy Victory 4G LTE Android Jelly Bean Dual Core Processor 4 inch Touchscreen Display 5MP Camera", "Samsung Galaxy Victory");
    mDatabaseOpenHelper.addWord("mobile smart phones LG Optimus F3 4 inch display 5MP camera Jelly Bean android", "LG Optimus F3");
    mDatabaseOpenHelper.addWord("mobile Alcatel One Evolve 4 inch Touch Screen 5MP Camera Jelly Bean Operating System", "Alcatel One Evolve");
    mDatabaseOpenHelper.addWord("mobile Kyocera Hydro Android Waterproof 3 Inch Touchscreen Display WiFi GPS 3MP Camera with Flash", "Kyocera Hydro");
    mDatabaseOpenHelper.addWord("mobile smart phones Apple Iphone 4s Black 16GB Black GSM A4 Processor WiFi Bluetooth GPS", "Apple Iphone 4s Black");
    mDatabaseOpenHelper.addWord("mobile smart phones Apple Iphone 5s Black 16GB White 8MP Camera True Tone flash Autofocus Face detection", "Apple Iphone 5s");
    mDatabaseOpenHelper.addWord("mobile LG G FLEX Black Vertically Curved Display Curved battery Ultra slim", "LG G FLEX");
    mDatabaseOpenHelper.addWord("mobile smart phones Samsung Galaxy S4 Black 13MP Camera 16GB", "Samsung Galaxy S4");
    mDatabaseOpenHelper.addWord("mobile smart phones Samsung Galaxy S3 White 16GB 8MP Camera Android ", "Samsung Galaxy S3");
    mDatabaseOpenHelper.addWord("mobile Generic Dual Sim White 4 Inch Display Android Dual Sim ", "Generic Dual Sim Phone");
    mDatabaseOpenHelper.addWord("video game Mario Kart 8 Nintendo Wii U New ", "Mario Kart 8");
    mDatabaseOpenHelper.addWord("video game Grand Theft Auto V Xbox 360 New ", "Grand Theft Auto");
    mDatabaseOpenHelper.addWord("video game FIFA 14 Xbox 360 New ", "FIFA 14");
    mDatabaseOpenHelper.addWord("video game Battlefield 4 Xbox 360 Used ", "Battlefield 4");
    mDatabaseOpenHelper.addWord("video game Titanfall Xbox 360 New ", "Titanfall");
    mDatabaseOpenHelper.addWord("video game Thief Xbox 360 New ", "Thief");
    mDatabaseOpenHelper.addWord("video game The LEGO Movie Videogame Wii U Used ", "Lego Game");
    mDatabaseOpenHelper.addWord("video game ZombiU Nintendo Wii U Used ", "ZombiU");
    mDatabaseOpenHelper.addWord("beauty product Pikmin 3 Wii U New ", "Pikmin 3");
    mDatabaseOpenHelper.addWord("beauty perfume Armani Code By Giorgio Armani For Men Eau De Toilette Spray 4Oz New ", "Armani for Men");
    mDatabaseOpenHelper.addWord("beauty bath LOreal Paris Go 360 Clean Deep Exfoliating Scrub Natural Apricot Beads 6 Fluid Ounce New", "LOreal Scrub");
    mDatabaseOpenHelper.addWord("beauty body wash Ultra Moisture Moisturizing Body Wash With Shea Butter 13Oz New ", "Olay Body Wash");
    mDatabaseOpenHelper.addWord("beauty sun screen lotion Nivea For Men Active3 Body Wash for Body Hair and Shave New ", "Nivea for Men");
    mDatabaseOpenHelper.addWord("beauty product Donkey Kong Country Tropical Freeze Nintendo Wii U Used ", "Donkey Kong");
    
    
    
    
  
   
    
    //delete();
}

private void delete() {
	SQLiteDatabase db = mDatabaseOpenHelper.getWritableDatabase();
    try {
        db.isOpen();           //should be added
    } catch (SQLException sqle) {
        System.out.println("exeception in deleting");
    } 
        db.delete(FTS_VIRTUAL_TABLE, null, null);
       System.out.println("after all data got deleted from database");
	
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
        mHelperContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	System.out.println("inside on create of database");
        mDatabase = db;
        mDatabase.execSQL(FTS_TABLE_CREATE);
       
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
        InputStream inputStream = resources.openRawResource(R.raw.resour);
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
    	System.out.println("inside adding new values into database table");
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_WORD, word);
        initialValues.put(COL_DEFINITION, definition);
        final SQLiteDatabase mDatabase=getReadableDatabase();
        System.out.println("mDatabase"+mDatabase.toString());
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
    builder.setDistinct(true);
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

public Cursor getDepartmentMatches(String query, String[] columns) {
	
	System.out.println("inside get word matches");
    String selection = COL_WORD + " MATCH ?";
    String[] selectionArgs = new String[] {"*"+query+"*"};
    
    return departmentquery(selection, selectionArgs, columns);
}

public Cursor departmentquery(String selection, String[] selectionArgs, String[] columns) {
	System.out.println("inside query returning cursor");
    SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
    builder.setTables(FTS_VIRTUAL_TABLE);
    builder.setDistinct(true);
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
