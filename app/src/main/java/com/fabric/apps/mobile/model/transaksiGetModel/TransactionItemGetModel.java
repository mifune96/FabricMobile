package com.fabric.apps.mobile.model.transaksiGetModel;

import com.google.gson.annotations.SerializedName;

public class TransactionItemGetModel {

	@SerializedName("note")
	private String note;

	@SerializedName("typeOfOngkir")
	private String typeOfOngkir;

	@SerializedName("totalHarga")
	private int totalHarga;

	@SerializedName("CustomerId")
	private int customerId;

	@SerializedName("shippingCosts")
	private int shippingCosts;

	@SerializedName("noPesanan")
	private String noPesanan;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("noResi")
	private String noResi;

	@SerializedName("buktiPembayaran")
	private String buktiPembayaran;

	@SerializedName("dateOfTransaction")
	private String dateOfTransaction;

	@SerializedName("statusBuktiPembayaran")
	private String statusBuktiPembayaran;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("id")
	private int id;

	@SerializedName("AddressId")
	private int addressId;

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

	public void setTypeOfOngkir(String typeOfOngkir){
		this.typeOfOngkir = typeOfOngkir;
	}

	public String getTypeOfOngkir(){
		return typeOfOngkir;
	}

	public void setTotalHarga(int totalHarga){
		this.totalHarga = totalHarga;
	}

	public int getTotalHarga(){
		return totalHarga;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setShippingCosts(int shippingCosts){
		this.shippingCosts = shippingCosts;
	}

	public int getShippingCosts(){
		return shippingCosts;
	}

	public void setNoPesanan(String noPesanan){
		this.noPesanan = noPesanan;
	}

	public String getNoPesanan(){
		return noPesanan;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNoResi(String noResi){
		this.noResi = noResi;
	}

	public String getNoResi(){
		return noResi;
	}

	public void setBuktiPembayaran(String buktiPembayaran){
		this.buktiPembayaran = buktiPembayaran;
	}

	public String getBuktiPembayaran(){
		return buktiPembayaran;
	}

	public void setDateOfTransaction(String dateOfTransaction){
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getDateOfTransaction(){
		return dateOfTransaction;
	}

	public void setStatusBuktiPembayaran(String statusBuktiPembayaran){
		this.statusBuktiPembayaran = statusBuktiPembayaran;
	}

	public String getStatusBuktiPembayaran(){
		return statusBuktiPembayaran;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAddressId(int addressId){
		this.addressId = addressId;
	}

	public int getAddressId(){
		return addressId;
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
			"TransactionItemGetModel{" +
			"note = '" + note + '\'' + 
			",typeOfOngkir = '" + typeOfOngkir + '\'' + 
			",totalHarga = '" + totalHarga + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",shippingCosts = '" + shippingCosts + '\'' + 
			",noPesanan = '" + noPesanan + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",noResi = '" + noResi + '\'' + 
			",buktiPembayaran = '" + buktiPembayaran + '\'' + 
			",dateOfTransaction = '" + dateOfTransaction + '\'' + 
			",statusBuktiPembayaran = '" + statusBuktiPembayaran + '\'' + 
			",waktu = '" + waktu + '\'' + 
			",id = '" + id + '\'' + 
			",addressId = '" + addressId + '\'' + 
			",status = '" + status + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}