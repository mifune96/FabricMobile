package com.fabric.apps.mobile.model.profileModel;


import com.google.gson.annotations.SerializedName;


public class ResponseProfilModel {

	@SerializedName("message")
	private String message;

	@SerializedName("customer")
	private CustomerProfilModel customer;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCustomer(CustomerProfilModel customer){
		this.customer = customer;
	}

	public CustomerProfilModel getCustomer(){
		return customer;
	}

	@Override
 	public String toString(){
		return 
			"ResponseProfilModel{" +
			"message = '" + message + '\'' + 
			",customer = '" + customer + '\'' + 
			"}";
		}
}