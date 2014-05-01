package com.example.actionbar;

import android.app.Activity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 

 
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
public class FilterActivity extends Activity{

	 
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelistviewbrand);
 
        createGroupList();
        System.out.println("after creating grouplist"+groupList.size());
        
        createCollection();
        System.out.println("after creating collection"+laptopCollection.size());
 
        expListView = (ExpandableListView) findViewById(R.id.brand_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, laptopCollection);
        expListView.setAdapter(expListAdapter);
 
        //setGroupIndicatorToRight();
 
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();
 
                return true;
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
    	String[] Brands = { "Apple", "Samsung",
                "Google","Nexus","HTC","LG Mobile","Motorola" };
        String[] PriceRange = { "Under $25","$25 to $50", "$50 to $100", "$100 to $200","$200 to $400","$400 to $800","$800 and above" };
        String[] Color = { "Black", "Blue","White", "Red","Green" };
        String[] Seller = { "Amazon", "ebay","google", "Apple" };
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
 
    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }
 
    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
 
        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }
 
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
 
}
