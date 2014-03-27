package com.example.actionbar;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class ShopByDepartment extends ListActivity {
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	System.out.println("inside shop by department");
	
	String[] values = new String[] { "Videogames", "MobileAccessories", "SmartPhones",
	        "Desktops and Laptops", "Drugs and Medicines", "Appliances", "Clothing", "Software Products",
	        "kitchen Appliances", "Beauty Products","Automotive Accessories" };
	    // use your custom layout
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  
	  }

protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent i=new Intent(getApplicationContext(),displayresults.class);
    startActivity(i);
  }
	


}
