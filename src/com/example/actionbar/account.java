package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class account extends Activity implements OnClickListener{
	public static String myshared="myshared";
	SharedPreferences shprefs;
	TextView tv,tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		tv=(TextView)findViewById(R.id.logout);
		tv1=(TextView)findViewById(R.id.yourcart);
		tv.setOnClickListener(this);
		tv1.setOnClickListener(this);
		
		
		
	}
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId())
		{
		case R.id.logout:
			shprefs=getSharedPreferences(myshared, 0);
			Editor edit;
			edit=shprefs.edit();
			edit.clear();
			edit.commit();
			Intent i = new Intent(this, mainactivity.class);
			startActivity(i);
			break;
		case R.id.yourcart:
			System.out.println("inside your cart activity");
			shprefs = getSharedPreferences(myshared, 0);
			String name = shprefs.getString("name", "Sign in");
			String email=shprefs.getString("email", "chandrababuna@gmail.com");
			Bundle b=new Bundle();
			b.putString("name", "babu");
			b.putString("username", name);
			b.putString("email", email);
			Intent i1 = new Intent(this, Cart.class);
			i1.putExtras(b);
			startActivity(i1);
			break;
		}
		
	}

}
