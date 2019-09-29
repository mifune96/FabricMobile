package com.fabric.apps.mobile.model.cartModel;

import com.google.gson.annotations.SerializedName;

public class CartItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("Customer")
	private CustomerCart customerCart;

	@SerializedName("Product")
	private ProductCart productCart;

	@SerializedName("ProductId")
	private int productId;

	@SerializedName("id")
	private int id;

	@SerializedName("CustomerId")
	private int customerId;

	@SerializedName("permeter")
	private double permeter;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setCustomerCart(CustomerCart customerCart){
		this.customerCart = customerCart;
	}

	public CustomerCart getCustomerCart(){
		return customerCart;
	}

	public void setProductCart(ProductCart productCart){
		this.productCart = productCart;
	}

	public ProductCart getProductCart(){
		return productCart;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setPermeter(double permeter){
		this.permeter = permeter;
	}

	public double getPermeter(){
		return permeter;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
	public String toString(){
		return
				"CartItem{" +
						"createdAt = '" + createdAt + '\'' +
						",customerCart = '" + customerCart + '\'' +
						",productCart = '" + productCart + '\'' +
						",productId = '" + productId + '\'' +
						",id = '" + id + '\'' +
						",customerId = '" + customerId + '\'' +
						",permeter = '" + permeter + '\'' +
						",updatedAt = '" + updatedAt + '\'' +
						"}";
	}
}