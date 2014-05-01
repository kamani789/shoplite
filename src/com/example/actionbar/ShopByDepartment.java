package com.example.actionbar;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.search.DatabaseTable;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
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
	
	DatabaseTable  db;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	System.out.println("inside shop by department");
	
	String[] values = new String[] { "video game", "Mobiles", "SmartPhones",
	        "Desktops and Laptops", "Drugs and Medicines", "Appliances", "Clothing", "Software Products",
	        "kitchen Appliances", "Beauty Products","Automotive Accessories" };
	    // use your custom layout
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	    
	    db = new DatabaseTable(getApplicationContext());
	  
	  }

protected void onListItemClick(ListView l, View v, int position, long id) {
	String query=null;
	
	if(position==1)
	  query="mobile";
	else if(position==0)
		query="video game";
	else if(position==2)
		query="smart phones";
	else if(position==3)
		query="desktop or laptop";
	else if(position==5)
		query="appliances";
	else if(position==9)
		query="beauty";	
	else
		query="video game";
	
	 System.out.println("position at video games is"+position);
	 Cursor c = db.getDepartmentMatches(query, null);
	 if(c!=null)
   	{
			
   		Bundle b=new Bundle();
   		
   		System.out.println("inside cursor not null");
 	  if(c.getCount()>0)
 	  {
 		  System.out.println("cursor results are"+c.getCount());
 		  c.moveToFirst();
 		  int i=1;
 		  HashMap<String, String[]> hm=new HashMap<String, String[]>();
 		  while(!c.isAfterLast())
 		  {
 			  String s=c.getString(0);
 			  
 	    	 System.out.println("column one  is "+s);
 	    	  String s1=c.getString(1);
 	    	  System.out.println("column  two "+s1);
 	    	  GetsearchedResultsfromBase g=new GetsearchedResultsfromBase();
 	    	  String[] m=g.getitem(s1);
 	    	  if(m!=null)
 	    	  {
 	    	  System.out.println("m length is"+m.length);
 	    	  hm.put("one"+i, m);
 	    	  System.out.println("after putting into hashmap");
 	    	  }
 	    	  System.out.println("before launching adapter");
 	    	  c.moveToNext();
 	    	  i++;
 		  }
 		  Iterator it=hm.entrySet().iterator();
 		    while(it.hasNext())
 		    {
 		    Map.Entry me=(Map.Entry)it.next();
 		    System.out.println("key values"+me.getKey());
 		    String[] st=(String[]) me.getValue();
 		    System.out.println("values are"+st.length);
 		    }
 		  System.out.println("size of hashmap before starting new intent is"+hm.size());
 		  b.putSerializable("hashmap", hm);
 	  	    Intent intnt=new Intent(getApplicationContext(),displayresults.class);
 	  	    intnt.putExtras(b);
 	  	    startActivity(intnt);
 	  }
 
   	}
    
  }
	


}
