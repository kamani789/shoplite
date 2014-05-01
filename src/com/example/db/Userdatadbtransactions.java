package com.example.db;

import java.util.List;

import android.content.Context;
import android.util.Log;

public class Userdatadbtransactions {

	public boolean isdatainserted(Context context, UserData u) {

		com.example.db.DBAdapter.init(context);
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		long st = com.example.db.DBAdapter.addUserData(u);

		// com.android.example.db.DBAdapter.addUserData(u);
		// com.android.example.db.DBAdapter.addUserData(u));
		// com.android.example.db.DBAdapter.addUserData(new
		// com.android.example.db.UserData("Sam", "11111111111"));

		// Reading all contacts
		Log.d("Reading: ", "Reading all contacts..");
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
		}
		if (st != -1)
			return true;
		else
			return false;
	}
	
	public boolean isvaliddetails(Context context, String id, String pwd) {
		System.out.println("inside is validate method");
		boolean isvalid = false;
		com.example.db.DBAdapter.init(context);
		UserData ud = null;
		List<UserData> data = com.example.db.DBAdapter.getAllUserData();
		System.out.println("data"+data.size()+data.get(0));
		for (com.example.db.UserData dt : data) {
			System.out.println("inside assigning method");
			if (id.equals(dt.name))
				ud = dt;
		}
		if (ud == null)
			return false;
		if (id.equalsIgnoreCase(ud.getName()) && pwd.equals(ud.getPassword()))
			return true;
		else
			return false;

	}
	public boolean delete(Context context)
	{
		System.out.println("inside is validate method");
		com.example.db.DBAdapter.init(context);		
		long st=com.example.db.DBAdapter.isdeleted();
		if(st>0)
			return true;
		else
			return false;
	}
	public boolean ismailexists(Context context,String email)
	{
		boolean isexists=false;
		List<UserData> data = com.example.db.DBAdapter.getAllUserData();
		for (com.example.db.UserData dt : data) {
			System.out.println("inside checking mail exists  method"+email+"   and   "+dt.getEmail());
				if(dt.getEmail().equalsIgnoreCase(email))
				{
					System.out.println("inside match");
					isexists=true;
					break;
				}
		}
		return isexists;
	}
	public String getemail(Context context, String id, String pwd) {
		System.out.println("inside is validate method");
		String email  = "";
		com.example.db.DBAdapter.init(context);
		UserData ud = new UserData();
		List<UserData> data = com.example.db.DBAdapter.getAllUserData();
		System.out.println("data size is"+data.size());
		for (com.example.db.UserData dt : data) {
			System.out.println("inside assigning method");
			if (id.equals(dt.name))
				ud = dt;
		}
		if (ud == null)
			email="default mail";
		if (id.equalsIgnoreCase(ud.getName()) && pwd.equals(ud.getPassword()))
			email= ud.getEmail();
		else
			email="default mail";
		
		return email;
	}

}
