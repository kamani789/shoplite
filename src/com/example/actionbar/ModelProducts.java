package com.example.actionbar;

import android.graphics.drawable.Drawable;

public class ModelProducts {
	
	private String productName;
	 public Drawable productImage; 
	 public Drawable getProductImage() {
		return productImage;
	}

	public void setProductImage(Drawable productImage) {
		this.productImage = productImage;
	}

	String productDesc;
	private int productPrice;
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private int quantity;
	
    public ModelProducts(String productName,String productDesc,int productPrice, Drawable productImage)
    {
    	this.productName  = productName;
    	this.productDesc  = productDesc;
    	this.productPrice = productPrice;
    	this.productImage=productImage;
    }
	
	public String getProductName() {
		
		return productName;
	}
   
    public String getProductDesc() {
		
		return productDesc;
	}
	
    public int getProductPrice() {
		
		return productPrice;
	}
		
}
