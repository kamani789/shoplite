package com.example.actionbar;

import java.util.ArrayList;

public class ModelCart {

	private ArrayList<ModelProducts> cartProducts = new ArrayList<ModelProducts>();
	
	private int quantity;

	public ArrayList<ModelProducts> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(ArrayList<ModelProducts> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ModelProducts getProducts(int pPosition) {

		return cartProducts.get(pPosition);
	}

	public void setProducts(ModelProducts Products) {

		cartProducts.add(Products);

	}

	public int getCartSize() {

		return cartProducts.size();

	}

	public boolean checkProductInCart(ModelProducts aProduct) {

		return cartProducts.contains(aProduct);

	}

}
