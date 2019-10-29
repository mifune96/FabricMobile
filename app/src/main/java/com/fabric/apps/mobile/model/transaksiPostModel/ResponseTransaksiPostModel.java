package com.fabric.apps.mobile.model.transaksiPostModel;


import com.google.gson.annotations.SerializedName;


public class ResponseTransaksiPostModel {

	@SerializedName("message")
	private String message;

	@SerializedName("transaction")
	private TransactionPostModel transaction;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTransaction(TransactionPostModel transaction){
		this.transaction = transaction;
	}

	public TransactionPostModel getTransaction(){
		return transaction;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTransaksiPostModel{" +
			"message = '" + message + '\'' + 
			",transaction = '" + transaction + '\'' + 
			"}";
		}
}