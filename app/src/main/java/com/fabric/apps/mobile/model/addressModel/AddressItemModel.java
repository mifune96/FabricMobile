package com.fabric.apps.mobile.model.addressModel;

import com.google.gson.annotations.SerializedName;


public class AddressItemModel {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("CitiesId")
	private int citiesId;

	@SerializedName("ProvinceId")
	private int provinceId;

	@SerializedName("DistrictId")
	private Object districtId;

	@SerializedName("CustomerAddressModel")
	private CustomerAddressModel customer;

	@SerializedName("id")
	private int id;

	@SerializedName("CustomerId")
	private int customerId;

	@SerializedName("mainAddress")
	private Object mainAddress;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setCitiesId(int citiesId){
		this.citiesId = citiesId;
	}

	public int getCitiesId(){
		return citiesId;
	}

	public void setProvinceId(int provinceId){
		this.provinceId = provinceId;
	}

	public int getProvinceId(){
		return provinceId;
	}

	public void setDistrictId(Object districtId){
		this.districtId = districtId;
	}

	public Object getDistrictId(){
		return districtId;
	}

	public void setCustomer(CustomerAddressModel customer){
		this.customer = customer;
	}

	public CustomerAddressModel getCustomer(){
		return customer;
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

	public void setMainAddress(Object mainAddress){
		this.mainAddress = mainAddress;
	}

	public Object getMainAddress(){
		return mainAddress;
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
			"AddressItemModel{" +
			"createdAt = '" + createdAt + '\'' + 
			",citiesId = '" + citiesId + '\'' + 
			",provinceId = '" + provinceId + '\'' + 
			",districtId = '" + districtId + '\'' + 
			",customer = '" + customer + '\'' + 
			",id = '" + id + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",mainAddress = '" + mainAddress + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}