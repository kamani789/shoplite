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
public class OrderDetailsDBAdapter {
public static final boolean DEBUG = true;

	
	public static final String LOG_TAG = "OrderDetailsDBAdapter";

	
	public static final String KEY_ID = "id";
	
	public static final String KEY_CART_ID = "cartid";

	public static final String KEY_USER_NAME = "username";

	public static final String KEY_USER_EMAIL = "useremail";

	public static final String KEY_USER_PRODUCT = "productname";
	
	
	
	

	public static final String KEY_PRODUCT_QUANTITY = "quantity";

	public static final String KEY_ORDER_DATE = "orderdate";
	
	public static final String KEY_ORDER_STATUS="orderstatus";

	
	public static final String DATABASE_NAME = "DB_sqllite_shop";


	public static final int DATABASE_VERSION = 19;// started at 1

	
	public static final String ORDER_TABLE = "table_order_details";
	
	/*public static final String USER_TABLE = "tbl_user_accounts";*/

	
	private static final String[] ALL_TABLES = { ORDER_TABLE};

	
	private static final String ORDER_CREATE = "create table table_order_details(id integer primary key autoincrement,cartid integer, username text not null, useremail text not null,productname text not null,quantity text not null,orderdate text not null,orderstatus text not null);";


	private static DataBaseHelper DBHelper = null;
	public OrderDetailsDBAdapter(Context context) {
		System.out.println("inside databse phone creation");
	    init(context);
	}


	public static void init(Context context) {
		if (DBHelper == null) {

			System.out.println("inside db helper null");
			if (DEBUG) {
				Log.i("OrderDetailsDBAdapter", context.toString());

				Log.i("OrderDetailsDBAdapter", "before calling creation of database");
				DBHelper = new DataBaseHelper(context);
			}

		}
	}

	/********************** Main Database creation INNER class ********************/
	private static class DataBaseHelper extends SQLiteOpenHelper {
		public DataBaseHelper(Context context) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);

			System.out.println("inside database helper of order table creation");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			if (DEBUG)
				Log.i(LOG_TAG, "new create");
			try {
				System.out.println("inside oncreate of table in try");
				db.execSQL(ORDER_CREATE);
				System.out.println("after creating the table name");

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
	public static long isdeleted(String name,String mail)
	{
		System.out.println("inside adapter deleting");
		long status = 0;
		final SQLiteDatabase db = open();
		String query = "delete  from " + ORDER_TABLE +" where  useremail='"+mail+"' and productname='"+name+"' and orderstatus='"+"Added"+"'";		
		Cursor cursor=db.rawQuery(query, null);
		System.out.println("rows deleted are"+cursor.getCount());
		System.out.println("after deleting status is-->>"+status);
		return status;
		
	}
	public static long isupdated(String name,String mail)
	{
		System.out.println("inside adapter deleting");
		long status = 0;
		final SQLiteDatabase db = open();
		String query = "update  " + ORDER_TABLE +"  set  orderstatus='Shipped' where  useremail='"+mail+"' and productname='"+name+"' and orderstatus='"+"Added"+"'";		
		Cursor cursor=db.rawQuery(query, null);
		System.out.println("rows updated are"+cursor.getCount());
		System.out.println("after updating status is-->>"+status);
		return status;
		
	}
	public static long addOrderData(Orderdata uData) {
		long status = 0;
		final SQLiteDatabase db = open();

		String name = sqlEscapeString(uData.getUsername());
		String cartid=sqlEscapeString("1");
		String email = sqlEscapeString(uData.getUseremail());
		String productname = sqlEscapeString(uData.getProductname());
		String date = sqlEscapeString(uData.getOrderdate());
		String quantity = sqlEscapeString(uData.getQuantity());
		String orderstatus=sqlEscapeString(uData.getStatus());		
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_CART_ID, cartid);
		cVal.put(KEY_USER_NAME, name);
		cVal.put(KEY_USER_EMAIL, email);
		cVal.put(KEY_USER_PRODUCT , productname);
		cVal.put(KEY_PRODUCT_QUANTITY , quantity);
		cVal.put(KEY_ORDER_DATE , date);
		cVal.put(KEY_ORDER_STATUS , orderstatus);
		
		status = db.insert(ORDER_TABLE, null, cVal);

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
		if(status!=0)
			System.out.println("succesfully inserted the data into order table");
		return status;
	}

	// Getting single product
	public static List<Orderdata> getOrderData(String id,String status) {
		System.out.println("inside get ORDERdara method");
		List<Orderdata> data = new ArrayList<Orderdata>();
		
		final SQLiteDatabase db = open();
		String query = "select * from " + ORDER_TABLE +" where useremail='"+id+"' and orderstatus='"+"Added"+"'";
		
		Cursor cursor = db.rawQuery(query, null);
		System.out.println("inside cursor not null"+cursor.getCount());
		if (cursor.moveToFirst()) {
			do {
			Orderdata od=new Orderdata();
			
			System.out.println("second column name is"+cursor.getColumnName(1));
			System.out.println("third column name is"+cursor.getColumnName(2));				
			od.setId(Integer.parseInt(cursor.getString(0)));
			od.setCartid(Integer.parseInt(cursor.getString(1)));
			od.setUsername(cursor.getString(2));
			od.setUseremail(cursor.getString(3));
			od.setProductname(cursor.getString(4));
			od.setOrderdate(cursor.getString(5));
			od.setQuantity(cursor.getString(6));
			od.setStatus(cursor.getString(7));
			
			data.add(od);
			} while (cursor.moveToNext());
		}
		// return contact
		return data;
	}

	// Getting All Contacts
	/*public static List<UserData> getAllUserData() {
		System.out.println("inside get all user data");
		List<UserData> contactList = new ArrayList<UserData>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + USER_TABLE;

		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(selectQuery, null);
		System.out.println("cursor length in get user data is"+cursor.getCount());
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
	}*/

/*	public static Orderdata getdata(String id) {

		Orderdata data = new Orderdata();
		String selectQuery = "SELECT  * FROM " + ORDER_TABLE + " where id=" + id;

		final SQLiteDatabase db = open();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor != null) {

			data.setId(Integer.parseInt(cursor.getString(0)));
			data.setUsername(cursor.getString(1));
			data.setUseremail(cursor.getString(2));
			data.setProductname(cursor.getString(3));
			data.setQuantity(cursor.getString(4));
			data.setOrderdate(cursor.getString(5));

		}
		return data;
	}*/

/*	// Updating single contact
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
	}*/

	// Deleting single contact
	/*public static void deleteUserData(UserData data) {
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
	}*/

}
