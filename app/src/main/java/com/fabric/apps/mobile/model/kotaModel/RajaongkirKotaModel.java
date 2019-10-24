package com.fabric.apps.mobile.model.kotaModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class RajaongkirKotaModel {

	@SerializedName("query")
	private QueryKotaModel query;

	@SerializedName("results")
	private List<ResultsItemKotaModel> results;

	@SerializedName("status")
	private StatusResultsItemKotaModel status;

	public void setQuery(QueryKotaModel query){
		this.query = query;
	}

	public QueryKotaModel getQuery(){
		return query;
	}

	public void setResults(List<ResultsItemKotaModel> results){
		this.results = results;
	}

	public List<ResultsItemKotaModel> getResults(){
		return results;
	}

	public void setStatus(StatusResultsItemKotaModel status){
		this.status = status;
	}

	public StatusResultsItemKotaModel getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RajaongkirKotaModel{" +
			"query = '" + query + '\'' + 
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}