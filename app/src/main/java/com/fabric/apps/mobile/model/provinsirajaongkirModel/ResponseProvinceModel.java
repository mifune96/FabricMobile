package com.fabric.apps.mobile.model.provinsirajaongkirModel;


import com.google.gson.annotations.SerializedName;


public class ResponseProvinceModel {

	@SerializedName("rajaongkir")
	private RajaongkirProvinceModel rajaongkir;

	public void setRajaongkir(RajaongkirProvinceModel rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public RajaongkirProvinceModel getRajaongkir(){
		return rajaongkir;
	}

	@Override
 	public String toString(){
		return 
			"ResponseProvinceModel{" +
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}