package com.example.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.serviceclasses.EmailValidator;
import com.example.serviceclasses.GMailSender;
import com.example.db.UserData;
import com.example.db.Userdatadbtransactions;

public class Register extends Activity implements OnClickListener {

	EditText et1, et2, et3, et4;
	TextView terr;
	Button b1;
	String error = "Please enter ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		intialization();
		b1.setOnClickListener(this);
	}

	private void intialization() {
		et1 = (EditText) findViewById(R.id.regusername);
		et2 = (EditText) findViewById(R.id.email);
		et3 = (EditText) findViewById(R.id.regpwd);
		et4 = (EditText) findViewById(R.id.regrepwd);
		b1 = (Button) findViewById(R.id.save);
		terr = (TextView) findViewById(R.id.regerrmsg);
	}

	@Override
	public void onClick(View arg0) {
		boolean isuploaded = false;
		UserData u = new UserData();

		boolean isall = false;
		isall = isallentered();
		boolean isemailvalid = false;
		EmailValidator e = new EmailValidator();
		isemailvalid = e.validate(et2.getText().toString());
		if (isall && isemailvalid) {
			System.out.println("inside success of entering all details");

			u.setName(et1.getText().toString());
			u.setEmail(et2.getText().toString());
			u.setPassword(et3.getText().toString());
			u.setRole("Admin");
			u.setAccount_status("A");
			Userdatadbtransactions u1 = new Userdatadbtransactions();
			isuploaded = u1.isdatainserted(getApplicationContext(), u);
			GMailSender sender = new GMailSender("kamanichandrababu@gmail.com",
					"9553339236");
			try {
				sender.sendMail(
						"ShopLiteAccountDetails",
						"Your Account Has been created successfully with ShopLite",
						"kamanichandrababu@gmail.com", et2.getText().toString());
			} catch (Exception e1) {
				System.out.println(e1);
			}
			if (isuploaded) {
				System.out.println("inside success");
				Log.d("success", "able to insert record in database");
				Intent i = new Intent(this, Userdatacreation.class);
				startActivity(i);
			} else {
				System.out.println("unable to insert recoerd in database");
			}
		} else {
			System.out.println("Please enter valid email address");
			error = error + " vald E-mail Address";
			terr.setText(error);

		}
	}

	private boolean isallentered() {
		System.out.println("inside is all entered");
		boolean isent = false;

		System.out.println("after error message");
		if (!(et1.getText().toString().length() > 0)) {
			System.out.println("inside name nothing");
			error = error + "Username, ";
			System.out.println(error);
		}
		if (!(et2.getText().toString().length() > 0)) {
			System.out.println("inside email nothing");
			error = error + "email ,";
			System.out.println(error);
		}
		if (et3.getText().toString() == null
				|| !(et3.getText().toString().length() > 0)) {
			System.out.println("inside pwd nothing");
			error = error + "password ,";
			System.out.println(error);
		}
		if (et3.getText().toString() == null
				|| !(et4.getText().toString().length() > 0)) {
			System.out.println("inside rpwd nthng");
			error = error + "password confirmation ,";
			System.out.println(error);
		}
		System.out.println("after checking all the fields");
		if (error.equalsIgnoreCase("Please enter "))
			isent = true;
		else {
			terr.setText(error);
			isent = false;
		}
		return isent;

	}

}
