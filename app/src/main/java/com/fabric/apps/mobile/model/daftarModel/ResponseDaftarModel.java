package com.fabric.apps.mobile.model.daftarModel;

import com.google.gson.annotations.SerializedName;

public class ResponseDaftarModel {

	@SerializedName("customerCreate")
	private CustomerCreateDaftarModel customerCreate;

	@SerializedName("address")
	private AddressDaftarModel address;

	@SerializedName("message")
	private String message;

	@SerializedName("accessToken")
	private String accessToken;

	public void setCustomerCreate(CustomerCreateDaftarModel customerCreate){
		this.customerCreate = customerCreate;
	}

	public CustomerCreateDaftarModel getCustomerCreate(){
		return customerCreate;
	}

	public void setAddress(AddressDaftarModel address){
		this.address = address;
	}

	public AddressDaftarModel getAddress(){
		return address;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDaftarModel{" +
			"customerCreate = '" + customerCreate + '\'' + 
			",address = '" + address + '\'' + 
			",message = '" + message + '\'' + 
			",accessToken = '" + accessToken + '\'' + 
			"}";
		}
}