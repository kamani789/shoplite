package com.example.db;

public class Orderdata {
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUseremail() {
	return useremail;
}
public void setUseremail(String useremail) {
	this.useremail = useremail;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public String getOrderdate() {
	return orderdate;
}
public void setOrderdate(String orderdate) {
	this.orderdate = orderdate;
}
private String quantity;
public String getQuantity() {
	return quantity;
}
public void setQuantity(String quantity) {
	this.quantity = quantity;
}
private String username;
private String useremail;
private String productname;
private String orderdate;
private String status;
private int cartid;

public int getCartid() {
	return cartid;
}
public void setCartid(int cartid) {
	this.cartid = cartid;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
// constructor
public Orderdata(int id,int cartid, String username, String useremail, String productname,
		String orderdate, String quantity,String status) {
	this.id = id;
	this.username = username;
	this.useremail = useremail;
	this.productname = productname;
	this.orderdate = orderdate;
	this.quantity = quantity;
	this.status=status;
	this.cartid=cartid;
}
public Orderdata() {
	// TODO Auto-generated constructor stub
}

}
