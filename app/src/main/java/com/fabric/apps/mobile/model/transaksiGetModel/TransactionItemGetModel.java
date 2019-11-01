package com.fabric.apps.mobile.model.transaksiGetModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionItemGetModel {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("noPesanan")
	@Expose
	private String noPesanan;
	@SerializedName("totalHarga")
	@Expose
	private Integer totalHarga;
	@SerializedName("CustomerId")
	@Expose
	private Integer customerId;
	@SerializedName("AddressId")
	@Expose
	private Integer addressId;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("dateOfTransaction")
	@Expose
	private String dateOfTransaction;
	@SerializedName("waktu")
	@Expose
	private Object waktu;
	@SerializedName("typeOfOngkir")
	@Expose
	private String typeOfOngkir;
	@SerializedName("note")
	@Expose
	private String note;
	@SerializedName("buktiPembayaran")
	@Expose
	private Object buktiPembayaran;
	@SerializedName("statusBuktiPembayaran")
	@Expose
	private Object statusBuktiPembayaran;
	@SerializedName("shippingCosts")
	@Expose
	private Integer shippingCosts;
	@SerializedName("noResi")
	@Expose
	private Object noResi;
	@SerializedName("createdAt")
	@Expose
	private String createdAt;
	@SerializedName("updatedAt")
	@Expose
	private String updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoPesanan() {
		return noPesanan;
	}

	public void setNoPesanan(String noPesanan) {
		this.noPesanan = noPesanan;
	}

	public Integer getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(Integer totalHarga) {
		this.totalHarga = totalHarga;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Object getWaktu() {
		return waktu;
	}

	public void setWaktu(Object waktu) {
		this.waktu = waktu;
	}

	public String getTypeOfOngkir() {
		return typeOfOngkir;
	}

	public void setTypeOfOngkir(String typeOfOngkir) {
		this.typeOfOngkir = typeOfOngkir;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Object getBuktiPembayaran() {
		return buktiPembayaran;
	}

	public void setBuktiPembayaran(Object buktiPembayaran) {
		this.buktiPembayaran = buktiPembayaran;
	}

	public Object getStatusBuktiPembayaran() {
		return statusBuktiPembayaran;
	}

	public void setStatusBuktiPembayaran(Object statusBuktiPembayaran) {
		this.statusBuktiPembayaran = statusBuktiPembayaran;
	}

	public Integer getShippingCosts() {
		return shippingCosts;
	}

	public void setShippingCosts(Integer shippingCosts) {
		this.shippingCosts = shippingCosts;
	}

	public Object getNoResi() {
		return noResi;
	}

	public void setNoResi(Object noResi) {
		this.noResi = noResi;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}