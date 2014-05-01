package com.example.actionbar;

import com.example.db.Userdatadbtransactions;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signin extends Activity implements OnClickListener {

	public static String myshared="myshared";
	EditText etuname, etpwd;
	TextView fpwd, errmsg;
	Button sign, create;
	SharedPreferences shprefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		intialization();
		shprefs=getSharedPreferences(myshared, 0);
		
		sign.setOnClickListener(this);
		fpwd.setOnClickListener(this);
		create.setOnClickListener(this);

	}

	private void intialization() {
		etuname = (EditText) findViewById(R.id.loginusername);
		etpwd = (EditText) findViewById(R.id.password);
		fpwd = (TextView) findViewById(R.id.loginforgot);
		errmsg = (TextView) findViewById(R.id.loginerrmsg);
		sign = (Button) findViewById(R.id.save);
		create = (Button) findViewById(R.id.register);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.loginforgot:
			Intent i = new Intent(this, Forgotpassword.class);
			startActivity(i);
			break;
		case R.id.register:
			Intent i1 = new Intent(this, Register.class);
			startActivity(i1);
			break;
		case R.id.save:
			if (etuname.getText().toString().length() > 0
					&& etpwd.getText().toString().length() > 0) {
				String uname = etuname.getText().toString();
				String pwd = etpwd.getText().toString();
				System.out.println("inside button click");
				Userdatadbtransactions u1 = new Userdatadbtransactions();
				boolean isvalid = false;
				isvalid = u1.isvaliddetails(getApplication(), uname, pwd);
				String email=u1.getemail(getApplication(), uname, pwd);
				if (isvalid) {
					SharedPreferences.Editor edit=shprefs.edit();
					
					String welcome="Hello,"+uname;
					edit.putString("name", welcome);
					edit.putString("email", email);
					edit.commit();
					Intent i2 = new Intent(this, mainactivity.class);
					startActivity(i2);
				} else {
					errmsg.setText("Please enter valid username and password");
				}
			} else {
				if (!(etuname.getText().toString().length() > 0)
						&& !(etpwd.getText().toString().length() > 0))
					errmsg.setText("Please enter username and password");
				else if (!(etuname.getText().toString().length() > 0))
					errmsg.setText("Please enter  username ");
				else
					errmsg.setText("Please enter password");
			}

			break;
		}

	}

}
