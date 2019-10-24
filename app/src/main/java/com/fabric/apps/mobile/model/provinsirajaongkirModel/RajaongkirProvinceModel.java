package com.fabric.apps.mobile.model.provinsirajaongkirModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RajaongkirProvinceModel {

	@SerializedName("query")
	private List<Object> query;

	@SerializedName("results")
	private List<ResultsItemProvinceModel> results;

	@SerializedName("status")
	private StatusProvinceModel status;

	public void setQuery(List<Object> query){
		this.query = query;
	}

	public List<Object> getQuery(){
		return query;
	}

	public void setResults(List<ResultsItemProvinceModel> results){
		this.results = results;
	}

	public List<ResultsItemProvinceModel> getResults(){
		return results;
	}

	public void setStatus(StatusProvinceModel status){
		this.status = status;
	}

	public StatusProvinceModel getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RajaongkirProvinceModel{" +
			"query = '" + query + '\'' + 
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}