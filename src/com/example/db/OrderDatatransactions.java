package com.example.db;
import java.util.List;

import com.example.actionbar.ModelProducts;

import android.content.Context;
import android.util.Log;
public class OrderDatatransactions {
	public boolean isdatainserted(Context context, Orderdata u) {

		com.example.db.OrderDetailsDBAdapter.init(context);
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		long st = com.example.db.OrderDetailsDBAdapter.addOrderData(u);

		// com.android.example.db.DBAdapter.addUserData(u);
		// com.android.example.db.DBAdapter.addUserData(u));
		// com.android.example.db.DBAdapter.addUserData(new
		// com.android.example.db.UserData("Sam", "11111111111"));

		// Reading all contacts
		/*Log.d("Reading: ", "Reading all contacts..");
		List<com.example.db.UserData> data = com.example.db.DBAdapter
				.getAllUserData();

		// Reading all contacts
		Log.d("Reading: ", "Reading all contacts..");
		List<com.example.db.UserData> dat = com.example.db.DBAdapter
				.getAllUserData();

		for (com.example.db.UserData dt : dat) {
			String log = "Id: " + dt.getID() + " ,Name: " + dt.getName()
					+ " ,E-mail: " + dt.getEmail();
			// Writing Contacts to log
			Log.d("Name: ", log);
		}*/
		if (st != -1)
			return true;
		else
			return false;
	}
	public List<Orderdata> getorderdata(Context context,String id,String status)
	{
		com.example.db.OrderDetailsDBAdapter.init(context);
		List<Orderdata> od=null;
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		od = com.example.db.OrderDetailsDBAdapter.getOrderData(id,status);
		return od;

	}
	public boolean isorderexists(Context context,String name,String email,String status)
	{
		System.out.println("inside cheking is existing the prodname is"+name+"email is"+email);
		List<Orderdata> ot=getorderdata(context,email,status);
		System.out.println("the size of list inside checking existing is"+ot.size());
		
		boolean isexists=false;
		if (ot.size() > 0) {
			System.out
					.println("data got from database is"
							+ ot.get(0).getProductname() + " and length is"
							+ ot.size());
			for (com.example.db.Orderdata dt : ot) {

				String prodname = dt.getProductname();
				System.out.println("inside for loop" + prodname);
				if(name.equalsIgnoreCase(prodname))
				{
					isexists=true;
					break;
				}
				
			}
		}
			return isexists;
	}
	public boolean isdeleted(Context context,String  productname,String usermail,String status)
	{
		com.example.db.OrderDetailsDBAdapter.init(context);
		Orderdata od=null;
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		System.out.println("inside transaction deleting"+productname+" ,"+usermail);
		long st=com.example.db.OrderDetailsDBAdapter.isdeleted(productname,usermail);
		if(st!=0)
			return true;
		else
			return true;

	}
	public boolean isupdated(Context context,String  productname,String usermail)
	{
		com.example.db.OrderDetailsDBAdapter.init(context);
		Orderdata od=null;
		// Inserting Contacts
		Log.d("Insert: ", "updating ..");
		System.out.println("inside transaction updating"+productname+" ,"+usermail);
		long st=com.example.db.OrderDetailsDBAdapter.isupdated(productname,usermail);
		if(st!=0)
			return true;
		else
			return true;

	}
}
