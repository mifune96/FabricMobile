package com.fabric.apps.mobile.model.signupModel;

import com.google.gson.annotations.SerializedName;


public class ResponseSignup {

	@SerializedName("customerCreate")
	private CustomerSignup customerSignup;

	@SerializedName("message")
	private String message;

	@SerializedName("accessToken")
	private String accessToken;

	public void setCustomerSignup(CustomerSignup customerSignup){
		this.customerSignup = customerSignup;
	}

	public CustomerSignup getCustomerSignup(){
		return customerSignup;
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
			"ResponseSignup{" +
			"customerSignup = '" + customerSignup + '\'' +
			",message = '" + message + '\'' + 
			",accessToken = '" + accessToken + '\'' + 
			"}";
		}
}