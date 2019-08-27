package com.fabric.apps.mobile.model.siginModel;


import com.google.gson.annotations.SerializedName;


public class ResponseLogin {

	@SerializedName("message")
	private String message;

	@SerializedName("accessToken")
	private String accessToken;

	@SerializedName("customer")
	private CustomerSigin customerSigin;

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

	public void setCustomerSigin(CustomerSigin customerSigin){
		this.customerSigin = customerSigin;
	}

	public CustomerSigin getCustomerSigin(){
		return customerSigin;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" +
			"message = '" + message + '\'' + 
			",accessToken = '" + accessToken + '\'' + 
			",customerSigin = '" + customerSigin + '\'' +
			"}";
		}
}