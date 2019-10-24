package com.fabric.apps.mobile.model.provinsirajaongkirModel;


import com.google.gson.annotations.SerializedName;


public class ResultsItemProvinceModel {

	@SerializedName("province")
	private String province;

	@SerializedName("province_id")
	private String provinceId;

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}

	public String getProvinceId(){
		return provinceId;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItemProvinceModel{" +
			"province = '" + province + '\'' + 
			",province_id = '" + provinceId + '\'' + 
			"}";
		}
}