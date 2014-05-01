package com.example.actionbar;
import java.util.List;
import java.util.Map; 

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
public class ExpandableListAdapter extends BaseExpandableListAdapter{
	private Activity context;
    private Map<String, List<String>> laptopCollections;
    private List<String> laptops;
 
    public ExpandableListAdapter(Activity context, List<String> laptops,
            Map<String, List<String>> laptopCollections) {
        this.context = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, final ViewGroup parent) {
        final String laptop = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.brandfilterchilditem, null);
        }
 
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        
        
  /*      
      CheckBox delete = (CheckBox) convertView.findViewById(R.id.FilterSelection);
       delete.setOnClickListener(new OnClickListener() {
    	   
 
            public void onClick(View v) {
            	
            	System.out.println("inside click of checkbox"+childPosition+","+groupPosition);
            	
            	
            	
            	
            	
           	 	
                
            }
        });*/
 
        item.setText(laptop);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return laptops.get(groupPosition);
    }
 
    public int getGroupCount() {
        return laptops.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.brandfilteritem,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}