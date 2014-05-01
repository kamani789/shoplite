package com.example.actionbar;

import com.example.serviceclasses.GMailSender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Forgotpassword extends Activity implements OnClickListener{

	Button forgotpwd;
	TextView tv1;
	EditText et1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgotpwd);
		et1=(EditText)findViewById(R.id.forgotemail);
		forgotpwd=(Button)findViewById(R.id.registersend);
		forgotpwd.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		/*StrictMode.ThreadPolicy policy = new
				StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
				StrictMode.setThreadPolicy(policy);*/
		GMailSender sender = new GMailSender("kamanichandrababu@gmail.com",
				"9553339236");
		try {
			sender.sendMail(
					"ShopLiteAccountForgotPassword",
					"Your Password has been sent to "+et1.getText().toString(),
					"kamanichandrababu@gmail.com", et1.getText().toString());
			
		} catch (Exception e1) {
			System.out.println(e1);
		}
		Intent i = new Intent(this, Userdatacreation.class);
		i.putExtra("msg", "Your Password has been sent to "+et1.getText().toString());
		startActivity(i);
		
	}
	
}
