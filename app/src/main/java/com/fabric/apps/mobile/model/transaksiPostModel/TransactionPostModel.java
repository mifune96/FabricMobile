package com.fabric.apps.mobile.model.transaksiPostModel;


import com.google.gson.annotations.SerializedName;

public class TransactionPostModel {

	@SerializedName("note")
	private String note;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("typeOfOngkir")
	private String typeOfOngkir;

	@SerializedName("dateOfTransaction")
	private String dateOfTransaction;

	@SerializedName("totalHarga")
	private String totalHarga;

	@SerializedName("id")
	private int id;

	@SerializedName("CustomerId")
	private String customerId;

	@SerializedName("AddressId")
	private String addressId;

	@SerializedName("shippingCosts")
	private String shippingCosts;

	@SerializedName("noPesanan")
	private int noPesanan;

	@SerializedName("status")
	private String status;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTypeOfOngkir(String typeOfOngkir){
		this.typeOfOngkir = typeOfOngkir;
	}

	public String getTypeOfOngkir(){
		return typeOfOngkir;
	}

	public void setDateOfTransaction(String dateOfTransaction){
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getDateOfTransaction(){
		return dateOfTransaction;
	}

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setAddressId(String addressId){
		this.addressId = addressId;
	}

	public String getAddressId(){
		return addressId;
	}

	public void setShippingCosts(String shippingCosts){
		this.shippingCosts = shippingCosts;
	}

	public String getShippingCosts(){
		return shippingCosts;
	}

	public void setNoPesanan(int noPesanan){
		this.noPesanan = noPesanan;
	}

	public int getNoPesanan(){
		return noPesanan;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"TransactionPostModel{" +
			"note = '" + note + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",typeOfOngkir = '" + typeOfOngkir + '\'' + 
			",dateOfTransaction = '" + dateOfTransaction + '\'' + 
			",totalHarga = '" + totalHarga + '\'' + 
			",id = '" + id + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",addressId = '" + addressId + '\'' + 
			",shippingCosts = '" + shippingCosts + '\'' + 
			",noPesanan = '" + noPesanan + '\'' + 
			",status = '" + status + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}