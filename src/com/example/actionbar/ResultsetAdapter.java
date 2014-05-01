package com.example.actionbar;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsetAdapter extends BaseAdapter {
	  
	 private List<ModelProducts> mProductList;
	 private LayoutInflater mInflater;
	 
	  
	 public ResultsetAdapter(List<ModelProducts> list, LayoutInflater inflater) {
	  mProductList = list;
	  mInflater = inflater;
	 
	 }
	 
	 @Override
	 public int getCount() {
	  return mProductList.size();
	 }
	 
	 @Override
	 public Object getItem(int position) {
	  return mProductList.get(position);
	 }
	 
	 @Override
	 public long getItemId(int position) {
	  return position;
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  final ViewItem item;
	   
	  if (convertView == null) {
		  System.out.println("inside convert view null");
	   convertView = mInflater.inflate(R.layout.item, null);
	   item = new ViewItem();
	    
	   item.productImageView = (ImageView) convertView.findViewById(R.id.ImageViewItem);
	    
	   item.productTitle = (TextView) convertView.findViewById(R.id.TextViewItem);
	   item.productDesc=(TextView)convertView.findViewById(R.id.TextView01);
	   item.productPrice=(TextView)convertView.findViewById(R.id.TextView02);
	   
	  
	    
	   convertView.setTag(item);
	  } else {
	   item = (ViewItem) convertView.getTag();
	  }
	   
	  ModelProducts curProduct = mProductList.get(position);
	   
	  item.productImageView.setImageDrawable(curProduct.productImage);
	  item.productTitle.setText(curProduct.getProductName());
	  item.productDesc.setText(curProduct.getProductDesc());
	  item.productPrice.setText(curProduct.getProductPrice());
	   
	   
	  return convertView;
	 }
	  
	  
	 private class ViewItem {
	  ImageView productImageView;
	  TextView productTitle;
	  TextView productDesc;
	  TextView productPrice;
	  
	 }
	 
	}