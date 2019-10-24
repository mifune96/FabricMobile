package com.fabric.apps.mobile.model.kotaModel;


import com.google.gson.annotations.SerializedName;

public class QueryKotaModel {

	@SerializedName("province")
	private String province;

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	@Override
 	public String toString(){
		return 
			"QueryKotaModel{" +
			"province = '" + province + '\'' + 
			"}";
		}
}