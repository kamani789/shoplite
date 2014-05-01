package com.example.actionbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;

public class displayresults extends Activity implements OnClickListener{
	
	private List<ModelProducts> mProductList;
	List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
	Button bsort;
	Button bfilter;
	SharedPreferences shpref;
	ListView listViewCatalog;
	protected Controller control;
	String selected="";
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	control = (Controller)this.getApplicationContext();
	setContentView(R.layout.displayresults);
	bsort=(Button)findViewById(R.id.sort);
	bfilter=(Button)findViewById(R.id.filter);
	 listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
	bsort.setOnClickListener(this);
	bfilter.setOnClickListener(this);
	 android.content.res.Resources res= getResources();
	 Bundle bask=getIntent().getExtras();
	   final HashMap<String, String[]> hm=(HashMap<String, String[]>) bask.get("hashmap");
	  String sortcriteria=bask.getString("sort");
	  System.out.println("sort criteria is"+sortcriteria);
	 System.out.println("hash map size in display results is"+hm.size());
	mProductList =new ArrayList<ModelProducts>();
		Set s=hm.keySet();
		
		System.out.println("key set size is"+s.size());
		for(String key:hm.keySet())
		{
			
			System.out.println("key"+key);
			String[] m=(String[]) hm.get(key);
			System.out.println("m length is"+m.length);
			System.out.println("m values are"+m[0]+","+m[1]+","+m[2]+","+m[3]);
			ModelProducts m4=new ModelProducts(m[0], m[1],m[2],getimage(m[3]),m[4]);
			mProductList.add(m4);
			
		}
	
	  listViewCatalog.setAdapter(new ResultsetAdapter(mProductList, getLayoutInflater()));
	  listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

		  
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) 
		{
			ModelProducts mp=(ModelProducts) arg0.getAdapter().getItem(position);
			System.out.println("selected  item is"+mp.getProductDesc());
			Intent productDetailsIntent = new Intent(getBaseContext(),Product.class);
			System.out.println("position of item in onclick of list item is"+position);
			Bundle b=new Bundle();
			int i=0;
			String[] m=null;
			for(String key:hm.keySet())
			{
				
				System.out.println("key"+key);
				m=(String[]) hm.get(key);
				System.out.println("m length in product launching  is"+m.length);
				System.out.println("m values in product launching are"+m[0]+","+m[1]+","+m[2]+","+m[3]);
				if(mp.getProductName().equalsIgnoreCase(m[0]))
				{
					break;
				}
				
				
				
				
			}
			b.putString("image", m[3]);
			b.putString("desc", m[1]);
			b.putString("name", mp.getProductName());
			b.putString("price", mp.getProductPrice());
			
			
			productDetailsIntent.putExtras(b);
		    startActivity(productDetailsIntent);
		}		
		  });
	  
	  

}
protected void onResume() {
    super.onResume();
    control.setCurrentActivity(this);
}
protected void onDestroy() {        
    clearReferences();
    super.onDestroy();
}

private void clearReferences(){
    Activity currActivity = control.getCurrentActivity();
    if (currActivity != null && currActivity.equals(this))
    	control.setCurrentActivity(null);
}
public Drawable getimage(String st)
{
	
	Resources res = getResources();
	int resID = res.getIdentifier(st , "drawable", getPackageName());
	Drawable drawable = res.getDrawable(resID );
	return drawable;
}
@Override
public void onClick(View arg0) {
	switch(arg0.getId())
	{
	case R.id.sort:
		System.out.println("inside onclick of sort");
		AlertDialog.Builder bilder = new AlertDialog.Builder(this);
		bilder.setTitle("Sort");
		bilder.setCancelable(true);
		 final String[] items={"Relevance","Price:Low to High","Price:High to Low"};
		 bilder.setItems(items, new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int item) {
			    String sort=items[item];
			    if(sort.equalsIgnoreCase("Price:Low to High"))
			    {
			    Collections.sort(mProductList, ModelProducts.Comparators.pricelow);
			    }
			    if(sort.equalsIgnoreCase("Price:High to Low"))
			    {
			    	Collections.sort(mProductList, ModelProducts.Comparators.pricehigh);
			    }
			    
			    listViewCatalog.setAdapter(new ResultsetAdapter(mProductList, getLayoutInflater()));
			  
			  }
			});
		 AlertDialog alert = bilder.create();
		 alert.show();
		break;
	case R.id.filter:
		System.out.println("inside filter activity switch case in display results");
	
		showdialog();
		
		break;
	}
	
}
private void showdialog() {
	 final Dialog dialog = new Dialog(displayresults.this);
    // Include dialog.xml file
    dialog.setContentView(R.layout.expandablelistviewbrand);
    dialog.setTitle("Filter");
    dialog.setOnShowListener(new OnShowListener() {
		
		public void onShow(DialogInterface dialog) {
			
			
		}
	});
    dialog.setCancelable(true);
    
    /*LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = li.inflate(R.layout.list, null, false);*/
    createGroupList();
    System.out.println("after creating grouplist"+groupList.size());
    
    createCollection();
    System.out.println("after creating collection"+laptopCollection.size());

    expListView = (ExpandableListView)dialog. findViewById(R.id.brand_list);
    final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
            this, groupList, laptopCollection);
    expListView.setChoiceMode(ExpandableListView.CHOICE_MODE_MULTIPLE);
    expListView.setAdapter(expListAdapter);
    expListView.setOnChildClickListener(new OnChildClickListener() {
    	
        public boolean onChildClick(ExpandableListView parent, View v,
                int groupPosition, int childPosition, long id) {
        	System.out.println("inside onchild click of expandable list view");
        	SparseBooleanArray checked = expListView.getCheckedItemPositions();
        	
        	if(checked!=null)
        		System.out.println("checked length is"+checked.size());
        	//parent.getChildAt(childPosition).setBackgroundColor(Color.CYAN);
     /*   	CheckBox cb = (CheckBox)v.findViewById( R.id.FilterSelection );
        	if(cb!=null)
        		cb.toggle();*/
        	selected =(String) expListAdapter.getChild(
                    groupPosition, childPosition);
            System.out.println("group position-->>"+groupPosition+"childPosition-->>"+childPosition);
            System.out.println("Selected string is"+selected);
            GettingFilteredProducts g=new GettingFilteredProducts();
            mProductList=g.getproductlist(selected,mProductList, groupPosition,childPosition);
            listViewCatalog.setAdapter(new ResultsetAdapter(mProductList, getLayoutInflater()));
            dialog.dismiss();
            return true;
        }
    });
  
    
    dialog.show();
    
    
    System.out.println("after returning from dialog show");
    
   
    
    expListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int chilposition,
				long id) {
			
			System.out.println("chilposition-->>"+chilposition);
			
		}
	});
    
	
}
private void createGroupList() {
    groupList = new ArrayList<String>();
    groupList.add("Brand");
    groupList.add("PriceRange");
    groupList.add("Color");
    groupList.add("Seller");
    groupList.add("Condition");
}

private void createCollection() {
    // preparing laptops collection(child)
	System.out.println("inside create collection product list size is"+mProductList.size());
	String[] Brands = getbrands(mProductList);
    String[] PriceRange = { "Under $25","$25 to $50", "$50 to $100", "$100 to $200","$200 to $400","$400 to $800","$800 and above" };
    String[] Color = { "Black", "Blue","White", "Red","Green" };
    String[] Seller=new String[Brands.length+3];
    for(int i=0;i<Brands.length;i++)
    {
    	Seller[i]=Brands[i];
    }
    Seller[Brands.length]="Amazon";Seller[Brands.length+1]="Ebay";Seller[Brands.length+2]="Google";
    String[] Condition = { "New", "Used", "Refurbished" };

    laptopCollection = new LinkedHashMap<String, List<String>>();

    for (String laptop : groupList) {
        if (laptop.equals("Brand")) {
            loadChild(Brands);
        } else if (laptop.equals("PriceRange"))
            loadChild(PriceRange);
        else if (laptop.equals("Color"))
            loadChild(Color);
        else if (laptop.equals("Seller"))
            loadChild(Seller);
        else  
            loadChild(Condition);

        laptopCollection.put(laptop, childList);
    }
}
public String [] getbrands(List<ModelProducts> mProductList)
{
	int size=mProductList.size();
	System.out.println("size of product list is"+size);
	System.out.println("mProductList.get(i).getProductname().toString()"+mProductList.get(0).getProductDesc().toString());
	System.out.println("mProductList.get(i).getProductbrand().toString()"+mProductList.get(0).getProductbrand().toString());

	Set<String> lump = new HashSet<String>();
	for(int i=0;i<mProductList.size();i++)
	{
		System.out.println("inside for loop of iteration"+i);
		System.out.println("mProductList.get(i).getProductbrand()"+mProductList.get(i).getProductbrand().toString());
		String brnd=mProductList.get(i).getProductbrand().toString();
		if(lump.size()==0)
		{
			lump.add(mProductList.get(i).getProductbrand().toString());
		}
		else
		{
			for(int j=0;j<lump.size();j++)
			{
				if(lump.contains(brnd))
				{
					System.out.println("duplicate found");
				}
				else
				{
					lump.add(mProductList.get(i).getProductbrand().toString());
					break;
				}
			}
		}
		
	}
	
	String a[]=new String[lump.size()];
	System.out.println("size of string after from hash set is"+a.length);
	int pos=0;
	Iterator i=lump.iterator();
	while(i.hasNext())
	{
		String res=(String)i.next();
		a[pos]=res;
		pos++;
	}
	
	System.out.println("size of a in returning is"+a.length);
	return a;
}
private void loadChild(String[] laptopModels) {
    childList = new ArrayList<String>();
    for (String model : laptopModels)
        childList.add(model);
}



}