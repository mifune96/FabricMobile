package com.fabric.apps.mobile.model.kotaModel;


import com.google.gson.annotations.SerializedName;


public class ResponseKotaModel {

	@SerializedName("rajaongkir")
	private RajaongkirKotaModel rajaongkir;

	public void setRajaongkir(RajaongkirKotaModel rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public RajaongkirKotaModel getRajaongkir(){
		return rajaongkir;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKotaModel{" +
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}