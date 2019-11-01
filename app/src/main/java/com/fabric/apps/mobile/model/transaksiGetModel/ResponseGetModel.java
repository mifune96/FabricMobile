package com.fabric.apps.mobile.model.transaksiGetModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetModel {
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("transaction")
	@Expose
	private List<TransactionItemGetModel> transaction = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TransactionItemGetModel> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<TransactionItemGetModel> transaction) {
		this.transaction = transaction;
	}
}