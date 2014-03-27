package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class account extends Activity implements OnClickListener{
	public static String myshared="myshared";
	SharedPreferences shprefs;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		tv=(TextView)findViewById(R.id.logout);
		tv.setOnClickListener(this);
		
		
		
	}
	@Override
	public void onClick(View arg0) {
		shprefs=getSharedPreferences(myshared, 0);
		Editor edit;
		edit=shprefs.edit();
		edit.clear();
		edit.commit();
		Intent i = new Intent(this, mainactivity.class);
		startActivity(i);
		
	}

}
