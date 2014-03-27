package com.example.actionbar;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Product extends Activity{
	
	
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
	
	TextView tv1=new TextView(getApplicationContext());
	tv1.setGravity(Gravity.CENTER_HORIZONTAL);
	TextView tv=new TextView(getApplicationContext());
	tv.setGravity(Gravity.CENTER_HORIZONTAL);
	lm.setOrientation(TableLayout.VERTICAL);	
	TableRow.LayoutParams params = new TableRow.LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	ImageView iv = new ImageView(getApplicationContext());
	Bundle basket=getIntent().getExtras();
	String iv1=basket.getString("image");
	String st=basket.getString("name");
	String price=basket.getString("price");
	int i=basket.getInt("id");
	System.out.println("image id");
	System.out.println(i);
	if(iv1.contains("plus"))
	{
		
		iv.setImageResource(R.drawable.xplus);
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
	}
	else if(iv1.contains("indle"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setImageResource(R.drawable.xkindle);
	}
	else if(iv1.contains("laptop"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.hplaptop);
	}
	else if(iv1.contains("drin"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.proteindrink);
	}
	else if(iv1.contains("tool"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.toolkit);
	}
	else if(iv1.contains("camera"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.camera);
	}
	else if(iv1.contains("phone"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.iphone4s);
	}
	else if(iv1.contains("lay"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.playstationlarge);
	}
	else if(iv1.contains("onsole"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.xboxconsole);
	}
	else if(iv1.contains("tan"))
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.titanfall);
	}
	else 
	{
		System.out.println(st);
		System.out.println(price);
		tv1.setText(price);	
		tv.setText(st);	
		iv.setLayoutParams(new LayoutParams(500, 500));
		iv.setImageResource(R.drawable.nikefieldglove);
	}

	iv.setAdjustViewBounds(false);	
	iv.setHorizontalScrollBarEnabled(true);
	iv.setLayoutParams(params);
	tv.setTextSize(15);
	tv1.setTextSize(15);
	Button b1=(Button)findViewById(R.id.buybutton);

	Button b2=(Button)findViewById(R.id.addtocart);
	
	Button b3=(Button)findViewById(R.id.subscribe);
	
	TextView textview=(TextView)findViewById(R.id.itemdescription);
	textview.setTextSize(10);
	tr3.removeAllViews();
	
	tr4.removeAllViews();
	tr5.removeAllViews();
	tr6.removeAllViews();
	
	tr1.addView(iv);
	tr.addView(tv);
	tr2.addView(tv1);
	tr3.addView(b1);
	tr4.addView(b2);
	tr5.addView(b3);
	tr6.addView(textview);
	
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
	
			
	
	
}


}
