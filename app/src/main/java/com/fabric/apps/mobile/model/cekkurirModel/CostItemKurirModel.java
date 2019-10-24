package com.fabric.apps.mobile.model.cekkurirModel;

import com.google.gson.annotations.SerializedName;


public class CostItemKurirModel {

	@SerializedName("note")
	private String note;

	@SerializedName("etd")
	private String etd;

	@SerializedName("value")
	private int value;

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setEtd(String etd){
		this.etd = etd;
	}

	public String getEtd(){
		return etd;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"CostItemKurirModel{" +
			"note = '" + note + '\'' + 
			",etd = '" + etd + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}