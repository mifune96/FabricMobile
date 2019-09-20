package com.fabric.apps.mobile.model.cartModel;

import com.google.gson.annotations.SerializedName;


public class ProductCart {

	@SerializedName("ukuran")
	private int ukuran;

	@SerializedName("warna")
	private String warna;

	@SerializedName("image")
	private String image;

	@SerializedName("code")
	private String code;

	@SerializedName("like")
	private int like;

	@SerializedName("stok")
	private int stok;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("material")
	private String material;

	@SerializedName("harga")
	private int harga;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setUkuran(int ukuran){
		this.ukuran = ukuran;
	}

	public int getUkuran(){
		return ukuran;
	}

	public void setWarna(String warna){
		this.warna = warna;
	}

	public String getWarna(){
		return warna;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setLike(int like){
		this.like = like;
	}

	public int getLike(){
		return like;
	}

	public void setStok(int stok){
		this.stok = stok;
	}

	public int getStok(){
		return stok;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setMaterial(String material){
		this.material = material;
	}

	public String getMaterial(){
		return material;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
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
			"ProductCart{" +
			"ukuran = '" + ukuran + '\'' + 
			",warna = '" + warna + '\'' + 
			",image = '" + image + '\'' + 
			",code = '" + code + '\'' + 
			",like = '" + like + '\'' + 
			",stok = '" + stok + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",material = '" + material + '\'' + 
			",harga = '" + harga + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}