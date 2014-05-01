package com.example.db;
import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View.OnCreateContextMenuListener;
public class CartAdapter {
public static final boolean DEBUG = true;

	
	public static final String LOG_TAG = "CartAdapter";

	
	public static final String KEY_ID = "id";

	public static final String KEY_PRODUCT_NAME = "Productname";	

	public static final String KEY_PRODUCT_DESC = "productdesc";
	
	public static final String KEY_PRODUCT_IMAGE = "productimage";

	public static final String KEY_PRODUCT_CATEGORY = "category";

	
	public static final String KEY_PRODUCT_PRICE = "price";
	
	public static final String KEY_PRODUCT_SELLER = "seller";
	
	public static final String KEY_PRODUCT_CONDITION = "condition";

	
	
	public static final String DATABASE_NAME = "DB_sqllite_shop";


	public static final int DATABASE_VERSION = 11;// started at 1

	
	public static final String PRODUCT_TABLE = "tbl_product_details";
	
	/*public static final String USER_TABLE = "tbl_user_accounts";*/

	
	private static final String[] ALL_TABLES = { PRODUCT_TABLE};

	
	private static final String PRODUCT_CREATE = "create table tbl_product_details(id integer primary key autoincrement, Productname text not null, productdesc text not null,productimage text not null,category text not null,price text not null,seller text ot null,condition text not null);";


	private static DataBaseHelper DBHelper = null;
	CartAdapter()
	{
		
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

			System.out.println("inside database helper");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			if (DEBUG)
				Log.i(LOG_TAG, "new create");
			try {
				System.out.println("inside oncreate of table in try");
				db.execSQL(PRODUCT_CREATE);

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
	public static long addProductData(ProductData uData) {
		long status = 0;
		final SQLiteDatabase db = open();

		String Productname	 = sqlEscapeString(uData.getProductname());
		String Productdesc	 = sqlEscapeString(uData.getProductdesc());
		String productimage = sqlEscapeString(uData.getProductimage());
		String category = sqlEscapeString(uData.getCategory());
		String price = sqlEscapeString(uData.getPrice());
		String seller=sqlEscapeString(uData.getSeller());
		String condition=sqlEscapeString(uData.getCondition());
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_PRODUCT_NAME , Productname);
		cVal.put(KEY_PRODUCT_DESC, Productdesc);
		cVal.put(KEY_PRODUCT_IMAGE , productimage);
		cVal.put(KEY_PRODUCT_CATEGORY , category);
		cVal.put(KEY_PRODUCT_PRICE , price);
		cVal.put(KEY_PRODUCT_SELLER , seller);
		cVal.put(KEY_PRODUCT_CONDITION , condition);
		status = db.insert(PRODUCT_TABLE, null, cVal);

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

	// Getting single contact
	public static ProductData getOrderData(String id) {
		System.out.println("inside get ORDERdara method");
		ProductData data = null;
		final SQLiteDatabase db = open();
		String query = "select * from " + PRODUCT_TABLE + " where id =" + id;

		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null) {
			cursor.moveToFirst();

			data = new ProductData(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getString(4),
					cursor.getString(5),cursor.getString(6), cursor.getString(7));

		}
		// return contact
		return data;
	}
}
