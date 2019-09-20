package com.fabric.apps.mobile.model.cartModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseCart {

	@SerializedName("message")
	private String message;

	@SerializedName("cart")
	private List<CartItem> cart;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCart(List<CartItem> cart){
		this.cart = cart;
	}

	public List<CartItem> getCart(){
		return cart;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCart{" +
			"message = '" + message + '\'' + 
			",cart = '" + cart + '\'' + 
			"}";
		}
}