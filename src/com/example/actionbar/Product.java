package com.example.actionbar;

import java.util.Date;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.OrderDatatransactions;
import com.example.db.Orderdata;
import com.example.serviceclasses.GMailSender;

public class Product extends Activity implements OnClickListener{
	public static String myshared = "myshared";
	SharedPreferences shprefs;
	Editor edit;
	String st;
	String desc;
	String iv1;
	String price;
	String brand;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.product);

	final TableLayout lm = (TableLayout) findViewById(R.id.linearMain);		
	TableRow tr=(TableRow)findViewById(R.id.trset);
	TableRow tr1=(TableRow)findViewById(R.id.trset1);
	TableRow tr2=(TableRow)findViewById(R.id.trset2);
	TableRow tr3=(TableRow)findViewById(R.id.trset3);
	TableRow tr4=(TableRow)findViewById(R.id.trset4);
	TableRow tr5=(TableRow)findViewById(R.id.trset5);
	TableRow tr6=(TableRow)findViewById(R.id.trset6);
	TableRow tr8=(TableRow)findViewById(R.id.trset8);
	TableRow tr9=(TableRow)findViewById(R.id.trset9);
	
	TextView tv1=new TextView(getApplicationContext());
	tv1.setGravity(Gravity.CENTER_HORIZONTAL);
	TextView tv=new TextView(getApplicationContext());
	tv.setGravity(Gravity.CENTER_HORIZONTAL);
	lm.setOrientation(TableLayout.VERTICAL);	
	TableRow.LayoutParams params = new TableRow.LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	TextView tv9=new TextView(getApplicationContext());
	EditText et9=new EditText(getApplicationContext());
	
	ImageView iv = new ImageView(getApplicationContext());
	Bundle basket=getIntent().getExtras();
	iv1=basket.getString("image");
	st=basket.getString("name");
	desc=basket.getString("desc");
	price=basket.getString("price");
	brand=basket.getString("brand");
	int i=basket.getInt("id");
	System.out.println("image id");
	System.out.println(i);
	iv1=iv1+"_large";
	iv.setImageDrawable(getimage(iv1));
	System.out.println(st);
	System.out.println(price);
	tv1.setText(price);	
	tv.setText(st);	
	tv9.setText("Quantity");
	et9.setText("1");

	iv.setAdjustViewBounds(false);	
	iv.setHorizontalScrollBarEnabled(true);
	iv.setLayoutParams(params);
	tv.setTextSize(20);
	tv.setTextColor(Color.BLACK);
	
	tv1.setTextSize(20);
	tv1.setTextColor(Color.RED);
	Button b1=(Button)findViewById(R.id.buybutton);
	b1.setOnClickListener(this);
	Button b2=(Button)findViewById(R.id.addtocart);
	b2.setOnClickListener(this);
	Button b3=(Button)findViewById(R.id.subscribe);
	b3.setOnClickListener(this);
	TextView textview=(TextView)findViewById(R.id.itemdescription);
	TextView textview1=(TextView)findViewById(R.id.aboutitem);
	textview.setTextSize(10);
	String desct=st+desc;
	textview1.setText(desct);
	textview1.setTextSize(15);
	textview1.setTextColor(Color.MAGENTA);
	tr3.removeAllViews();
	
	tr4.removeAllViews();
	tr5.removeAllViews();
	tr6.removeAllViews();
	tr8.removeAllViews();
	tr9.removeAllViews();
	
	tr1.addView(iv);
	tr.addView(tv);
	tr2.addView(tv1);
	tr3.addView(b1);
	tr4.addView(b2);
	tr5.addView(b3);
	tr6.addView(textview);
	tr8.addView(textview1);
	tr9.addView(tv9);
	tr9.addView(et9);
	
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
	lm.addView(tr3, new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
	lm.addView(tr4, new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
	lm.addView(tr5, new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
	lm.addView(tr6, new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
	lm.addView(tr8, new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
	lm.addView(tr9, new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
			
			
	
	
}
public Drawable getimage(String st)
{
	
	Resources res = getResources();
	int resID = res.getIdentifier(st , "drawable", getPackageName());
	Drawable drawable = res.getDrawable(resID );
	return drawable;
}
@Override
public void onClick(View arg0) {
	shprefs = getSharedPreferences(myshared, 0);
	String name = shprefs.getString("name", "Sign in");
	String email=shprefs.getString("email", "chandrababuna@gmail.com");
	System.out.println("name value is"+name);
	String prdctname=st;
	OrderDatatransactions o=new OrderDatatransactions();
	switch(arg0.getId())
	{
	
	case R.id.buybutton:
	
		if(name!=null && name.equalsIgnoreCase("Sign in"))
		{
			System.out.println("inside if");
			AlertDialog.Builder bilder = new AlertDialog.Builder(this);
			bilder.setTitle("alert");
			bilder.setCancelable(true);
			bilder.setMessage("Please Sign in in order to buy a product");
			AlertDialog alert = bilder.create();
			 alert.show();
		}
		else
		{
			System.out.println("user already signed in");
			boolean ezist=o.isorderexists(getApplicationContext(), prdctname,email, "Added");
			if(ezist)
			{
				Toast.makeText(getBaseContext(), "Product already existing in the cart", Toast.LENGTH_LONG).show();
			}
			else
			{
			Bundle b=new Bundle();
			b.putString("name", prdctname);
			b.putString("username", name);
			b.putString("email", email);
			System.out.println("product name in launching cart is"+prdctname);
 	  	    Intent intnt=new Intent(getApplicationContext(),Cart.class);
 	  	    intnt.putExtras(b);
 	  	    startActivity(intnt);
			}
			
		}
		break;
	case R.id.addtocart:
		if(name!=null && name.equalsIgnoreCase("Sign in"))
		{
			AlertDialog.Builder bilder = new AlertDialog.Builder(this);
			bilder.setTitle("alert");
			bilder.setCancelable(true);
			bilder.setMessage("Please Sign in in order to add a product to cart");
			AlertDialog alert = bilder.create();
			 alert.show();
		}
		else
		{
			boolean ezist=o.isorderexists(getApplicationContext(), prdctname,email, "Added");
			if(ezist)
			{
				Toast.makeText(getBaseContext(), "Product already existing in the cart", Toast.LENGTH_LONG).show();
			}
			else
			{
			Date d=new Date();
			String date=d.toString();
			Orderdata od = new Orderdata(1, 1,name ,
					email,prdctname , date, "1", "Added");
			
			boolean status=o.isdatainserted(getApplicationContext(), od);
			if(status)
				Toast.makeText(getBaseContext(), "Product added to cart", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getBaseContext(), "error in adding product to the cart", Toast.LENGTH_LONG).show();
			System.out.println("user already signed in");
			}
		}
		break;
	case R.id.subscribe:
		if(name!=null && name.equalsIgnoreCase("Sign in"))
		{
			AlertDialog.Builder bilder = new AlertDialog.Builder(this);
			bilder.setTitle("alert");
			bilder.setCancelable(true);
			bilder.setMessage("Please Sign in in order to subscribe for  a product");
			AlertDialog alert = bilder.create();
			 alert.show();
		}
		else
		{
			shprefs = getSharedPreferences(myshared, 0);
			
			StrictMode.ThreadPolicy policy = new
					StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
					StrictMode.setThreadPolicy(policy);
			GMailSender sender = new GMailSender("kamanichandrababu@gmail.com","9553339236");
			try {
				sender.sendMail(
						"ShopLite Product subscription",
						"You have subscribed for the product  "+desc+ " and you will recieve messages regarding the new  products in that category ",
						"kamanichandrababu@gmail.com",email);
				
			} catch (Exception e1) {
				System.out.println(e1);
			}
			Toast.makeText(getBaseContext(), "You have subscribed for this Product", Toast.LENGTH_LONG).show();
			System.out.println("user already signed in");
		}
		break;
	}
	
}

}
