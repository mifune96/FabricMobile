package com.fabric.apps.mobile.model.addressModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseAddressModel {

	@SerializedName("address")
	private List<AddressItemModel> address;

	@SerializedName("message")
	private String message;

	public void setAddress(List<AddressItemModel> address){
		this.address = address;
	}

	public List<AddressItemModel> getAddress(){
		return address;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ResponseAddressModel{" +
			"address = '" + address + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}