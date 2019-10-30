package com.fabric.apps.mobile.model.transaksiGetModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseGetModel {

	@SerializedName("message")
	private String message;

	@SerializedName("transaction")
	private List<TransactionItemGetModel> transaction;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTransaction(List<TransactionItemGetModel> transaction){
		this.transaction = transaction;
	}

	public List<TransactionItemGetModel> getTransaction(){
		return transaction;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetModel{" +
			"message = '" + message + '\'' + 
			",transaction = '" + transaction + '\'' + 
			"}";
		}
}