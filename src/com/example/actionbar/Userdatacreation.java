package com.example.actionbar;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Userdatacreation extends Activity implements OnClickListener {
	Button b1;
	TextView tv1;
	String msg="Please";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationsuccess);
		Bundle basket=getIntent().getExtras();
		msg=basket.getString("msg");			
		initialize();
		if(msg!=null && !msg.equalsIgnoreCase(" S"))
			tv1.setText(msg);
		b1.setOnClickListener(this);
	}

	private void initialize() {
		b1 = (Button) findViewById(R.id.returnbutton);
		tv1=(TextView)findViewById(R.id.successtext);
	}

	@Override
	public void onClick(View arg0) {

		Intent i = new Intent(this, Signin.class);
		startActivity(i);

	}

}
