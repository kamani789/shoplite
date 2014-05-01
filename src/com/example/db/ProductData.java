package com.example.db;

public class ProductData {
	ProductData()
	{
		
	}
	ProductData(int id,String Productname,String productdesc,String productimage,String category,String price ,String seller,String Condition)
	{
		this.Productname=Productname;
		this.productdesc=productdesc;
		this.productimage=productimage;
		this.category=category;
		this.price=price;
		this.seller=seller;
		this.condition=Condition;
	}
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getProductname() {
	return Productname;
}
public void setProductname(String productname) {
	Productname = productname;
}
public String getProductdesc() {
	return productdesc;
}
public void setProductdesc(String productdesc) {
	this.productdesc = productdesc;
}
public String getProductimage() {
	return productimage;
}
public void setProductimage(String productimage) {
	this.productimage = productimage;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getSeller() {
	return seller;
}
public void setSeller(String seller) {
	this.seller = seller;
}
private String Productname;
private String productdesc;
private String productimage;
private String category;
private String price;
private String seller;
private String condition;
public String getCondition() {
	return condition;
}
public void setCondition(String condition) {
	this.condition = condition;
}
}
