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
	private String productId;

	@SerializedName("id")
	private String id;

	@SerializedName("CustomerId")
	private String customerId;

	@SerializedName("permeter")
	private String permeter;

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

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setPermeter(String permeter){
		this.permeter = permeter;
	}

	public String getPermeter(){
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