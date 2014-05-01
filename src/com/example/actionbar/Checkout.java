package com.example.actionbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.OrderDatatransactions;
import com.example.serviceclasses.GMailSender;

public class Checkout extends Activity{
	TextView tv1;
	public static String myshared = "myshared";
	SharedPreferences shprefs;
	EditText et1,et2,et3;
	Button btnSubmit;
	Spinner spinner1,spinner2;
	  HashMap<String, String[]> hm1;
	  String errmsg="Please enter";
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.checkout);
	intializeparameters();
	 Bundle bask=getIntent().getExtras();
	hm1 = ((HashMap<String, String[]>) bask.get("product"));
	 System.out.println("size of model products in check out activity is"+hm1.size());
	
	addItemsOnSpinner2();
	addListenerOnButton();
	addListenerOnSpinnerItemSelection();
}
private void intializeparameters() {
	tv1=(TextView)findViewById(R.id.street);
	et1=(EditText)findViewById(R.id.street1);
	et2=(EditText)findViewById(R.id.street2);
	et3=(EditText)findViewById(R.id.city);
	
}
int updatedata(HashMap<String, String[]> hm1)
{
	int i=0;
	System.out.println("inside updatng database results");
	OrderDatatransactions o = new OrderDatatransactions();
	for(String key:hm1.keySet())
	{
		
		System.out.println("key"+key);
		String[] m=(String[]) hm1.get(key);
		o.isupdated(getApplicationContext(), m[2], m[4]);
		System.out.println("after updating from order databse");
		
	}
	System.out.println("after completion of all results");
	
	return i;
}
public void addItemsOnSpinner2() {
	 
	spinner2 = (Spinner) findViewById(R.id.spinner2);
	List<String> list = new ArrayList<String>();
	
	list.add("Texas");
	list.add("Tennessee");
	list.add("Washington");
	list.add("Oregon");
	list.add("Nevada");
	list.add("California");
	list.add("Arizona");
	list.add("UTAH");
	list.add("Idaho");
	list.add("Montana");
	list.add("Wyoming");
	list.add("Colorado");
	list.add("New Mexico");
	list.add("North Dakota");
	list.add("South Dakota");
	list.add("Nebraska");
	list.add("Kansas");
	list.add("Oklahoma");
	list.add("Minnesota");
	list.add("Iowa");
	list.add("Missouri");
	list.add("Arkansas");
	list.add("Mississippi");
	list.add("Louisiana");
	list.add("Alabama");
	list.add("Wisconsin");
	list.add("Illinois");
	list.add("Indiana");
	list.add("Michigan");
	list.add("New York");
	list.add("Maine");
	list.add("Pennsylvania");
	list.add("Ohio");
	list.add("Indiana");
	list.add("Kentucky");
	list.add("West Virginia");
	list.add("Virginia");
	list.add("North carolina");
	list.add("South carolina");
	list.add("Georgia");
	list.add("Florida");
	list.add("District of Columibia");
	list.add("Maryland");
	list.add("Delaware");
	list.add("New Jersy");
	list.add("Connecticut");
	list.add("Rhode Island");
	list.add("massachusetts");
	list.add("Hampshire");
	list.add("Vermont New");
		
	
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, list);
	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinner2.setAdapter(dataAdapter);
  }
 
  public void addListenerOnSpinnerItemSelection() {
	spinner1 = (Spinner) findViewById(R.id.spinner1);
	spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
  }
 
  // get the selected dropdown list value
  public void addListenerOnButton() {
 
	spinner1 = (Spinner) findViewById(R.id.spinner1);
	spinner2 = (Spinner) findViewById(R.id.spinner2);
	btnSubmit = (Button) findViewById(R.id.shipAddress);
 
	btnSubmit.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
		  System.out.println("onclick of button");
		  shprefs = getSharedPreferences(myshared, 0);
			String name = shprefs.getString("name", "Sign in");
			String email=shprefs.getString("email", "chandrababuna@gmail.com");
			Date d=new Date();
			String d1=d.toString();
		  
		  if(et1.getText().toString()==null  || !(et1.getText().toString().length()>0))
		  {
			  
			  System.out.println("inside Address 1");
			  errmsg = errmsg + "Address 1 ";
		  }
		  if(et2.getText().toString()==null  || !(et2.getText().toString().length()>0))
		  {
			  
			  System.out.println("inside street 2");
			  errmsg = errmsg + "Address 2 ";
		  }
		  if(et3.getText().toString()==null  || !(et3.getText().toString().length()>0))
		  {
			  
			  System.out.println("inside city");
			  errmsg = errmsg + " city";
		  }
		  System.out.println("errmsg"+errmsg);
		  System.out.println(errmsg!=null);
		  System.out.println(!errmsg.equalsIgnoreCase("Please enter"));
		  if(errmsg!=null && !errmsg.equalsIgnoreCase("Please enter"))
		  {
			  System.out.println("inside if");
			  
			  AlertDialog.Builder bilder = new AlertDialog.Builder(Checkout.this);
				bilder.setTitle("alert");
				bilder.setCancelable(true);
				bilder.setMessage(errmsg);
				AlertDialog alert = bilder.create();
				 alert.show();
				 errmsg="Please enter";
		  }
		  else
		  {
		  updatedata(hm1);
		  shprefs = getSharedPreferences(myshared, 0);
			
			/*StrictMode.ThreadPolicy policy = new
					StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
					StrictMode.setThreadPolicy(policy);*/
			GMailSender sender = new GMailSender("kamanichandrababu@gmail.com","9553339236");
			try {
				String Address ="\nAddress 1  : "+et1.getText().toString() +
						"\nAddress 2  : "+et2.getText().toString() +
						"\nCity  : "+ et3.getText().toString()+
		                "\nState  : "+ String.valueOf(spinner1.getSelectedItem()) + 
		                "\n Country : "+ String.valueOf(spinner2.getSelectedItem());
				sender.sendMail(
						"ShopLite Order Delivery",
						"Your order on  "+ d1 + " has been delivered to the address "+ Address +".  \n Please login to your Shop Lite account to view the order",
						"kamanichandrababu@gmail.com",email);
				
			} catch (Exception e1) {
				System.out.println(e1);
			}
		  
	    Toast.makeText(Checkout.this,
		"your order has shipped to : " + 
				"\nAddress 1  : "+et1.getText().toString() +
				"\nAddress 2  : "+et2.getText().toString() +
				"\nCity  : "+ et3.getText().toString()+
                "\nState  : "+ String.valueOf(spinner1.getSelectedItem()) + 
                "\n Country : "+ String.valueOf(spinner2.getSelectedItem()),
			Toast.LENGTH_SHORT).show();
	    Intent intnt=new Intent(getApplicationContext(),mainactivity.class);
		  startActivity(intnt);
		  }
	  }
 
	});
  }
}
