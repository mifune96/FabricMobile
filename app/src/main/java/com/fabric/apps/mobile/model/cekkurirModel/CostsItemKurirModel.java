package com.fabric.apps.mobile.model.cekkurirModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CostsItemKurirModel {

	@SerializedName("service")
	@Expose
	private String service;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("cost")
	@Expose
	private List<CostItemKurirModel> cost = null;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CostItemKurirModel> getCost() {
		return cost;
	}

	public void setCost(List<CostItemKurirModel> cost) {
		this.cost = cost;
	}

}
