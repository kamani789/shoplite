package com.example.actionbar;

import java.util.Comparator;

import android.graphics.drawable.Drawable;

public class ModelProducts implements Comparable<ModelProducts>{
	
	private String productName;
	 public Drawable productImage; 
	 private String productDesc;
		private  String productPrice;
		private String productbrand;
	 public String getProductbrand() {
			return productbrand;
		}

		public void setProductbrand(String productbrand) {
			this.productbrand = productbrand;
		}

	public Drawable getProductImage() {
		return productImage;
	}

	public void setProductImage(Drawable productImage) {
		this.productImage = productImage;
	}

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private int quantity;
	
    public ModelProducts(String productName,String productDesc,String productPrice, Drawable productImage,String productbrand)
    {
    	this.productName  = productName;
    	this.productDesc  = productDesc;
    	this.productPrice = productPrice;
    	this.productImage=productImage;
    	this.productbrand=productbrand;
    }
	
	public String getProductName() {
		
		return productName;
	}
   
    public String getProductDesc() {
		
		return productDesc;
	}
	
    public String getProductPrice() {
		
		return productPrice;
	}

	@Override
	public int compareTo(ModelProducts o) {
		// TODO Auto-generated method stub
		 return Comparators.pricelow.compare(this, o);
	}
		 public static class Comparators {

		        
		        public static Comparator<ModelProducts> pricelow = new Comparator<ModelProducts>() {
		            public int compare(ModelProducts o1, ModelProducts o2) {
		            	String first=o1.getProductPrice().substring(1);
		            	double d1=Double.parseDouble(first);
		            	System.out.println("first product value is"+d1);
		            	String second=o2.getProductPrice().substring(1);
		            	double d2=Double.parseDouble(second);
		            	System.out.println("first product value is"+d2);
		                int i;
		                if(d1>=d2)
		                	i=1;
		                else
		                	i=-1;
		               /* if (i == 0) {
		                    i = Integer.parseInt(o1.productPrice) - Integer.parseInt(o2.productPrice);
		                }*/
		                return i;
		            }
		        };
		        public static Comparator<ModelProducts> pricehigh = new Comparator<ModelProducts>() {
		            public int compare(ModelProducts o1, ModelProducts o2) {
		            	String first=o1.getProductPrice().substring(1);
		            	double d1=Double.parseDouble(first);
		            	System.out.println("first product value is"+d1);
		            	String second=o2.getProductPrice().substring(1);
		            	double d2=Double.parseDouble(second);
		            	System.out.println("first product value is"+d2);
		                int i;
		                if(d1>=d2)
		                	i=-1;
		                else
		                	i=1;
		               /* if (i == 0) {
		                    i = Integer.parseInt(o1.productPrice) - Integer.parseInt(o2.productPrice);
		                }*/
		                return i;
		            }
		        };
		    }
	
		
}
