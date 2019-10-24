package com.fabric.apps.mobile.model.kotaModel;

import com.google.gson.annotations.SerializedName;

public class StatusResultsItemKotaModel {

	@SerializedName("code")
	private int code;

	@SerializedName("description")
	private String description;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
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
			"StatusResultsItemKotaModel{" +
			"code = '" + code + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}