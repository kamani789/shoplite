package com.example.actionbar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class displayresults extends Activity{
	
	private List<ModelProducts> mProductList;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.displayresults);
	 android.content.res.Resources res= getResources();
	mProductList =new ArrayList<ModelProducts>();
	
	ModelProducts m4=new ModelProducts(" Playstation"," 4",45,res.getDrawable(R.drawable.playstation4));
	ModelProducts m5=new ModelProducts("Xbox 360 "," Console ",45,res.getDrawable(R.drawable.xboxconsole));
	ModelProducts m6=new ModelProducts(" Titan fall"," - Xbox 360",45,res.getDrawable(R.drawable.titanfall));
	ModelProducts m7=new ModelProducts("Final Fantasy","- Xbox 360",45,res.getDrawable(R.drawable.finalfantasy));
	
	
	
	mProductList.add(m4);
	mProductList.add(m5);
	mProductList.add(m6);
	mProductList.add(m7);
	
	
	ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
	  listViewCatalog.setAdapter(new ResultsetAdapter(mProductList, getLayoutInflater(), false));
	  listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

		  
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) 
		{
			
			Intent productDetailsIntent = new Intent(getBaseContext(),Product.class);
			System.out.println("position of item in onclick of list item is"+position);
			Bundle b=new Bundle();
			b.putString("image", "Titanfall");
			b.putString("name", " -X box 360 ");
			b.putString("price", "$ 24.23");
			productDetailsIntent.putExtras(b);
		    startActivity(productDetailsIntent);
		}		
		  });
}
}
