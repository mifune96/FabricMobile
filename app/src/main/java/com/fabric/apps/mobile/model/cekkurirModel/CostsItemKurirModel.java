package com.fabric.apps.mobile.model.cekkurirModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class CostsItemKurirModel {

	@SerializedName("cost")
	private List<CostItemKurirModel> cost;

	@SerializedName("service")
	private String service;

	@SerializedName("description")
	private String description;

	public void setCost(List<CostItemKurirModel> cost){
		this.cost = cost;
	}

	public List<CostItemKurirModel> getCost(){
		return cost;
	}

	public void setService(String service){
		this.service = service;
	}

	public String getService(){
		return service;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"CostsItemKurirModel{" +
			"cost = '" + cost + '\'' + 
			",service = '" + service + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}