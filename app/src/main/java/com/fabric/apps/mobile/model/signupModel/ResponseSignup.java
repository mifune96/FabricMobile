package com.fabric.apps.mobile.model.signupModel;


import com.google.gson.annotations.SerializedName;


public class ResponseSignup {

	@SerializedName("message")
	private String message;

	@SerializedName("customer")
	private CustomerSignup customerSignup;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCustomerSignup(CustomerSignup customerSignup){
		this.customerSignup = customerSignup;
	}

	public CustomerSignup getCustomerSignup(){
		return customerSignup;
	}

	@Override
 	public String toString(){
		return 
			"ResponseSignup{" +
			"message = '" + message + '\'' + 
			",customerSignup = '" + customerSignup + '\'' +
			"}";
		}
}