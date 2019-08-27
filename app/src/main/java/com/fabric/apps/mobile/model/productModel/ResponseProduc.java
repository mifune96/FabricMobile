package com.fabric.apps.mobile.model.productModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseProduc {

	@SerializedName("message")
	private String message;

	@SerializedName("products")
	private List<ProductsItem> products;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	@Override
 	public String toString(){
		return 
			"ResponseProduc{" +
			"message = '" + message + '\'' + 
			",products = '" + products + '\'' + 
			"}";
		}
}