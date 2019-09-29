package com.fabric.apps.mobile.model.siginModel;


import com.google.gson.annotations.SerializedName;


public class CustomerSigin {

	@SerializedName("image")
	private String image;

	@SerializedName("createdAt")
	private Object createdAt;

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("salt")
	private String salt;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("username")
	private String username;

	@SerializedName("updatedAt")
	private Object updatedAt;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setSalt(String salt){
		this.salt = salt;
	}

	public String getSalt(){
		return salt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"CustomerSignup{" +
			"image = '" + image + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",password = '" + password + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",salt = '" + salt + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",username = '" + username + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}