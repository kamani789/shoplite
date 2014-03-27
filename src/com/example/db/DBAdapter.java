package com.example.db;

import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View.OnCreateContextMenuListener;

public class DBAdapter {


	public static final boolean DEBUG = true;

	
	public static final String LOG_TAG = "DBAdapter";

	
	public static final String KEY_ID = "id";

	public static final String KEY_USER_NAME = "username";

	public static final String KEY_USER_EMAIL = "useremail";

	public static final String KEY_USER_PASSWORD = "password";

	public static final String KEY_USER_Role = "Role";

	public static final String KEY_USER_STATUS = "Account_status";

	
	public static final String DATABASE_NAME = "DB_sqllite_shop";


	public static final int DATABASE_VERSION = 1;// started at 1

	
	public static final String USER_TABLE = "tbl_user_accounts";

	
	private static final String[] ALL_TABLES = { USER_TABLE };

	
	private static final String USER_CREATE = "create table tbl_user_accounts(id integer primary key autoincrement, username text not null, useremail text not null,password text not null,Role text not null,Account_status text not null);";


	private static DataBaseHelper DBHelper = null;

	protected DBAdapter() {
	}


	public static void init(Context context) {
		if (DBHelper == null) {

			System.out.println("inside db helper null");
			if (DEBUG) {
				Log.i("DBAdapter", context.toString());

				Log.i("DBAdapter", "before calling creation of database");
				DBHelper = new DataBaseHelper(context);
			}

		}
	}

	/********************** Main Database creation INNER class ********************/
	private static class DataBaseHelper extends SQLiteOpenHelper {
		public DataBaseHelper(Context context) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);

			System.out.println("inside database helper");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			if (DEBUG)
				Log.i(LOG_TAG, "new create");
			try {
				System.out.println("inside oncreate of table in try");
				db.execSQL(USER_CREATE);

			} catch (Exception exception) {
				if (DEBUG)
					Log.i(LOG_TAG, "Exception onCreate() exception");
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if (DEBUG)
				Log.w(LOG_TAG, "Upgrading database from version" + oldVersion
						+ "to" + newVersion + "...");

			for (String table : ALL_TABLES) {
				db.execSQL("DROP TABLE IF EXISTS " + table);
			}
			onCreate(db);
		}

	} // Inner class closed


	private static synchronized SQLiteDatabase open() throws SQLException {
		return DBHelper.getWritableDatabase();
	}


	private static String sqlEscapeString(String aString) {
		String aReturn = "";

		if (null != aString) {
			// aReturn = aString.replace("'", "''");
			aReturn = DatabaseUtils.sqlEscapeString(aString);
			// Remove the enclosing single quotes ...
			aReturn = aReturn.substring(1, aReturn.length() - 1);
		}

		return aReturn;
	}


	private static String sqlUnEscapeString(String aString) {

		String aReturn = "";

		if (null != aString) {
			aReturn = aString.replace("''", "'");
		}

		return aReturn;
	}
	public static long addUserData(UserData uData) {
		long status = 0;
		final SQLiteDatabase db = open();

		String name = sqlEscapeString(uData.getName());
		String email = sqlEscapeString(uData.getEmail());
		String password = sqlEscapeString(uData.getPassword());
		String Role = "Admin";
		String Account_status = "A";
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_USER_NAME, name);
		cVal.put(KEY_USER_EMAIL, email);
		cVal.put(KEY_USER_PASSWORD, password);
		cVal.put(KEY_USER_Role, Role);
		cVal.put(KEY_USER_STATUS, Account_status);
		status = db.insert(USER_TABLE, null, cVal);

		/*
		 * Cursor cursor = getReadableDatabase().
		 * rawQuery("SELECT * FROM dbname.sqlite_master WHERE type='table'");
		 * if(cursor!=null) {
		 * System.out.println("inside if of cursor not null");
		 * 
		 * }
		 * 
		 * else System.out.println("in side cursor null");
		 */

		db.close(); // Closing database connection
		return status;
	}

	// Getting single contact
	public static UserData getUserData(String id) {
		System.out.println("inside get userdara method");
		UserData data = null;
		final SQLiteDatabase db = open();
		String query = "select * from " + USER_TABLE + " where id =" + id;

		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null) {
			cursor.moveToFirst();

			data = new UserData(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getString(4),
					cursor.getString(5));

		}
		// return contact
		return data;
	}

	// Getting All Contacts
	public static List<UserData> getAllUserData() {
		List<UserData> contactList = new ArrayList<UserData>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + USER_TABLE;

		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				UserData data = new UserData();
				data.setID(Integer.parseInt(cursor.getString(0)));
				data.setName(cursor.getString(1));
				data.setEmail(cursor.getString(2));
				data.setPassword(cursor.getString(3));
				data.setRole(cursor.getString(4));
				data.setAccount_status(cursor.getString(5));

				// Adding contact to list
				contactList.add(data);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	public static UserData getdata(String id) {

		UserData data = new UserData();
		String selectQuery = "SELECT  * FROM " + USER_TABLE + " where id=" + id;

		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor != null) {

			data.setID(Integer.parseInt(cursor.getString(0)));
			data.setName(cursor.getString(1));
			data.setEmail(cursor.getString(2));
			data.setPassword(cursor.getString(3));
			data.setRole(cursor.getString(4));
			data.setAccount_status(cursor.getString(5));

		}
		return data;
	}

	// Updating single contact
	public static int updateUserData(UserData data) {
		final SQLiteDatabase db = open();

		ContentValues values = new ContentValues();
		values.put(KEY_USER_NAME, data.getName());
		values.put(KEY_USER_EMAIL, data.getEmail());
		values.put(KEY_USER_PASSWORD, data.getPassword());
		values.put(KEY_USER_Role, data.getRole());
		values.put(KEY_USER_STATUS, data.getAccount_status());

		// updating row
		return db.update(USER_TABLE, values, KEY_ID + " = ?",
				new String[] { String.valueOf(data.getID()) });
	}

	// Deleting single contact
	public static void deleteUserData(UserData data) {
		final SQLiteDatabase db = open();
		db.delete(USER_TABLE, KEY_ID + " = ?",
				new String[] { String.valueOf(data.getID()) });
		db.close();
	}

	// Getting contacts Count
	public static int getUserDataCount() {
		String countQuery = "SELECT  * FROM " + USER_TABLE;
		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
