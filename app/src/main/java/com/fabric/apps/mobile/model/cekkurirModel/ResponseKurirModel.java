package com.fabric.apps.mobile.model.cekkurirModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseKurirModel {

	@SerializedName("code")
	@Expose
	private String code;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("costs")
	@Expose
	private List<CostsItemKurirModel> costs = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CostsItemKurirModel> getCosts() {
		return costs;
	}

	public void setCosts(List<CostsItemKurirModel> costs) {
		this.costs = costs;
	}
}