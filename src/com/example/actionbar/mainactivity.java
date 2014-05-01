package com.example.actionbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.db.DBAdapter;
import com.example.db.OrderDetailsDBAdapter;
import com.example.search.DatabaseTable;
import com.example.search.PhoneDatabaseclass;
import com.example.serviceclasses.ProductList;

public class mainactivity extends Activity implements OnClickListener {
	
	 
	

	
	public static String myshared = "myshared";
	SharedPreferences shprefs;
	Editor edit;
	int imageid;
	int id;
	String pName,pimage,pdesc,pbrand;
	String pPrice;
	DatabaseTable  db;
	PhoneDatabaseclass ph;
	OrderDetailsDBAdapter oradap;
	DBAdapter user;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);
		 db = new DatabaseTable(getApplicationContext());
		ph=new PhoneDatabaseclass(getApplicationContext());	
		oradap=new OrderDetailsDBAdapter(getApplicationContext());
		user=new DBAdapter();
		handleIntent(getIntent());
		Button b2 = (Button) findViewById(R.id.shopbydept);
		b2.setOnClickListener(this);
		TextView tv1 = (TextView) findViewById(R.id.accnt);
		tv1.setOnClickListener(this);
		TextView tv = (TextView) findViewById(R.id.signin);
		shprefs = getSharedPreferences(myshared, 0);
		String name = shprefs.getString("name", "Sign in");
		String email=shprefs.getString("email", "default one in main");
		System.out.println("email id in main activity is"+email);
		System.out.println("before printing shared data name");
		System.out.println(name);
		tv.setText(name);
		if (!tv.getText().toString().equalsIgnoreCase("Sign in")) {
			tv1.setText("Your Account");
			tv.setClickable(false);
			tv.setEnabled(false);
		}
		tv.setOnClickListener(this);
		final TableLayout lm = (TableLayout) findViewById(R.id.linearMain);
		final TableLayout lm1 = (TableLayout) findViewById(R.id.linearsecond);
		final TableLayout lm2 = (TableLayout) findViewById(R.id.linearselling);
		TableRow tr = (TableRow) findViewById(R.id.trset);
		TableRow tr1 = (TableRow) findViewById(R.id.trset1);
		TableRow tr2 = (TableRow) findViewById(R.id.trset2);
		TableRow tr4 = (TableRow) findViewById(R.id.trset4);
		TableRow tr5 = (TableRow) findViewById(R.id.trset5);
		TableRow tr6 = (TableRow) findViewById(R.id.trset6);
		TableRow tr7=(TableRow)findViewById(R.id.trsetv);
		TableRow tr8=(TableRow)findViewById(R.id.trsetv1);
		TableRow tr9=(TableRow)findViewById(R.id.trsetv2);
		lm.setOrientation(TableLayout.HORIZONTAL);
		/* final Button secondBtn = (Button) findViewById(R.id.second); */

		// Get Global Controller Class object (see application tag in
		// AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();
		System.out.println(aController.toString());

		/****************** Create Dummy Products Data ***********/

		/*ModelProducts productObject = null;
		for (int i = 1; i <= 3; i++) {
			int price = 10 + i;
			// Create product model class object
			productObject = new ModelProducts("Product " + i, "Description "
					+ i, price,"image"+i);

			// store product object to arraylist in controller
			aController.setProducts(productObject);

		}*/

		/****************** Products Data Creation End ***********/

		/******* Create view elements dynamically and show on activity ******/

		// Product arraylist size
		int ProductsSize = aController.getProductsArraylistSize();

		// create the layout params that will be used to define how your
		// button will be displayed
		TableRow.LayoutParams params = new TableRow.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		/******** Dynamically create view elements - Start **********/
		ProductList p = new ProductList();
		ArrayList<String[]> a = p.getproductinfo();

		for (int j = 0; j < 3; j++) {
			// Get probuct data from product data arraylist
			String[] s = a.get(j);
			pName = s[0];
			pPrice = s[2];
			pdesc=s[1];
			pimage=s[3];
			pbrand=s[4];
			TextView product = new TextView(this);
			product.setText(" " + pName + "    ");
			product.setGravity(Gravity.LEFT);

			// Add textView to LinearLayout
			tr1.addView(product);

			TextView price = new TextView(this);
			price.setText("" + pPrice + " ");
			price.setGravity(Gravity.CENTER_HORIZONTAL);

			// Add textView to LinearLayout
			tr2.addView(price);
			ImageView iv = new ImageView(getApplicationContext());
			if (j == 0) {
			
				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				iv.setImageDrawable(drawable );
				
				
				iv.setTag(j + 10);
			} else if (j == 1) {
				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				iv.setImageDrawable(drawable );
				iv.setTag(j + 10);
			} else {
				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				iv.setImageDrawable(drawable );
				iv.setTag(j + 10);
			}
			iv.setAdjustViewBounds(false);
			imageid = j;
			iv.setId(imageid);
			iv.setHorizontalScrollBarEnabled(true);
			iv.setLayoutParams(params);
			iv.setClickable(true);
			id = j;

			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					System.out.println("id in on click view is");
					
					System.out.println(id);
					ProductList p1 = new ProductList();
					ArrayList<String[]> a1 = p1.getproductinfo();
					String[] s1 ;
					Bundle b = new Bundle();
					ImageView iv1 = (ImageView) v;
					switch (Integer.parseInt(iv1.getTag().toString())) {
					case 10:
						s1= a1.get(0);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];

						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;

					case 11:
						s1 = a1.get(1);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;
					case 12:
						 s1 = a1.get(2);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;
					}

					Intent i1 = new Intent(getApplicationContext(),
							Product.class);

					i1.putExtras(b);
					startActivity(i1);
				}
			});

			tr.addView(iv);

			/*
			 * final Button btn = new Button(this); btn.setId(j + 1);
			 * btn.setGravity(Gravity.CENTER_HORIZONTAL);
			 * btn.setTextAlignment(TEXT_); btn.setText("Add To Cart");
			 * 
			 * // set the layoutParams on the button
			 * btn.setLayoutParams(params);
			 * 
			 * final int index = j;
			 * 
			 * // Create click listener for dynamically created button
			 * btn.setOnClickListener(new OnClickListener() { public void
			 * onClick(View v) {
			 * 
			 * // Clicked button index Log.i("TAG", "index :" + index);
			 * 
			 * // Get product instance for index ModelProducts tempProductObject
			 * = aController .getProducts(index);
			 * 
			 * // Check Product already exist in Cart or Not if
			 * (!aController.getCart().checkProductInCart( tempProductObject)) {
			 * btn.setText("Added");
			 * 
			 * // Product not Exist in cart so add product to // Cart product
			 * arraylist aController.getCart().setProducts(tempProductObject);
			 * 
			 * Toast.makeText( getApplicationContext(), "Now Cart size: " +
			 * aController.getCart().getCartSize(), Toast.LENGTH_LONG).show(); }
			 * else { // Cart product arraylist contains Product Toast.makeText(
			 * getApplicationContext(), "Product " + (index + 1) +
			 * " already added in cart.", Toast.LENGTH_LONG).show(); } } });
			 */

			// Add button to LinearLayout
			/*
			 * ll.addView(btn);
			 */

			// Add LinearLayout to XML layout
			lm.removeAllViews();
			lm.addView(tr, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			lm.addView(tr1, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			lm.addView(tr2, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));

		}
		for (int j = 3; j < 8; j++) {
			// Get probuct data from product data arraylist
			String[] s = a.get(j);
			pName = s[0];
			pPrice = s[2];
			pdesc=s[1];
			pimage=s[3];
			TextView product = new TextView(this);
			product.setText(" " + pName + "    ");
			product.setGravity(Gravity.LEFT);

			// Add textView to LinearLayout
			tr5.addView(product);

			TextView price = new TextView(this);
			price.setText("" + pPrice + " ");
			price.setGravity(Gravity.CENTER_HORIZONTAL);

			// Add textView to LinearLayout
			tr6.addView(price);
			ImageView ivg = new ImageView(getApplicationContext());
			if (j == 3){
				ivg.setTag(j + 10);
				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else if (j == 4)
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else if (j == 5)
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else if (j == 6)
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			ivg.setAdjustViewBounds(false);
			imageid = j;
			ivg.setId(imageid);
			ivg.setHorizontalScrollBarEnabled(true);
			ivg.setLayoutParams(params);
			ivg.setClickable(true);
			OnClickListener appleListener = new OnClickListener() {

				@Override
				public void onClick(View v) {

					System.out.println("id in on click view is");

					System.out.println(id);
					ProductList p1 = new ProductList();
					ArrayList<String[]> a1 = p1.getproductinfo();
					String[] s1 ;
					Bundle b = new Bundle();
					ImageView iv1 = (ImageView) v;
					switch (Integer.parseInt(iv1.getTag().toString())) {
					case 13:

						s1= a1.get(3);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;

					case 14:
						s1= a1.get(4);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);

						break;
					case 15:
						s1= a1.get(5);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;
					case 16:
						s1= a1.get(6);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;
					case 17:
						s1= a1.get(7);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;

					}
					Intent i1 = new Intent(getApplicationContext(),
							Product.class);

					i1.putExtras(b);
					startActivity(i1);
					System.out.println("id in on click view is");
				}
			};
			ivg.setOnClickListener(appleListener);

			tr4.addView(ivg);
			

			/*
			 * final Button btn = new Button(this); btn.setId(j + 1);
			 * btn.setGravity(Gravity.CENTER_HORIZONTAL);
			 * btn.setTextAlignment(TEXT_); btn.setText("Add To Cart");
			 * 
			 * // set the layoutParams on the button
			 * btn.setLayoutParams(params);
			 * 
			 * final int index = j;
			 * 
			 * // Create click listener for dynamically created button
			 * btn.setOnClickListener(new OnClickListener() { public void
			 * onClick(View v) {
			 * 
			 * // Clicked button index Log.i("TAG", "index :" + index);
			 * 
			 * // Get product instance for index ModelProducts tempProductObject
			 * = aController .getProducts(index);
			 * 
			 * // Check Product already exist in Cart or Not if
			 * (!aController.getCart().checkProductInCart( tempProductObject)) {
			 * btn.setText("Added");
			 * 
			 * // Product not Exist in cart so add product to // Cart product
			 * arraylist aController.getCart().setProducts(tempProductObject);
			 * 
			 * Toast.makeText( getApplicationContext(), "Now Cart size: " +
			 * aController.getCart().getCartSize(), Toast.LENGTH_LONG).show(); }
			 * else { // Cart product arraylist contains Product Toast.makeText(
			 * getApplicationContext(), "Product " + (index + 1) +
			 * " already added in cart.", Toast.LENGTH_LONG).show(); } } });
			 */

			// Add button to LinearLayout
			/*
			 * ll.addView(btn);
			 */

			// Add LinearLayout to XML layout
			lm1.removeAllViews();
			lm1.addView(tr4, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			lm1.addView(tr5, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			lm1.addView(tr6, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));

		}
		for(int j=8;j<12;j++)
		{

			// Get probuct data from product data arraylist
			String[] s = a.get(j);
			pName = s[0];
			pPrice = s[2];
			pdesc=s[1];
			pimage=s[3];
			System.out.println("pimage in third row is"+pimage);
			TextView product = new TextView(this);
			product.setText(" " + pName + "    ");
			product.setGravity(Gravity.LEFT);

			// Add textView to LinearLayout
			tr8.addView(product);

			TextView price = new TextView(this);
			price.setText("" + pPrice + " ");
			price.setGravity(Gravity.CENTER_HORIZONTAL);

			// Add textView to LinearLayout
			tr9.addView(price);
			ImageView ivg = new ImageView(getApplicationContext());
			if (j == 8){
				ivg.setTag(j + 10);
				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else if (j == 9)
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else if (j == 10)
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else if (j == 11)
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			else
			{
				ivg.setTag(j + 10);

				Resources res = getResources();
				String mDrawableName = pimage;
				int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
				Drawable drawable = res.getDrawable(resID );
				ivg.setImageDrawable(drawable );
				
			}
			ivg.setAdjustViewBounds(false);
			imageid = j;
			ivg.setId(imageid);
			ivg.setHorizontalScrollBarEnabled(true);
			ivg.setLayoutParams(params);
			ivg.setClickable(true);
			OnClickListener appleListener = new OnClickListener() {

				@Override
				public void onClick(View v) {

					System.out.println("id in on click view is");

					System.out.println(id);
					ProductList p1 = new ProductList();
					ArrayList<String[]> a1 = p1.getproductinfo();
					String[] s1 ;
					Bundle b = new Bundle();
					ImageView iv1 = (ImageView) v;
					switch (Integer.parseInt(iv1.getTag().toString())) {
					case 18:
						s1= a1.get(8);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);

					case 19:
						s1= a1.get(9);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);

						break;
					case 20:
						s1= a1.get(10);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;
					case 21:
						s1= a1.get(11);
						pName = s1[0];
						pPrice = s1[2];
						pdesc=s1[1];
						pimage=s1[3];
						pbrand=s1[4];
						b.putString("image", pimage);
						b.putString("name",pName );
						b.putString("price", pPrice);
						b.putString("desc", pdesc);
						b.putString("brand", pbrand);
						break;

					}
					Intent i1 = new Intent(getApplicationContext(),
							Product.class);

					i1.putExtras(b);
					startActivity(i1);
					System.out.println("id in on click view is");
				}
			};
			ivg.setOnClickListener(appleListener);

			tr7.addView(ivg);
			

			/*
			 * final Button btn = new Button(this); btn.setId(j + 1);
			 * btn.setGravity(Gravity.CENTER_HORIZONTAL);
			 * btn.setTextAlignment(TEXT_); btn.setText("Add To Cart");
			 * 
			 * // set the layoutParams on the button
			 * btn.setLayoutParams(params);
			 * 
			 * final int index = j;
			 * 
			 * // Create click listener for dynamically created button
			 * btn.setOnClickListener(new OnClickListener() { public void
			 * onClick(View v) {
			 * 
			 * // Clicked button index Log.i("TAG", "index :" + index);
			 * 
			 * // Get product instance for index ModelProducts tempProductObject
			 * = aController .getProducts(index);
			 * 
			 * // Check Product already exist in Cart or Not if
			 * (!aController.getCart().checkProductInCart( tempProductObject)) {
			 * btn.setText("Added");
			 * 
			 * // Product not Exist in cart so add product to // Cart product
			 * arraylist aController.getCart().setProducts(tempProductObject);
			 * 
			 * Toast.makeText( getApplicationContext(), "Now Cart size: " +
			 * aController.getCart().getCartSize(), Toast.LENGTH_LONG).show(); }
			 * else { // Cart product arraylist contains Product Toast.makeText(
			 * getApplicationContext(), "Product " + (index + 1) +
			 * " already added in cart.", Toast.LENGTH_LONG).show(); } } });
			 */

			// Add button to LinearLayout
			/*
			 * ll.addView(btn);
			 */

			// Add LinearLayout to XML layout
			lm2.removeAllViews();
			lm2.addView(tr7, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			lm2.addView(tr8, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			lm2.addView(tr9, new TableLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));

		
		}

	}
	protected void onNewIntent(Intent intent) {
	    setIntent(intent);
	    handleIntent(intent);
	}
	private void handleIntent(Intent intent) {
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	    	System.out.println("inside if of handle intent");
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      System.out.println("inside querying and query is"+query);
	      Cursor c = db.getWordMatches(query, null);
	      	if(c!=null)
	      	{	
	      		Bundle b=new Bundle();
	      		
	      		System.out.println("inside cursor not null");
	    	  if(c.getCount()>0)
	    	  {
	    		  System.out.println("cursor results are"+c.getCount());
	    		  c.moveToFirst();
	    		  int i=1;
	    		  HashMap<String, String[]> hm=new HashMap<String, String[]>();
	    		  
	    		  
	    		  while(!c.isAfterLast())
	    		  {
	    			  String s=c.getString(0);
	    			  
	    	    	 System.out.println("column one  is "+s);
	    	    	  String s1=c.getString(1);
	    	    	  System.out.println("column  two "+s1);
	    	    	  GetsearchedResultsfromBase g=new GetsearchedResultsfromBase();
	    	    	  String[] m=g.getitem(s1);
	    	    	  if(m!=null)
	    	    	  {
	    	    	  System.out.println("m length is"+m.length);
	    	    	  hm.put("one"+i, m);
	    	    	  System.out.println("after putting into hashmap");
	    	    	  }
	    	    	  System.out.println("before launching adapter");
	    	    	  c.moveToNext();
	    	    	  i++;
	    		  }
	    		  Iterator it=hm.entrySet().iterator();
	    		    while(it.hasNext())
	    		    {
	    		    Map.Entry me=(Map.Entry)it.next();
	    		    System.out.println("key values"+me.getKey());
	    		    String[] st=(String[]) me.getValue();
	    		    System.out.println("values are"+st.length);
	    		    }
	    		  System.out.println("size of hashmap before starting new intent is"+hm.size());
	    		  shprefs = getSharedPreferences(myshared, 0);
	    		  SharedPreferences.Editor editor=shprefs.edit();	    		 
	    		    b.putSerializable("hashmap", hm);
	    	  	    Intent intnt=new Intent(getApplicationContext(),displayresults.class);
	    	  	    intnt.putExtras(b);
	    	  	    startActivity(intnt);
	    	  }
	    }
	    	  else
	    	  {
	    		  System.out.println("no results for cursor");
	    	  }
	    }
	    
	}
	
	public Drawable getimage(String st)
	{
		
		Resources res = getResources();
		int resID = res.getIdentifier(st , "drawable", getPackageName());
		Drawable drawable = res.getDrawable(resID );
		return drawable;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar, menu);
		Context context=getApplicationContext();
		SearchManager searchManager =
		           (SearchManager) getSystemService(context.SEARCH_SERVICE);
		    SearchView searchView =
		            (SearchView) menu.findItem(R.id.action_search).getActionView();
		    searchView.setSearchableInfo(
		            searchManager.getSearchableInfo(getComponentName()));

		    return true;
		
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.signin:
			System.out.println("inside launching sign in screen");
			Intent i = new Intent(this, Signin.class);
			startActivity(i);
			break;
		case R.id.accnt:
			System.out.println("inside launching account in screen");
			Intent i1 = new Intent(this, account.class);
			startActivity(i1);
			break;
		case R.id.shopbydept:
			System.out.println("inside launching shop by department activity");
			Intent i2 = new Intent(this, ShopByDepartment.class);
			startActivity(i2);
			break;

		}
	}
}
