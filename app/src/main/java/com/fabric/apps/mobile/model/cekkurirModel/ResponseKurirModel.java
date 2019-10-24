package com.fabric.apps.mobile.model.cekkurirModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseKurirModel {

	@SerializedName("costs")
	private List<CostsItemKurirModel> costs;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	public void setCosts(List<CostsItemKurirModel> costs){
		this.costs = costs;
	}

	public List<CostsItemKurirModel> getCosts(){
		return costs;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKurirModel{" +
			"costs = '" + costs + '\'' + 
			",code = '" + code + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}