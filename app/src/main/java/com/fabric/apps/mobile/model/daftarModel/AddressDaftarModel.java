package com.fabric.apps.mobile.model.daftarModel;

import com.google.gson.annotations.SerializedName;

public class AddressDaftarModel {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("CitiesId")
	private String citiesId;

	@SerializedName("ProvinceId")
	private String provinceId;

	@SerializedName("id")
	private int id;

	@SerializedName("CustomerId")
	private int customerId;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setCitiesId(String citiesId){
		this.citiesId = citiesId;
	}

	public String getCitiesId(){
		return citiesId;
	}

	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}

	public String getProvinceId(){
		return provinceId;
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

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"AddressDaftarModel{" +
			"createdAt = '" + createdAt + '\'' + 
			",citiesId = '" + citiesId + '\'' + 
			",provinceId = '" + provinceId + '\'' + 
			",id = '" + id + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}